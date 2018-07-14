package com.wgq1.MyMap;

/**
 * 实现一链表为底层结构的 映射
 */
public class LinkedListMap<K,V> implements MyMap<K,V> {

    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value,Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key){
            this(key,null,null);
        }
        public Node() {
            this(null,null,null);
        }

        @Override
        public String toString(){
            return key.toString()+":"+value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = null;
        size = 0;
    }

    /**
     * 一个辅助的方法  通过key找到整个的Node
     * @return
     */
    private Node getNode(K key){
        Node cur = dummyHead.next;
        while(cur != null){
            if(key.equals(cur.key)){
                return cur;
            }
            cur = cur.next;
        }
        return  null;
    }
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(K key, V value) {
        //相当于向链表的头部插入一个节点  需要先判断Key是否存在
        Node node = getNode(key);
        if(node == null){
            dummyHead.next = new Node(key,value,dummyHead.next);
            size++;
        }else
            throw new IllegalArgumentException("key值 已经存在");
    }

    @Override
    public V remove(K key) {
        Node pre = dummyHead;
        while(pre.next!=null){
            if(key.equals(pre.next.key)){
                break;
            }
            pre = pre.next;
        }
        if(pre.next!=null){
            Node del = pre.next;
            pre.next = del.next;
            size--;
            del.next = null;
            return del.value;
        }
        return null;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if(node == null){
            throw new IllegalArgumentException(key+"不存在");
        }else{
            node.value = newValue;
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node==null?null:node.value;
    }
}
