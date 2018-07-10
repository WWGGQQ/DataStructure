package com.wgq.Array;

public class TestMyArray {
    public static void main(String[] args){
        MyArray myArray = new MyArray(4);
        myArray.addLast(1);
        myArray.addLast(2);
        myArray.addLast(3);
        myArray.addLast(4);
        System.out.println(myArray);

        myArray.remove(0);
        System.out.println(myArray);
    }
}
