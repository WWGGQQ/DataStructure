package com.wgq1.BinSearchTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/***
 * 二分搜索树
 *    是一棵特殊的二叉树
 *      对于二叉搜索树中的每一个节点
 *        大于其左子树所有节点的值
 *        小于其右子树所有的值
 *    每一颗子树也是一颗二分搜索树
 *    ****树中存储的数据类型要具有可比较性
 *
 */
public class BST<E extends Comparable<E>> {

    /**
     * 私有内部类
     *    二分搜索树的节点
     */
    private class Node{
        public E e;
        public Node left,right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
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
     * 向二分搜索树添加元素  使用递归
     */
    public void add(E e){
        root = add(root,e);
    }

    //返回插入新节点后二分搜索树的根
    private Node add(Node node, E e){
      //递归终止条件
        if(node == null){
            size++;
            node = new Node(e);
            return node;
        }
     //递归体
        if(e.compareTo(node.e)<0){
            node.left = add(node.left,e);
        }else if(e.compareTo(node.e)>0){
            node.right = add(node.right,e);
        }
        return node;
    }

    //看在二分搜索树中是否包含元素
    public boolean contains(E e){
        return contaions(root,e);
    }

    private boolean contaions(Node node,E e){
        if(node == null){
            return  false;
        }
        if(e.compareTo(node.e)<0){
           return contaions(node.left,e);
        }else if(e.compareTo(node.e)>0){
           return contaions(node.right,e);
        }else{
            return true;
        }
    }

    /**
     * 二分搜索树的前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    //前序遍历以node为根的二叉树
    private void preOrder(Node node){
        //递归终止条件
        if(node == null){
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 前序遍历的非递归写法
     *    利用栈
     */
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
            if(cur.right!=null){
                stack.push(cur.right);
            }
            if(cur.left!=null){
                stack.push(cur.left);
            }

        }
    }
    /**
     * 中序遍历二分搜索树
     *      二分搜索树 中序遍历  得到所有元素按顺序排序的结果  所以又叫二叉排序树
     */
    public void inOrder(){
        inOrder(root);
    }
    //中序遍历以node为根的二分搜索树
    public void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 二叉搜索树的后序遍历
     *    典型应用： 释放内存
     */
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 二叉搜索树的层次遍历
     *     非递归 需要借助队列
     */
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e);
            if(cur.left!=null){
                queue.add(cur.left);
            }
            if(cur.right!=null){
                queue.add(cur.right);
            }
        }
    }


    /**
     * 删除二分搜索树中的最小值 和 最大值
     *     先找到  再删除
     */
    //寻找二分搜索树中的最小值
    public E minimum(){
        if(size == 0){
            throw new IllegalArgumentException("树为空");
        }
        return minimum(root).e;
    }
    //返回以node为根的 二分搜索树的 最小值所在的节点
    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    //寻找二分搜索树中的最大值
    public E maximum(){
        if(size == 0){
            throw new IllegalArgumentException("树为空");
        }
        return maximum(root).e;
    }
    //返回以node为根的 二分搜索树的 最大值所在的节点
    private Node maximum(Node node){
        if(node.right == null){
            return node;
        }
        return maximum(node.right);
    }
    //删除 的 具体操作   要考虑待删除节点是否还存在子树
        //删除最小值  并返回
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }
    //删除以node为根的二分搜索树的最小节点，并返回删除后的新的二分搜索树的根
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

    //删除最大值  并返回
    public E removeMax(){
        E ret = minimum();
        root = removeMax(root);
        return ret;
    }
    //删除以node为根的二分搜索树的最大节点，并返回删除后的新的二分搜索树的根
    private Node removeMax(Node node){
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }
    /**
     * 删除二分搜索树中的 任意节点
     *     1.删除的节点只有左孩子
     *           左子树取代他的位置
     *     2.删除的节点只有右孩子
     *           右子树取代它的位置
     *     3.删除叶子节点
     *     4.删除的节点左右孩子都有
     *          找到待删除节点的右子树中的最小节点  替代要删除的节点
     *
     *
     * 可以复用之前写的方法
     */

    public void remove(E e){
        root = remove(root,e);
    }

    //删除以node为根的二分搜索树中 值为e的节点 并返回删除后额新的根
    private Node remove(Node node ,E e){
        if(node == null){
            return null;
        }
        if(e.compareTo(node.e)<0){
            node.left = remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e)>0){
            node.right = remove(node.right,e);
            return node;
        }else{
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node successor  = minimum(node.right);//找到后继
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;

        }

    }
    /**
     * 利用先序遍历打印出二叉搜索树
     * @return
     */
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    private void generateBSTString(Node node,int depth,StringBuilder res){
        if(node == null){
            res.append(gennerateDepthString(depth)+"null\n");
            return;
        }
        res.append(gennerateDepthString(depth)+node.e+"\n");
        generateBSTString(node.left,depth+1,res);
        generateBSTString(node.right,depth+1,res);
    }

    private String gennerateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i=0;i<depth;i++){
            res.append("--");
        }
        return res.toString();
    }

}

