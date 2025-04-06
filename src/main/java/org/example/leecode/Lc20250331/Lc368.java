package org.example.leecode.Lc20250331;

import java.util.*;

public class Lc368 {
    public static void main(String[] args) {
        Lc368 lc368 = new Lc368();
        List<Integer> list = lc368.largestDivisibleSubset(new int[]{1, 2, 3});
        System.out.println(list);
    }

    int[] used;
    int[] from;

    public List<Integer> largestDivisibleSubset(int[] nums) {
        // 排序，保证子序列中 右边的元素是左边元素的倍数
        Arrays.sort(nums);
        int n = nums.length;
        // 记录 元素是否 被使用过, 以及累计次数
        used = new int[n];
        // 记录上一个索引位置
        from = new int[n];
        Arrays.fill(from, -1);
        // 当前最大子序列 大小
        int maxSize = 0;
        // 更新最长子序列的最后一个下标
        int maxIndex = 0;
        // 枚举 子序列
        for (int i = 0; i < n; i++) {
            // 返回的是满足条件的 当前的子序列大小
            if (dfs(nums, i) > maxSize) {
                maxSize = dfs(nums, i);
                maxIndex = i;
            }
        }
        // 构造结果集
        List<Integer> res = new ArrayList<>(maxSize);
        for (int i = maxIndex; i >= 0; i = from[i]) {
            res.add(nums[i]);
        }
        return res;
    }

    public int dfs(int[] nums, int index) {
        // 被使用过，返回他的总共的次数
        if (used[index] > 0) {
            return used[index];
        }
        // 满足倍数条件的元素个数
        int res = 0;
        // 枚举
        for (int i = 0; i < index; i++) {
            // 不满足 倍数条件
            if (nums[index] % nums[i] != 0) {
                continue;
            }
            // 满足
            int time = dfs(nums, i);
            if (time > res) {
                res = time;
                // 记录当前的上一个索引位置
                from[index] = i;
            }
        }
        // 如果没有，就只有当前子集
        return used[index] = res + 1;
    }
}
