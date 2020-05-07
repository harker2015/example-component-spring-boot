package org.github.harker2015.example.component.core;

import java.util.HashMap;
import java.util.Map;

public class LocalCache {
    private static Map<String, String> map;

    public LocalCache() {
        this.map = new HashMap<>();
    }

    public static String get(String key){
        return map.get(key);
    }

    public static void set(String key, String value){
        map.put(key, value);
    }
}
