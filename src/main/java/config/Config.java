package config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
/**
 * The Config class is responsible for loading and providing access to configuration settings.
 * It utilizes the Jackson library for JSON parsing.
 */

public class Config {
    private static Map<String, Object> globalConfig;
    private static Map<String, Object> localConfig;
    private String browser;
    private String chromeDriverPath;
    private String firefoxDriverPath;
    private String internetExplorerDriverPath;

    /**
     * Loads the configuration settings from a JSON file.
     * @param filePath The path to the JSON configuration file.
     * @return An instance of the Config class populated with the configuration settings.
     */
    public static Config loadConfig(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filePath), Config.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Gets the browser type from the configuration.
     *
     * @return The browser type.
     */
    public String getBrowser() {
        return browser;
    }
    /**
     * Gets the path to the ChromeDriver executable from the configuration.
     *
     * @return The path to the ChromeDriver executable.
     */
    public String getChromeDriverPath() {
        return chromeDriverPath;
    }
    /**
     * Gets the path to the FirefoxDriver executable from the configuration.
     *
     * @return The path to the FirefoxDriver executable.
     */
    public String getFirefoxDriverPath() {
        return firefoxDriverPath;
    }
    /**
     * Gets the path to the Internet Explorer Driver executable from the configuration.
     *
     * @return The path to the Internet Explorer Driver executable.
     */
    public String getInternetExplorerDriverPath() {
        return internetExplorerDriverPath;
    }

    static {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Load global configuration
            JsonNode globalNode = objectMapper.readTree(new File("src/main/resources/config/global-config.json"));
            globalConfig = objectMapper.convertValue(globalNode, Map.class);
           /* Load local configuration
            JsonNode localNode = objectMapper.readTree(new File("src/main/resources/config//local-config.json"));
            localConfig = objectMapper.convertValue(localNode, Map.class);*/
        } catch (IOException e) {
            throw new RuntimeException("Error loading JSON configuration", e);
        }
    }
    /**
     * Gets a value from the global configuration using the specified key.
     *
     * @param key The key to retrieve the value.
     * @return The value associated with the key in the global configuration.
     */
    public static Object getGlobalValue(String key) {
        return globalConfig.get(key);
    }
    /**
     * Gets a value from the local configuration using the specified key.
     *
     * @param key The key to retrieve the value.
     * @return The value associated with the key in the local configuration.
     */
    public static Object getLocalValue(String key) {
        return localConfig.get(key);
    }
}
