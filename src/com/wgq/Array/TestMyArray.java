package com.wgq.Array;

public class TestMyArray {
    public static void main(String[] args){
//        MyArray<Integer> myArray = new MyArray(4);
//        myArray.addLast(1);
//        myArray.addLast(2);
//        myArray.addLast(3);
//        myArray.addLast(4);
//        System.out.println(myArray);
//
//        myArray.remove(0);
//        System.out.println(myArray);
        MyArray<User> array = new MyArray<>();
        array.addLast(new User(1,"wwww"));
        array.addLast(new User(2,"GGG"));
        array.addLast(new User(3,"qqq"));
//        System.out.println(array);

        System.out.println(array.contains(new User(2,"GGG")));
    }
}
