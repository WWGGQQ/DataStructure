package com.wgq1.MyMap;

/**
 * 模拟映射接口
 * @param <K>
 * @param <V>
 */
public interface MyMap<K,V> {
    int getSize();

    boolean isEmpty();

    void add(K key,V value);

    V remove(K key);

    void set(K key,V value);

    boolean contains(K key);

    V get(K key);

}
