package com.howtodoinjava.hibernate.test;



import java.util.LinkedHashMap;
import java.util.Map;

public class MapDemoClass {

    private static void lruCacheTest() {
        System.out.println("\n\nInside lruCacheTest...");
        Map<String, String> lruCache = new LRUCache<>(16, 0.75F, true);
        lruCache.put("a", "A");
        lruCache.put("b", "B");
        lruCache.put("c", "C");

        System.out.println(lruCache);

        lruCache.get("a");
        lruCache.get("a");
        lruCache.get("a");
        System.out.println(lruCache);
        lruCache.get("b");
        System.out.println(lruCache);

        lruCache.put("d", "D");
        System.out.println(lruCache);

        lruCache.put("e", "E");
        System.out.println(lruCache);
    }


    public static void main(String[] args) {
        lruCacheTest();
    }
}
    class LRUCache<K,V> extends LinkedHashMap<K,V>{
        private static final int MAX_ENTRIES = 3;

        public LRUCache(int initialCapacity, float loadFactor, boolean accessOrder) {
            super(initialCapacity, loadFactor, accessOrder);
        }

        public boolean removeEldestEntry(Map.Entry eldest){
            return size() > MAX_ENTRIES;
        }
    }
