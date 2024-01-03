package configuration;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
/**
 * The Configuration class is responsible for loading and providing access to selector and value configurations
 * from JSON files.
 */
public class Configuration {
    private ObjectNode selectors;
    private ObjectNode values;
    /**
     * Constructs a new {@code Configuration} instance by loading selector and value configurations from JSON files.
     *
     * @param selectorsPath The path to the JSON file containing selector configurations.
     * @param valuesPath    The path to the JSON file containing value configurations.
     * @throws IOException If there is an error reading the JSON files.
     */
    public Configuration(String selectorsPath, String valuesPath) throws IOException {
        if (!isJsonFile(selectorsPath) || !isJsonFile(valuesPath)) {
            throw new IllegalArgumentException("One of the files is not of JSON format");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        selectors = (ObjectNode) objectMapper.readTree(new File(selectorsPath));
        values = (ObjectNode) objectMapper.readTree(new File(valuesPath));
    }
    /**
     * Retrieves the selector associated with the given key.
     *
     * @param key The key for the selector.
     * @return The selector string, or null if the key is not found.
     */
    public String getSelector(String key) {
        JsonNode selectorNode = selectors.get(key);
        return selectorNode != null ? selectorNode.asText() : null;
    }
    /**
     * Retrieves the value associated with the given key.
     *
     * @param key The key for the value.
     * @return The value string, or null if the key is not found.
     */
    public String getValue(String key) {
        JsonNode valueNode = values.get(key);
        return valueNode != null ? valueNode.asText() : null;
    }

    private boolean isJsonFile(String filePath) {
        return filePath.toLowerCase().endsWith(".json");
    }

}
