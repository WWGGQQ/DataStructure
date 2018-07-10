package com.wgq.Array;

/**
 * 基于java的数组 进行二次封装
 */
//增加泛型
public class MyArray<E>{
    private E[] data; //底层数组
    private int size; //数组中 有效元素的 个数 （指向第一个没有存放数据的索引）

    /**
     * 构造函数，传入数组的容量构造数组
     * @param capacity
     */
    public MyArray(int capacity){
        this.data = (E[]) new Object[capacity];//java不支持 泛型数组（new E[] -->错误）    需要强转一下
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
    public void addLast(E e) {
        add(size,e);
    }
    //向靠头添加一个元素
    public void addFirst(E e){
        add(0,e);
    }
    //向数组的指定位置添加一个元素
    public void add(int index,E e){
        if(index<0 || index > size){
            throw new IllegalArgumentException("索引不合法");
        }
        addCapacity();
        for(int i=size-1;i>index;i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }
    //获取index位置的元素
    public E get(int index){
        if(index<0||index>=size){
            throw new IllegalArgumentException("index 不合法");
        }
        return data[index];
    }
    //修改index位置的元素
    public void set(int index,E e){
        if(index<0||index>=size){
            throw new IllegalArgumentException("index 不合法");
        }
        data[index] = e;
    }

    //查看数组中是否存在某一个元素
    public boolean contains(E e){
        for (int i=0;i<size;i++){
            if(data[i] .equals(e) ){//因为  加了泛型  所以不能用==了   需要用equals
                return true;
            }
        }
        return false;
    }

    //查看数组中元素e所在的索引（第一个出现的），不存在返回-1
    public int find(E e){
        for (int i=0;i<size;i++){
            if(data[i] .equals(e) ){
                return i;
            }
        }
        return -1;
    }

    //从数组中删除元素   并返回删除的元素
       //对于index之后的每一个元素  都向前移动一位
    public E remove(int index){
        E removeElement = data[index];
        if(index<0||index>=size){
            throw new IllegalArgumentException("没有这个元素，index不合法");
        }
        for (int i=index+1;i<size;i++){
            data[i-1] = data[i];
        }
        size--;
        removeCapacity();
        return removeElement;
    }

    //s删除第一个元素
    public E removeFirst(){
        return remove(0);
    }
    //删除 最后一个元素
    public E removeLast(){
        return remove(size-1);
    }

    //从数组中删除 指定元素  第一个碰到的
    public boolean removeElement(E e){
        int index = find(e);
        if(index != -1){
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 动态数组的 实现
     *
     *   扩容   添加元素前 判断
     *    建立一个新的数组  将原来数组的 所有元素复制进来
     */
    private void addCapacity(){
       if(size == data.length){
           E[] newData = (E[])new Object[size*2];
           for (int i=0;i<size;i++){
               newData[i] = data[i];
           }
           data = newData;
       }
    }
    //缩小容量  删除元素后 size的值-1后   判断
    private void removeCapacity(){
        if(size<=data.length/2 && data.length/2!=0){
            E[] newData = (E[])new Object[data.length/2];
            for (int i=0;i<newData.length;i++){
                newData[i] = data[i];
            }
            data = newData;
        }
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
