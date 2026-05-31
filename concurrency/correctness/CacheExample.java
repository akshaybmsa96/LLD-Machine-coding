package concurrency.correctness;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheExample {
    public static void main(String[] args) {

        Cache<String, Integer> cache = new Cache<>();

        System.out.println(cache.getKeyData("data"));
        cache.putKeyData("data", 1);
        System.out.println(cache.getKeyData("data"));
        cache.putKeyData("data1", 2);
        cache.putKeyData("data2", 3);
        System.out.println(cache.getKeyData("data1"));
        System.out.println(cache.getKeyData("data2"));

    }
}

class Cache<K, V> {

    private final Map<K, V> cacheMap = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public V getKeyData(K key){
        try{
            lock.readLock().lock();
            return cacheMap.get(key);
        }finally {
            lock.readLock().unlock();
        }
    }

    public void putKeyData(K key, V data){
        try{
            lock.writeLock().lock();
            cacheMap.put(key, data);

        }finally {
            lock.writeLock().unlock();
        }

    }
}

