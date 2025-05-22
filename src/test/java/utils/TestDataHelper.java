package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class TestDataHelper {
    private static Map<String, String> testData = new HashMap<>();

    public static void loadTestData(String filePath) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            for (Object key : jsonObject.keySet()) {
                testData.put((String) key, (String) jsonObject.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getData(String key) {
        return testData.getOrDefault(key, "DataNotFound");
    }
}
