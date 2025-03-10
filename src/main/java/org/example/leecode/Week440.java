package org.example.leecode;

import java.util.*;

public class Week440 {

    public static void main(String[] args) {
        Week440 week440 = new Week440();
        // 第一题
//        int[] fruits = {3,6,1};
//        int[] fruits = {1, 4};
        int[] fruits = {4, 2, 5};
//        int[] fruits = {3, 6, 1};
//        int[] baskets = {6,4,7};
//        int[] baskets = {8, 1};
        int[] baskets = {3, 5, 4};
//        int[] baskets = {6, 4, 7};

//        System.out.println(week440.numOfUnplacedFruits(fruits, baskets));

//        第二题
        int[] nums1 = {4, 2, 1, 5, 3};
//        int[] nums1 = {2,2,2,2};
//        int[] nums2 = {3, 1, 2, 3};
        int[] nums2 = {10, 20, 30, 40, 50};
//        int k = 1;
        int k = 2;
        System.out.println(Arrays.toString(week440.findMaxSum(nums1, nums2, k)));
    }

    // 第一题
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        if (n == 1) {
            return fruits[0] > baskets[0] ? 1 : 0;
        }
        int f = 0;
        boolean[] bFlag = new boolean[n];
        for (int fruit : fruits) {
            for (int j = 0; j < n; j++) {
                if (fruit <= baskets[j] && !bFlag[j]) {
                    f++;
                    bFlag[j] = true;
                    break;
                }
            }
        }
        return n - f;
    }


    // 第二题
    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] pairs = new int[n][3];

        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums2[i];
            pairs[i][2] = i;
        }

        // 按照 nums1 的值进行排序
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        // 前缀和数组，存储前 k 个最大的 nums2[j] 值的和
        long[] prefixSum = new long[n];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // 结果数组
        long[] answer = new long[n];
        // 前缀和
        long sum = 0;

        for (int i = 0; i < n; ) {
            // 开始指针
            int start = i;
            // 获取临时值
            int item = pairs[start][0];
            //去除重复的nums1[i]
            while (i < n && pairs[i][0] == item) {
                // pairs[i][2] 代表 给 answer结果数组的指定索引赋值 ，值为前缀和
                answer[pairs[i][2]] = sum;
                i++;
            }
            for (int j = start; j < i; j++) {
                // 获取当前左指针的结果
                int value = pairs[j][1];
                // 求前缀和
                sum += value;
                // 放入最小堆
                minHeap.offer(value);
                // 维护一个大小为k的最小堆
                if (minHeap.size() > k) {
                    // 前缀和去除原来加过的最小值
                    sum -= minHeap.poll();
                }
            }
        }
        return answer;
    }
}
