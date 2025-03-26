package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigManager {


private static Dotenv dotenv;

static {
    String env = System.getProperty("env", "dev"); // Default to "dev"
    dotenv = Dotenv.configure()
            .directory(System.getProperty("user.dir")) // Root directory
            .filename(".env." + env) // Load correct environment file
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();
}

public static String get(String key) {
    return dotenv.get(key);
}

public static boolean getBoolean(String key) {
    return Boolean.parseBoolean(dotenv.get(key));
}
}