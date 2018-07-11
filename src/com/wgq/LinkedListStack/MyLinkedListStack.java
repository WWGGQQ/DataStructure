package com.wgq.LinkedListStack;

import com.wgq.LinkList.LinkedList;
import com.wgq.MyStack.Satck;

/**
 * 利用自己写的链表结构（com.wgq.LinkList.LinkedList）实现栈的操作
 *    对于链表来说  对头结点进行操作的时间复杂的都是O(1)   所以用来实现栈  把链表头当作栈顶 性能很好
 */
public class MyLinkedListStack<E> implements Satck<E>{

    private LinkedList<E> linkedList;

    public MyLinkedListStack(){
        linkedList = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.deleteFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    //遍历  从栈顶开始  也就是从链表的头部开始
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("LinkedStack:  top：");
        res.append(linkedList);
        return res.toString();
    }
}
