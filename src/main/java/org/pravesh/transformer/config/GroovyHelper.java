package org.pravesh.transformer.config;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilationFailedException;
import org.codehaus.groovy.control.CompilerConfiguration;

public class GroovyHelper {

    public  static Script parse (final String strScript) throws CompilationFailedException {
        final CompilerConfiguration config = new CompilerConfiguration();
        final GroovyShell shell = new GroovyShell(null, new Binding(), config);
        return  shell.parse(strScript);
    }

    public static  Object evaluate (final Script script){
        return script.run();
    }
}
