package com.wgq.LinkedListStack;

import com.wgq.MyStack.ArrayStack;
import com.wgq.MyStack.Satck;

import java.util.Random;

public class LinkAndArrayStack {
    public static void main(String[] args){
        int opCount = 100000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        MyLinkedListStack<Integer> linkedListStack = new MyLinkedListStack<>();
        double timeArray = testStack(arrayStack,opCount);
        double timeLinkLisk = testStack(linkedListStack,opCount);
        System.out.println("timeArray:"+timeArray+"s");
        System.out.println("timeLinkLisk"+timeLinkLisk+"s");

        /**
         * 结果:
         * timeArray:0.030017591s
           timeLinkLisk0.011773171s
           原因：
             因为对于数组栈来说 ，还有一个扩容的操作**所以会慢一些
             注意  链表中有一个new Node()的操作 ***也很耗时
         */
    }

    /**
     * 测试动态数组实现的栈和链表实现的栈的性能
     * @return
     */
    public static double testStack(Satck<Integer> stack,int opCount){
        Random random = new Random();
        long startTime = System.nanoTime();
        for(int i=0;i<opCount;i++){
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for(int i=0;i<opCount;i++){
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }

}
