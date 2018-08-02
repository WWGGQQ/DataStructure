package com.wgq1.heap;

import java.util.Random;

public class TestHeap {
    public static void main(String[] args){
        Random random = new Random();
        Integer[] nums = new Integer[1000000];
        for(int i=0;i<nums.length;i++){
            nums[i] = random.nextInt(Integer.MAX_VALUE);
        }
        double time1 = testHeap(nums,false);
        System.out.println("WithOut Heapify:"+time1+"s");

        double time2 = testHeap(nums,true);
        System.out.println("With Heapify:"+time2+"s");
    }

    public static double testHeap(Integer[] testDate,boolean isHeapify){
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap ;
        if(isHeapify){
            maxHeap = new MaxHeap<>(testDate);
        }else{
            maxHeap = new MaxHeap<>();
            for(int i=0;i<testDate.length;i++){
                maxHeap.add(testDate[i]);
            }
        }

        Integer[] nums = new Integer[testDate.length];
        for(int i=0;i<testDate.length;i++){
            nums[i] = maxHeap.extractMax();
        }
        for(int i=0;i<nums.length-1;i++){
            if(nums[i] < nums[i+1]){
                throw new IllegalArgumentException("堆创建失败");
            }
        }
        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000000.0;
    }
}
