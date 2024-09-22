package org.pravesh.transformer.config;

import groovy.lang.Script;

import java.util.List;

public class TRGroup {

    private String grName;

    private String grEntryScript;

    private Script grParsedEntryScript;

    private List <TRMetaData> ruleScript;

    public String getGrName(){
        return this.grName;
    }

    public void setGrName(String str){
            this.grName =str;
    }

    public String getGrEntryScript(){
        return this.grEntryScript;
    }

    public void setGrEntryScript(String str){
        this.grEntryScript = str;
    }

    public  Script getGrParsedEntryScript(){
        return  this.grParsedEntryScript;
    }

    public void setGrParsedEntryScript(Script compiledScript){
        this.grParsedEntryScript = compiledScript;
    }

    public List <TRMetaData> getRuleScript(){
        return  this.ruleScript;
    }

    public void setRuleScript(List <TRMetaData> rules){
        this.ruleScript = rules;
    }
}
