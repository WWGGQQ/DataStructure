package com.wgq.MyStack;

import com.wgq.Array.MyArray;

/**
 * 复用之前写的 MyArray结构 实现栈
 *
 * @param <E>
 */
public class ArrayStack<E> implements Satck<E>{

    private MyArray<E> array;

    public ArrayStack(int capacity){
        array = new MyArray<>(capacity);
    }

    public ArrayStack(){
        this(10);
    }


    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack:");
        res.append("[");
        for(int i=0;i<array.getSize();i++){
            res.append(array.get(i));
            if(i != array.getSize()-1){
                res.append(", ");
            }
        }
        res.append("]  top");
        return res.toString();
    }
}
