package dev.perfectbogus.design.patterns.creational.configuration.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static volatile ConfigManager instance;
    private final Properties props = new Properties();

    private ConfigManager() {
        try (InputStream in = getClass().getResourceAsStream("/config.properties")) {
            if (in == null) throw new IllegalStateException("config.properties not found on classpath");
            props.load(in);
            System.out.println("Config loaded!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConfigManager getInstance() {
        ConfigManager result = instance;
        if (result == null) {
            synchronized (ConfigManager.class) {
                result = instance;
                if (result == null) {
                    instance = result = new ConfigManager();
                }
            }
        }
        return result;
    }

    public String get(String key) {
        return props.getProperty(key);
    }
}
