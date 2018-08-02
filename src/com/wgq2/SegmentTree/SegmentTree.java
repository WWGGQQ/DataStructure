package com.wgq2.SegmentTree;

/**
 * 线段树
 *
 */
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;


    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger = merger;
        data = (E[])new Object[arr.length];
        for(int i=0;i<data.length;i++){
            data[i] = arr[i];
        }

        tree = (E[])new Object[4*arr.length];
        buildSegmentTree(0,0,data.length-1);

    }


    /**
     * 在treeIndex位置创建表示区间【l,r】的线段树
     * @param treeIndex  创建的线段树的根节点
     * @param l   左端点
     * @param r   右端点
     */
    private void buildSegmentTree(int treeIndex,int l,int r){
        //递归结束条件
        if(l == r){
          tree[treeIndex]  = data[l];
          return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l+(r-l)/2;//考虑一个 整形溢出的问题
        //递归创建左右子树
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid+1,r);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }
    /**
     * 两个辅助函数
     */
    //返回完全二叉树的数组表示中  ---一个索引所表示的元素的左孩子的索引
    private int leftChild(int index){
        return 2*index+1;
    }
    //返回完全二叉树的数组表示中  ---一个索引所表示的元素的右孩子的索引
    private int rightChild(int index){
        return 2*index+2;
    }
    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0||index>=data.length){
            throw new IllegalArgumentException("index不合法");
        }

        return data[index];
    }


    /**
     * 返回区间[queryL,queryR];所存储的值
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL,int queryR){
        if(queryL<0||queryL>=data.length||queryR<0||queryR>=data.length||queryL>queryR){
            throw new IllegalArgumentException("索引不合法");
        }
        return query(0,0,data.length-1,queryL,queryR);
    }

    /**
     * 在 treeIndex索引所在的线段树的【l,r】区间中 找到区间【queryL，queryR】的值
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex,int l,int r,int queryL,int queryR){
        if(l == queryL && r == queryR){
            return tree[treeIndex];
        }

        int mid = l+(r-l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex  = rightChild(treeIndex);

        if(queryL >= mid+1){
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        }else if(queryR <= mid){
            return query(leftTreeIndex,l,mid,queryL,queryR);
        }

        //一部分落在左孩子区间   一部分落右孩子区间
        E leftResult = query(leftTreeIndex,l,mid,queryL,mid);
        E rightResult = query(rightTreeIndex,mid+1,r,mid+1,queryR);
        return merger.merge(leftResult,rightResult);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i=0;i<tree.length;i++){
            if(tree[i] != null){
                res.append(tree[i]);
            }else {
                res.append("NULL");
            }
            if(i != tree.length - 1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

}
