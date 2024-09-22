package org.pravesh.transformer.config;

import groovy.lang.Script;
import lombok.Data;
import java.util.List;

@Data
public class TRMetaData {

    private String ruleName;
    private String ruleScript;



    private Script ruleParsedScript;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
    public String getRuleScript() {
        return ruleScript;
    }

    public void setRuleScript(String ruleScript) {
        this.ruleScript = ruleScript;
    }

    public Script getRuleParsedScript() {
        return ruleParsedScript;
    }

    public void setRuleParsedScript(Script ruleParsedScript) {
        this.ruleParsedScript = ruleParsedScript;
    }

}
