package com.wgq.LoopQueue;

import com.wgq.MyQueue.Queue;


/**
 * 循环队列
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E>{

    private E[] data;
    private int front;  //指向队首的指针
    private int tail;   //指向队列中最后一个元素的下个位置的指针   循环数组 有意识的浪费一个单位来做队列是否满的判断
    //队列为空的条件：front == tail;   队列满的条件 ：(tail+1)%数组的长度 == front
    private int size;

    public LoopQueue(int capacity){
        //循环数组 有意识的浪费一个单位来做队列是否满的判断
        data = (E[])new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length-1;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return front==tail;
    }

    @Override
    public void enqueue(E e) {
        //先判断队列是否已经满了
        if((tail+1)%data.length == front){
            //满了扩容
            resize(getCapacity()*2);
        }
        data[tail] = e;
        tail = (tail+1)%data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("队列为空，出队操作无法实现");
        }
        E ret = data[front];
        data[front] = null;
        front = (front+1)%data.length;
        size--;

        //出队之后判断一下是否需要缩小容量   注意Lazy策略  防止复杂度的震荡
        if(size == getCapacity()/4  && getCapacity()/2!=0){
            resize(getCapacity()/2);
        }
        return  ret;
    }

    @Override
    public E getFront() {
        //先判断队列是否为空
        if(isEmpty()){
            throw new IllegalArgumentException("队列为空");
        }
        return data[front];
    }

    /**
     * 改变容量的方法
     *    根据传来的参数大小 新建数组
     *    将原数组的值  按照【*队列*】的顺序放入新数组中
     *    修改 front 和 tail指针的值
     * @param newCapacity
     */
    private void resize(int newCapacity){
        E[] newDate = (E[])new Object[newCapacity+1];
        for(int i=0;i<size;i++){
            newDate[i] = data[(i+front)%data.length];
        }
        data = newDate;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue:\nSize = %d,Capacity = %d\n",this.size,getCapacity()) );
        res.append("队首 [");
//     两种方式 实现循环数组的遍历
//     1.i从 front开始 到 tail结束
        for(int i= front;i != tail ;i=(i+1)%data.length){
            res.append(data[i]);
            if((i+1)%data.length != tail){
                res.append(",  ");
            }
        }
       //2.i从0开始  加上一个front的偏移量
       /* for(int i=0;i<size;i++){
            res.append(data[(i+front)%data.length]);
            if(i!=size-1){
                res.append(",  ");
            }
        }*/
        res.append("] 队尾");
        return res.toString();
    }
}
