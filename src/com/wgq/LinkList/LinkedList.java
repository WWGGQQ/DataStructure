package com.wgq.LinkList;

public class LinkedList<E> {

    /**
     * 内部类
     * 链表中的 一个节点
     */
    private class Node{
        public Node next;//下一个节点的地址
        public E e;//节点中的真实数据

        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }
        public Node(E e){ this(e,null); }
        public Node(){ this(null,null); }
        @Override
        public String toString(){ return e.toString(); }
    }

    private Node dummyHead;//使用虚拟头结点
    private int size;
    public LinkedList(){
        dummyHead = new Node();
        size = 0;
    }

    //获取链表中的元素个数
    public int getSize(){
        return size;
    }
    //链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }


    //在链表中间index（从0开始，起到一个索引的作用）的位置添加一个元素
    //在日常使用中不常用
    public void add(int index,E e){
        if(index<0||index>size){
            throw new IllegalArgumentException("index不合法");
        }

        Node pre = dummyHead;
        for(int i=0;i<index;i++){
            pre = pre.next;
        }
        Node node = new Node(e);
        node.next = pre.next;
        pre.next = node;
        size++;

    }
    //在链表头添加元素
    public void addFirst(E e){
        add(0,e);
    }
    //向链表的末尾添加元素
    public void addLast(E e){
        add(size,e);
    }

    //获得链表中的index的位置的元素
    public E get(int index){
        if(index<0||index>=size){
            throw new IllegalArgumentException("index不合法");
        }
        Node current = dummyHead.next;
        for(int i=0;i<index;i++){
            current = current.next;
        }
        return current.e;
    }

    //获得链表头的元素
    public E getFirst(){
        return get(0);
    }

    //获得链表尾部的元素
    public E getLast(){
        return get(size-1);
    }

    //修改链表中的index位置的节点的值
    public void set(int index,E e){
        if(index<0||index>=size){
            throw new IllegalArgumentException("index 不合法");
        }
        Node current = dummyHead.next;
        for(int i=0;i<index;i++){
            current = current.next;
        }
        current.e = e;
    }
    //查看链表中是否有元素e
    public boolean contains(E e){
        Node current = dummyHead.next;
        while(current!=null){
            if(current.e .equals(e)){
                return true;
            }
        }
        return false;
    }

    //删除链表中的index位置的元素
    public E delete(int index){
        if(index<0||index>=size){
            throw new IllegalArgumentException("index索引不合法");
        }
        //找到 待删除元素之前的元素
        Node pre = dummyHead;
        for(int i=0;i<index;i++){
            pre = pre.next;
        }
        Node retNode = pre.next;
        pre.next = retNode.next;
        retNode.next = null;
        return retNode.e;
    }
    //删除链表头的元素
    public E deleteFirst(){
        return delete(0);
    }
    //删除链表尾部的元素
    public E deleteLast(){
        return delete(size-1);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("LinkedList:\n");
        res.append(String.format("Size:%d   \n",getSize()));
        res.append("head:   ");
        Node current = dummyHead.next;
        while(current!=null){
            res.append(current.e+"->");
            current = current.next;
        }
        res.append("NULL");
        size--;
        return res.toString();
    }
}


/*
链表的时间复杂度分析
   添加操作
      addLast(e)   O(n)  必须从头开始遍历
      addFirst(e)  O(1)
      add(index,e)       O(n/2)  ---> O(n)
   删除操作
      removeLast()  O(n)
      removeFirst()  O(1)
      remove(index)   O(n/2)   --> O(n)

    修改操作
       set(index,e)-->需要从头遍历   O（n）
 */