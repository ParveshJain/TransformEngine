package org.pravesh.transformer.executor;

import groovy.lang.Binding;
import groovy.lang.Script;
import lombok.NonNull;
import org.pravesh.transformer.config.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class RulesExecutor {

    private final Map<String, TRSet> trSetCache = new ConcurrentHashMap<>();

    public void executeYaml(@NonNull final String ruleFile, @NonNull final Map<String,Object> bindingData) throws Exception{

        final  TRSet trSet;

        try {
            if (trSetCache.containsKey(ruleFile)){
                trSet = trSetCache.get(ruleFile);
            }else {
                YamlReader<TRSet> yamlReader = new YamlReader<>(ruleFile, TRSet.class.getName());
                trSet  = yamlReader.getConfigObject();
                trSetCache.put(ruleFile,trSet);
            }
        }catch (Exception exp){
            exp.printStackTrace();
            throw  new Exception(String.format("Can not load rule definitions from %s file", ruleFile));
        }

        for (final TRGroup group : trSet.getRuleGroups()){
            Script parsedGrScript = group.getGrParsedEntryScript();

            boolean grEval = false;
            if (parsedGrScript == null && group.getRuleScript()!=null){
                parsedGrScript = GroovyHelper.parse(String.valueOf(group.getRuleScript()));
                group.setGrParsedEntryScript(parsedGrScript);
            }else {
                grEval = true;
            }

            if (!grEval){
                grEval = (Boolean)GroovyHelper.evaluate(newInstanceOfCompiledScript(bindingData,parsedGrScript));
            }

            if (grEval){
                for (final TRMetaData rules : group.getRuleScript()){
                    Script parsedRuleScript = rules.getRuleParsedScript();

                    if (parsedRuleScript == null){
                        parsedRuleScript = GroovyHelper.parse(rules.getRuleScript());
                        rules.setRuleParsedScript(parsedRuleScript);
                    }

                    GroovyHelper.evaluate(newInstanceOfCompiledScript(bindingData,parsedRuleScript));
                }
            }
        }
    }

    private final Script newInstanceOfCompiledScript(final Map<String,Object> bindingData, final Script compiledScript ) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final Constructor constructor = compiledScript.getClass().getConstructor(Binding.class);
        final Script script =(Script)constructor.newInstance(new Binding(bindingData));
        script.setMetaClass(compiledScript.getMetaClass());
        return script;
    }
}
