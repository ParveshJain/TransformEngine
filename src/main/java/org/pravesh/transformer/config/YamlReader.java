package org.pravesh.transformer.config;

import lombok.Data;
import lombok.NonNull;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Data
public class YamlReader <T> {

    private T configObject = null;

    public T getConfigObject() {
        return configObject;
    }

    public YamlReader(@NonNull String ruleFile, @NonNull String className) throws  Exception{

        InputStream in = this.getClass().getClassLoader().getResourceAsStream(ruleFile);
        if ( null == in){
            in = new FileInputStream(new File(ruleFile));
        }

        Yaml yaml = new Yaml(new Constructor(Class.forName(className)));
        this.configObject = yaml.load(in);

    }

}
