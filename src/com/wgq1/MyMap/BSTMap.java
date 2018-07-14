package com.wgq1.MyMap;

/**
 * 基于二叉搜索树  实现Map
 * @param <K>
 * @param <V>
 */
public class BSTMap<K extends Comparable<K>,V> implements MyMap<K,V> {

    /**
     * 内部类   map中的一个<K,V>
     */
    private class Node{
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key,V value,Node left,Node right){
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
        public Node(K key,V value){
            this(key,value,null,null);
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }



    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override//递归实现
    public void add(K key, V value) {
        add(root,key,value);
    }
    //在以node为根的二分搜索树中 插入一个节点 并返回插入后的新的根
    private Node add(Node node,K key, V value){
        if(node == null){
            node = new Node(key,value);
            size++;
            return node;
        }

        if(key.compareTo(node.key)<0){
            node.left = add(node.left,key,value);
        }else if(key.compareTo(node.key)>0){
            node.right = add(node.right,key,value);
        }else {
            throw new IllegalArgumentException(key+"已经存在了");
        }
        return node;
    }

    //返回以node为根节点的二分搜索树中，以key为键值的节点
    private Node getNode(Node node,K key){
        if(node == null){
            return null;
        }
        if(node.key.equals(key)){
            return node;
        }

        if(key.compareTo(node.key)<0){
            return getNode(node.left,key);
        }else{
            return getNode(node.right,key);
        }
    }
    @Override
    public V remove(K key) {
        Node node = getNode(root,key);
        if(node == null){
            throw new IllegalArgumentException("您要删除的元素不存在");
        }else {
            root = remove(root,key);
            return node.value;
        }
    }

    //删除 以node为根节点的二分搜索树中key为键值对应的节点  并返回删除后的新的根节点
    private Node remove(Node node,K key){
        if(node == null){
            return null;
        }else if(key.compareTo(node.key) == 0) {
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }else if(node.right == null){//待删除右子树为空的情况
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }else{
                //待删除左右节点都不为空的情况
                //在待删除节点的右子树中找到 最小的节点，称其为“后继”，在右子树中删除该后继，用这个后继代替要删除的节点
                Node successor = minimun(node);
                successor.left = node.left;
                successor.right = removeMin(node);
                node.left = node.right = null;
                return  successor;
            }
        }


        if(key.compareTo(node.key)<0){
            node.left = remove(node.left,key);
            return node;
        }else{
            node.right = remove(node.right,key);
            return node;
        }
    }

    //返回以node为根的二分搜索树中 key值最小的节点
    private Node minimun(Node node ){
        if(node.left == null){
            return node;
        }
        return minimun(node.left);
    }

    //删除掉 以node为根的 二分搜索树中 key值最小的节点 并返回删除后的新树的根节点
    private Node removeMin(Node node){
       if(node.left == null){
           Node rightNode = node.right;
           node.right = null;
           size--;
           return rightNode;
       }
       node.left = removeMin(node.left);
       return node;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root,key);
        if(node==null){
            throw new IllegalArgumentException(key+"不存在");
        }else{
            node.value = value;
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root,key);
        return node==null?null:node.value;
    }
}
