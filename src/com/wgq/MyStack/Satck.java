package com.wgq.MyStack;

/**
 * 自己模拟实现栈的基本操作
 *     void push(E e)  压栈
 *     E pop()   栈顶元素出栈
 *     E peek()  查看栈顶元素
 *     int getSize()   查看栈中元素的个数
 *     boolean isEmpty()
 * @param <E>
 */
public interface Satck<E> {

    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
