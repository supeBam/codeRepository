package org.example.leecode.Lc20250407;


import java.util.*;

//https://leetcode.cn/problems/partition-equal-subset-sum/?envType=daily-question&envId=2025-04-07
public class Lc416 {

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int j = sum / 2;
        int n = nums.length;

        // 记忆化
        int[][] memo = new int[n][sum / 2 + 1];
        for (int[] ints : memo) {
            Arrays.fill(ints, -1);
        }
        return dfs(n - 1, j, nums, memo);
    }

    public boolean dfs(int i, int j, int[] nums, int[][] memo) {
        if (i < 0) {
            return j == 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j] == 1;
        }

        boolean res = j >= nums[i] && dfs(i - 1, j - nums[i], nums, memo) || dfs(i - 1, j, nums, memo);
        memo[i][j] = res ? 1 : 0;
        return res;
    }


}
