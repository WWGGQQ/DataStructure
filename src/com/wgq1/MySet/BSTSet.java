package com.wgq1.MySet;

import com.wgq1.BinSearchTree.BST;

/**
 * 基于之前写过的 二分搜索树  实现Set
 * @param <E>
 */
public class BSTSet<E extends Comparable<E>> implements MySet<E>{

    private BST<E> bst;

    public BSTSet(){
        this.bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
