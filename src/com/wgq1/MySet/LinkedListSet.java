package com.wgq1.MySet;

import com.wgq.LinkList.LinkedList;

/**
 *使用链表实现集合
 * @param <E>
 */
public class LinkedListSet<E> implements MySet<E> {
    private LinkedList<E> linkedList;

    public LinkedListSet(){
        linkedList = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if(!linkedList.contains(e)){
            linkedList.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        linkedList.deleteElement(e);
    }

    @Override
    public boolean contains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
/*
对于二分搜素树增加删除 复杂度是O(h)  h是树的高度
    对于链表 O（n）  n为链表的长度
*/
