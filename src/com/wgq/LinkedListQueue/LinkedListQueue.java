package com.wgq.LinkedListQueue;

import com.wgq.MyQueue.Queue;

/**
 * 优化之前写过的链表   添加尾指针   利用优化后的链表 实现队列
 * 因为对链表的头部插入删除都很简单，而对链表尾部的删除不简单，插入很简单
 * 所以 头部作为队首   尾部作为队尾（插入简单O（1））
 */
public class LinkedListQueue<E> implements Queue<E>{

    private class Node{
        public E e;
        public Node next;

        public Node(E e,Node next){
            this.e =e;
            this.next = next;
        }
        public Node(E e){this(e,null);}
        public Node(){this(null,null);}
    }

    private Node head,tail;//头节点   尾节点
    private int size;
    public LinkedListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    //对于链表实现的队列  入队操作在链表尾部进行
    @Override
    public void enqueue(E e) {
        if(tail == null){//说明  head也是空 链表为空
            tail = new Node(e);
            head = tail;
        }else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    //出队操作  从链表头部出队
    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("队列为空");
        }
        Node delNode = head;
        head = head.next;
        delNode.next = null;
        if(head == null){//考虑队列中只有一个元素的情况
            tail = null;
        }
        size--;
        return delNode.e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("队列为空111");
        }
        return tail.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("LinkedListQueue:\n");
        res.append("head:[");
        Node current = head;
        while(current!=null){
            res.append(current.e+"-->");
            current = current.next;
        }
        res.append("NULL] tail");
        return res.toString();
    }
}
