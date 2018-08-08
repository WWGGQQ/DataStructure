package com.wgq2.AVLTree;

import java.util.ArrayList;

/**
 * 平衡树
 */
public class AVL<K extends Comparable<K>,V> {
    /**
     * 内部类
     *     树中的节点
     */
    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public int height; //当前节点的高度值


        public Node(K key,V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }


    private Node root;
    private int size;

    public AVL(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 私有的辅助函数   获得 节点的高度值
     * @param node
     * @return
     */
    private int getHeight(Node node ){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    /**
     * 私有辅助函数  获得节点的平衡因子
     * @return
     */
    private int getBalanceFactor(Node node){
        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }
    /**
     * 判断当前二叉树是否是一棵二分搜索树
     *     利用二分搜索树的一个性质   : 对二分搜索树进行中序遍历    所有的元素一定是有序的！！
     */
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);
        for(int i=1;i<keys.size();i++){
            if(keys.get(i-1).compareTo(keys.get(i)) > 0){//不满足性质不是二分搜索树
                return false;
            }
        }
        return true;
    }
       //中序遍历二叉树  遍历结果放在传入的动态数组中
    private void inOrder(Node node,ArrayList<K> keys){
        if(node == null ){
            return;
        }
        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    /**
     * 判断是否是一棵 平衡二叉树
     * @return
     */
    public boolean isBalanced(){
        return isBalanced(root);
    }
    private boolean isBalanced(Node node){
        if(node == null){
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 对节点y进行向右旋转操作   返回旋转后的新的根节点x
     *             y                               x
     *           /  \           右旋转            /  \
     *         x     t4     ------------->      z     y
     *       /  \                              / \    /\
     *     z     t3                           t1 t2  t3 t4
     *    / \
     *   t1  t2
     * @param y
     * @return
     */
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;
        //向右旋转
        x.right = y;
        y.left = T3;
        //更新节点的height值
        y.height = Math.max(getHeight(y.left),getHeight(y.right))+1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right))+1;

        return x;
    }
    /**
     * 对节点y进行向左旋转操作   返回旋转后的新的根节点x
     *                  y
     *                 / \                                 x
     *               T4   x              左旋转           / \
     *                   / \          ----------->       y    z
     *                 T3   z                           /\     /\
     *                     / \                        T4  T3  T2 T1
     *                    T2  T1
     *
     * @param y
     * @return
     */
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        y.height = Math.max(getHeight(y.left),getHeight(y.right))+1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right))+1;

        return x;
    }
    /**
     * 向AVL树中插入新的元素    要考虑是否打破了平衡
     *     加入节点后  沿着节点向上回溯维护平衡性
     */
    public void add(K key,V value){
        root = add(root,key,value);
    }
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

        //维护一下height************
        node.height = 1+Math.max(getHeight(node.left),getHeight(node.right));
        //计算平衡因子**************
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){//判断是否打破了AVLTree的条件
            System.out.println("unbalanced: " + balanceFactor);
        }
        //进行平衡的维护**************
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){//情况1：LL 左子树高度比右子树高度高  并且大于2
            //右旋转
            return rightRotate(node);
        }
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0){//情况2：RR 右子树高度比左子树高度高  并且大于2
            //左旋转
            return leftRotate(node);
        }
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){//情况3 :LR
            //先node的左孩子进行一次左旋转  转化成 LL模式
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){//情况4 RL
            //先对node的有孩子进行一次右旋转 转化为RR模式
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }


    /**
     * AVL树的删除
     */
    private Node remove(Node node,K key){
        Node retNode = null;

        //递归终止条件
        if(node == null){
            return null;
        }else if(key.compareTo(node.key) == 0){
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }

            Node successor  = minimum(node.right);//找到后继
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            retNode = successor;
        }

        //递归体
        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left,key);
            retNode = node;
        }else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right,key);
            retNode = node;
        }

        //保持平衡的机制
        //维护一下height************
        node.height = 1+Math.max(getHeight(retNode.left),getHeight(retNode.right));
        //计算平衡因子**************
        int balanceFactor = getBalanceFactor(retNode);
        if(Math.abs(balanceFactor) > 1){//判断是否打破了AVLTree的条件
            System.out.println("unbalanced: " + balanceFactor);
        }

        //进行平衡的维护**************
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){//情况1：LL 左子树高度比右子树高度高  并且大于2
            //右旋转
            return rightRotate(retNode);
        }
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0){//情况2：RR 右子树高度比左子树高度高  并且大于2
            //左旋转
            return leftRotate(retNode);
        }
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){//情况3 :LR
            //先node的左孩子进行一次左旋转  转化成 LL模式
            node.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){//情况4 RL
            //先对node的有孩子进行一次右旋转 转化为RR模式
            node.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return  retNode;

    }

    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }
    private Node removeMin(Node node){
        if(node.left == null){
            Node rigthtNode = node.right;
            node.right = null;
            size--;
            return rigthtNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

}
