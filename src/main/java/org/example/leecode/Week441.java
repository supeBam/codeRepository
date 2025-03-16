package org.example.leecode;

import java.util.*;

public class Week441 {

    public static void main(String[] args) {
        Week441 week441 = new Week441();
        int[] ints = {1, 2, 3, 4, 5};
        System.out.println(week441.maxSum(ints));

        int[] nums = {14,14,4,2,19,19,14,19,14};
        int[] queries = {2,4,8,6,3};
        System.out.println(week441.solveQueries(nums, queries));
    }

//    第一题
    public int maxSum(int[] nums) {
        int ans = 0;
        return ans;
    }


//    第二题
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        // 每个值对应的索引集合位置  例如：1：[0,2,4] 1出现在0，2，4位置上
        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        int n = nums.length;
        int[] maxInt = new int[n];
        Arrays.fill(maxInt, Integer.MAX_VALUE);


        for (int i = 0; i < n; i++) {
            int num = nums[i];
            // 判断是否存在
            if (indexMap.containsKey(num)) {
                // 存在
                List<Integer> indexList = indexMap.get(num);
                indexList.add(i);
                indexMap.put(num, indexList);
                // 存在的话只需要看当前索引的，前后两个位置相同的值
                // 当前值 在集合内 的索引位置，按顺序添加，新的值肯定在list最后
                int size = indexList.size();
                int curIndexList = size - 1;
                int curIndex = indexList.get(size - 1);
                //前一个相同元素的位置
                int preIndex = indexList.get(curIndexList - 1);
                int preDistance = Math.abs(curIndex - preIndex);
                //后一个索引的距离
                int nextIndex = indexList.get(0);
                int nextDistance = Math.abs(n - curIndex + nextIndex);
                // 更新当前索引最短距离
                maxInt[i] = Math.min(preDistance, nextDistance);
                // 判断前一个和后一个最短距离需不需要更新
                maxInt[preIndex] =  Math.min(maxInt[preIndex], preDistance);
                maxInt[nextIndex] =  Math.min(maxInt[nextIndex], nextDistance);
            }
            // 不存在
            else  {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                indexMap.put(num, list);
            }
        }


        List<Integer> list = new ArrayList<>();
        for (int query : queries) {
            if (maxInt[query] == Integer.MAX_VALUE) {
                list.add(-1);
                continue;
            }
            list.add(maxInt[query]);
        }
        return list;
    }
}
