package com.wgq.Array;

/**
 * 基于java的数组 进行二次封装
 */
public class MyArray {
    private int[] data; //底层数组
    private int size; //数组中 有效元素的 个数 （指向第一个没有存放数据的索引）

    /**
     * 构造函数，传入数组的容量构造数组
     * @param capacity
     */
    public MyArray(int capacity){
        this.data = new int[capacity];
        this.size = 0;
    }
    //无惨构造函数，默认是10
    public MyArray(){
        this(10);
    }

    //获得数组中有效元素的个数
    public int getSize(){
        return this.size;
    }
    //获得 数组的容量
    public int getCapacity(){
        return data.length;
    }
    //是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //向末尾添加一个元素
    public void addLast(int e) {
        add(size,e);
    }
    //向靠头添加一个元素
    public void addFirst(int e){
        add(0,e);
    }
    //向数组的指定位置添加一个元素
    public void add(int index,int e){
        if(size == data.length){//数组已经满了
            throw new IllegalArgumentException("数组已经满了");
        }
        if(index<0 || index > size){
            throw new IllegalArgumentException("索引不合法");
        }
        for(int i=size-1;i>index;i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }
    //获取index位置的元素
    public int get(int index){
        if(index<0||index>=size){
            throw new IllegalArgumentException("index 不合法");
        }
        return data[index];
    }
    //修改index位置的元素
    public void set(int index,int e){
        if(index<0||index>=size){
            throw new IllegalArgumentException("index 不合法");
        }
        data[index] = e;
    }

    //查看数组中是否存在某一个元素
    public boolean contains(int e){
        for (int i=0;i<size;i++){
            if(data[i] == e){
                return true;
            }
        }
        return false;
    }

    //查看数组中元素e所在的索引（第一个出现的），不存在返回-1
    public int find(int e){
        for (int i=0;i<size;i++){
            if(data[i] == e){
                return i;
            }
        }
        return -1;
    }

    //从数组中删除元素   并返回删除的元素
       //对于index之后的每一个元素  都向前移动一位
    public int remove(int index){
        int removeElement = data[index];
        if(index<0||index>=size){
            throw new IllegalArgumentException("没有这个元素，index不合法");
        }
        for (int i=index+1;i<size;i++){
            data[i-1] = data[i];
        }
        size--;
        return removeElement;
    }

    //s删除第一个元素
    public int removeFirst(){
        return remove(0);
    }
    //删除 最后一个元素
    public int removeLast(){
        return remove(size-1);
    }

    //从数组中删除 指定元素  第一个碰到的
    public boolean removeElement(int e){
        int index = find(e);
        if(index != -1){
            remove(index);
            return true;
        }
        return false;
    }

    //拼接字符串显示数组的信息
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n",size,data.length));
        res.append('[');
        for (int i=0;i<size;i++){
            res.append(data[i]);
            if(i!=size-1){
                res.append(',');
            }
        }
        res.append(']');
        return res.toString();
    }


}
