package com.wgq.MyQueue;

import com.wgq.Array.MyArray;

/**
 * 复用 自己写的 动态数组  实现队列
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E>{
    private MyArray<E> array;

    public ArrayQueue(int capacity) {
        this.array = new MyArray<>(capacity);
    }

    public ArrayQueue(){
        this.array = new MyArray<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 入队  只能从队尾 入队
     * @param e
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * 出队  只能从队首出队
     * @return
     */
    @Override
    public E dequeue() {
       return array.removeFirst();
    }

    /**
     * 获得 队首元素
     * @return
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue:    ");
        res.append("队首: [");
        for (int i=0;i<array.getSize();i++){
            res.append(array.get(i));
            if(i!=array.getSize()-1){
                res.append(", ");
            }
        }
        res.append("] 队尾");
        return res.toString();
    }

}
