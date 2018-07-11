package com.wgq.Array;

public class TestMyArray {
    public static void main(String[] args){
        MyArray<Integer> array = new MyArray<>(4);
        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        System.out.println(array);
        array.addLast(5);
        System.out.println(array);
        array.removeLast();
        System.out.println(array);
    }
}
