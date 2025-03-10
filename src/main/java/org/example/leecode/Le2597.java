package org.example.leecode;


import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/the-number-of-beautiful-subsets/description/
public class Le2597 {

    int ans = -1; // 去除空子集

    public static void main(String[] args) {
        Le2597 le2597 = new Le2597();
        int i = le2597.beautifulSubsets(new int[]{1, 2, 3, 4, 5}, 3);
        System.out.println(i);
    }

    public int beautifulSubsets(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        dfs(0, nums, k, map);
        return ans;
    }

    //回溯方法一 答案出发，所有的结果都选一次，满足条件才添加
    public int dfs(int index, int[] nums, int k, Map<Integer, Integer> map) {
        ans++;
        // 递归结束条件
        if (index == nums.length) {
            return ans;
        }
        for (int i = index; i < nums.length; i++) {
            // 临时的值
            int item = nums[i];
            // 判断是否满足条件添加进去
            if (map.getOrDefault(item - k, 0) == 0 && map.getOrDefault(item + k, 0) == 0) {
                // 计数
                map.merge(item, 1, Integer::sum);
                dfs(i + 1, nums, k, map);
                // 恢复现场，扣除原来的计数
                map.merge(item, -1, Integer::sum);
            }
        }
        return ans;
    }

    //回溯方法二 目标出发，每个元素都有两种选择，要么选，要么不选，满足条件就放进去
    public void dfs2(int index, int[] nums, int k, Map<Integer, Integer> map) {
        // 递归结束条件
        if (index == nums.length) {
            ans++;
            return;
        }
        // 不选的情况
        dfs2(index + 1, nums, k, map);

        int item = nums[index];
        // 选的情况 （需要满足条件）
        if (map.getOrDefault(item - k, 0) == 0 && map.getOrDefault(item + k, 0) == 0) {
            // 计数
            map.merge(item, 1, Integer::sum);
            dfs(index + 1, nums, k, map);
            // 恢复现场，扣除原来的计数
            map.merge(item, -1, Integer::sum);

        }
    }
}
