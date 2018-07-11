package com.wgq.LoopQueue;

import com.wgq.MyQueue.ArrayQueue;
import com.wgq.MyQueue.Queue;

import java.util.Random;

/**
 * 比较一下   循环队列 和  动态数组队列 的性能差异
 *
 *
 *                                      循环队列        动态数组队列
 *     int getSize();                       O(1)             O(1)

       boolean isEmpty();                   O(1)             O(1)

       void enqueue(E e);                   O(1)均摊         O(1)均摊    入队操作可能触发扩容

       E dequeue();(每次出队所有元素都要前移)O(n)            O(1)均摊    出队操作可能触发缩容

       E getFront();                        O(1)             O(1)
 */


public class LoopAndArrayQueue {
    public static void main(String[] args){
        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue,opCount);
        System.out.println("ArrayQueue,time :"+time1+"s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue,opCount);
        System.out.println("LoopQueue,time :"+time2+"s");

      /*
        结果如下：
        ArrayQueue,time :3.79368432s
        LoopQueue,time :0.013084722s*/
    }

    public static double testQueue(Queue<Integer> queue,int opCount){
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i=0;i<opCount;i++){
            queue.enqueue(random.nextInt());
        }
        for (int i=0;i<opCount;i++){
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000000.0;
    }
}
