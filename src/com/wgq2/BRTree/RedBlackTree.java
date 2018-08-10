package com.wgq2.BRTree;

import com.sun.org.apache.regexp.internal.RE;

/**
 * 模拟实现  红黑树
 *    红黑树的性质
 *    1.每个节点的颜色或者是红色的   或者是黑色的
 *    2.根节点一定是黑色的
 *    3.每一个叶子节点（指最后的空节点）是黑色的
 *    4.如果在红黑树中，一个节点时红色的，那么他的子节点是黑色的
 *    5.从任意一个节点出发到叶子节点，经过的黑色节点是一样的
 */
public class RedBlackTree<K extends Comparable<K>,V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        private Node left,right;
        private boolean color;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;
    public RedBlackTree(){
        root = null;
        size = 0;
    }

    private boolean isRed(Node node){
        if(node == null){
            return BLACK;
        }
        return node.color;
    }


    /**
     * 红黑树添加新元素
     *    保持  根节点为黑色
     */

    public void add(K key,V value){
        root = add(root,key,value);
        root.color = BLACK;
    }
    //辅助过程1：红黑树的左旋转
    private Node leftRotate(Node node ){
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }
    //辅助过程2：颜色反转
    private void flipColors(Node node){
        node.color = RED;
        node.right.color = BLACK;
        node.left.color = BLACK;
    }
    //辅助过程3：红黑树的右旋转
    private Node rightRotate(Node node){
        Node x = node.left;
        Node t1 = x.right;
        node.left = t1;
        x.right = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }
    //向以node为根的红黑树中插入元素
    private Node add(Node node,K key,V value){
        if(node == null){
            size++;
            return new Node(key,value);
        }
        if(key.compareTo(node.key) < 0){
            node.left = add(node.left,key,value);
        }else if(key.compareTo(node.key) > 0){
            node.right = add(node.right,key,value);
        }else{
            node.value = value;
        }

        //判断是否需要左旋转
        if(isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }
        //判断是否需要右旋转
        if(isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        //判断是否 需要颜色反转
        if(isRed(node.right) && isRed(node.left)){
            flipColors(node);
        }
        //返回递归的上一层   继续验证
        return node;
    }



}
