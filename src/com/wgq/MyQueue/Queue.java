package com.wgq.MyQueue;

public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E e);//入队

    E dequeue();//出队

    E getFront();//获得 队首元素


}
