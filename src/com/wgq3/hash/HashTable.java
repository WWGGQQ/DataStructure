package com.wgq3.hash;
import com.wgq.LinkList.LinkedList;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用jdk提供的TreeMap
 * 实现自己的 哈希表
 *    hash + TreeMap
 */
public class HashTable<K ,V> {


    private static final int uperTol = 10;//用作 动态空间处理 上界
    private static final int lowerTol = 2;//用作 动态空间处理 下界
    private static final int initCapicity = 7;

    private TreeMap<K,V>[] hashTable;
    private int M; //hash表中 有多少位置
    private int size; // 表中的 元素个数


    public HashTable(int M){
        this.M = M;
        size = 0;
        hashTable = new TreeMap[M];

        for(int i = 0; i < M ; i++){
            hashTable[i] = new TreeMap<>();
        }
    }

    public HashTable(){
        this(initCapicity);
    }

    //一个私有的辅助方法
       //对于任意类型的 key返回一个整数 （对应hash表数组的索引）
    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M ;// 去掉符号位
    }

    public int getSize(){
        return this.size;
    }

    //添加
    public void add(K key,V value){
        TreeMap<K,V> map = hashTable[hash(key)];
        if(map.containsKey(key)){//表中已经存在 相同的key值   在原来的基础上修改
            map.put(key,value);
        }else{
            map.put(key,value);//不存在  ，添加  维护一下 size
            size++;
            if(size >= uperTol * M){//判断是否需要扩容
                resize(2*M);
            }
        }
    }

    //删除
    public V remove(K key){
        TreeMap<K,V> map = hashTable[hash(key)];
        V ret = null;
        if(map.containsKey(key)){
            ret = map.remove(key);
            size--;
            if(size <= lowerTol * M  && M/2 >= initCapicity){
                resize(M/2);
            }
        }
        return ret;
    }

    //修改
    public void set(K key,V value){
        TreeMap<K,V> map = hashTable[hash(key)];
        if(!map.containsKey(key)){
            throw new IllegalArgumentException(key+"不存在");
        }else{
            map.put(key,value);
        }
    }

    public boolean contains(K key){
        TreeMap<K,V> map = hashTable[hash(key)];
        return map.containsKey(key);
    }

    public V get(K key){
        return hashTable[hash(key)].get(key);
    }


    //私有辅助函数  扩容和 缩容的具体实现
    /*
        新建一个 TreeMap的数组  把原来哈希表中的数据 全部复制到 新的 数组中
     */
    private void resize(int newM){
        TreeMap<K,V>[] newHashTable = new TreeMap[newM];
        for(int i=0;i<newM;i++){
            newHashTable[i] = new TreeMap<>();
        }
        //注意  在改变容量后 重新计算hash值的 时候  要 改变 M 的值
        int oldM = M;
        this.M = newM;
        for(int i=0;i<oldM;i++){
            TreeMap<K,V> map = hashTable[i];
            for(K key:map.keySet()){
                newHashTable[hash(key)].put(key,map.get(key));
            }
        }
        this.hashTable = newHashTable;

    }

}
