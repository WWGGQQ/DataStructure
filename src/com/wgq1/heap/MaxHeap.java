package com.wgq1.heap;

import com.wgq.Array.MyArray;

/**
 * 堆的本质 是一棵完全二叉树
 *    最大堆：父亲节点的值总是大于子节点的值
 *   使用数组实现堆
 */
public class MaxHeap<E extends Comparable<E>> {

    private MyArray<E> data;

    public MaxHeap(int capacity){
        data = new MyArray<>(capacity);
    }

    public MaxHeap(){
        data = new MyArray<>();
    }
    /**
     * heapify  : 将任意数组整理成 堆的形状
     *     对于这个数组，当作是完全二叉树的数组表示，从最后一个非叶子节点开始向前 不断进行siftDown 操作
     *     需要确定倒数第一个非叶子节点的索引：
     *            其实就是最后一个节点的父节点  so easy
     */
    public MaxHeap(E[] es){
        data = new MyArray<>(es);
        for(int i= parent(es.length-1);i>=0;i--){
            siftDown(i);
        }
    }

    /**
     * 返回堆中元素的个数
     */
    public int size(){
        return data.getSize();
    }
    /**
     * 判断堆中元素的个数
     */
    public boolean isEmpty(){
        return data.isEmpty();
    }

    //三个私有的辅助函数
    /**
     * 返回完全二叉树的 数组表示中  一个索引所表示的节点的 父节点的索引
     */
    private int parent(int index){
        if(index == 0 ){
            throw new IllegalArgumentException("根节点没有父节点");
        }
        return (index-1)/2;
    }
    /**
     * 返回完全二叉树的数组表示中，一个索引所标识的节点中，左孩子的索引
     */
    private int leftChild(int index){
        return index*2+1;
    }
    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     */
    private int rightChild(int index){
        return index*2+2;
    }

    /**
     * 向堆中插入一个元素（sift up）
     *   1.向完全二叉树的数组表示的最后插入一个元素
     *   2.比较新插入的元素和他的父节点的值的大小关系（利用到私有的辅助函数）
     *       若新插入的值比其父节点的值大 交换二者在数组中的位置
     *   3.重复 2 直到 2 中的条件不成立
     */
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    private void siftUp(int k){
        while(k>0 && data.get(k).compareTo(data.get(parent(k))) > 0){
            data.swap(k,parent(k));
            k = parent(k);
        }
    }

    /**
     * 取出堆中的 最大值
     *    其实就是 取出堆顶的元素 取出堆顶的元素后 将剩下的两个子树融合成为一个新的树
     *        1.将完全二叉树的数组表示中的最后一位元素移到数组头
     *        2.新的根元素 和 两个子节点中最大的节点 进行比较
     *            若 新的根元素<两个字节点中最大的那个   二者交换位置
     *        3.重复2中的操作 直到条件不满足
     */
    public E extractMax(){
        E max = findMax();
        data.swap(0,data.getSize()-1);
        data.removeLast();
        siftDown(0);
        return max;
    }

    /**
     * 查看 堆中最大元素
     */
    public E findMax(){
        if(data.getSize() == 0){
             throw new IllegalArgumentException("堆是空的");
        }
        return data.get(0);
    }

    private void siftDown(int k){
        while(leftChild(k) < data.getSize()){
            int j = leftChild(k);
            if(j+1<data.getSize() && data.get(j).compareTo(data.get(j+1)) < 0){
                j = rightChild(k);
            }
            //此时 data[j] 是 索引为k的节点的左右孩子中的最大值
            if(data.get(k).compareTo(data.get(j)) >= 0){
                break;
            }else{
                data.swap(k,j);
                k = j;
            }
        }
    }

    /**
     * replace 取出堆中最大元素 在放入一个新元素
     *    两种方法
     *       1.组合之前写的逻辑  先 extractMax 在add(e)   两次 O(logn) 的操作
     *       2.先 用 e 替换掉 第一个元素  在siltDown(0)   一次 O(logn) 的操作
     */
    public E replace(E e){
        E ret = findMax();
        data.set(0,e);
        siftDown(0);
        return ret;
    }



}
