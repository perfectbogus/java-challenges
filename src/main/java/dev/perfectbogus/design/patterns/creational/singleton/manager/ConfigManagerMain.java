package dev.perfectbogus.design.patterns.creational.singleton.manager;

public class ConfigManagerMain {

    public static void main(String[] args) {
        ConfigManager config = ConfigManager.getInstance();
        String dbUrl = config.get("db.url");
        System.out.println(dbUrl);
    }
}
