package quiz;

import java.util.List;
import java.util.Map;

public class MapHandler {
    private static final MapHandler instance = new MapHandler();

    public static MapHandler getInstance() {
        return instance;
    }

    public void createMap(List<String> list, Map<Integer, String> map) {

        map.clear();

        for (int i = 1; i < list.size() + 1; i++) {
            map.put(i, list.get(i - 1));
        }

    }

    public void printMap(Map<Integer, String> map) {

        for (Map.Entry<Integer, String> singleKeyValue : map.entrySet()) {
            int index = singleKeyValue.getKey();
            String value = singleKeyValue.getValue();
            System.out.println(index + ". " + value);
        }

    }
}
