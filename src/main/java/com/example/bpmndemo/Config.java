package com.example.bpmndemo;

import ch.qos.logback.classic.gaffer.PropertyUtil;

import java.io.InputStreamReader;
import java.util.Properties;

public class Config {
    public static Properties properties;
    private static Config config = new Config();

    static {
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(Config.class.getClassLoader().getResourceAsStream("\\config.properties"), "UTF-8");
            properties = new Properties();
            properties.load(reader);
        } catch (Exception e) {

        }

    }

    private Config(){}
    public static Config getInstance(){
        return config;
    }
}
