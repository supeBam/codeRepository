package org.example.leecode.Lc20250421;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lc2845 {
    /**
     * nums[i] % modulo == k
     * cnt % modulo == k 。
     * 子数组
     * <p>
     * 例子
     * [3,1,9,6] , m = 3, k = 0
     * 题目需要满足 子数组中nums[i] % m == k 且 cnt % m == k
     * 所以可以转换为：[1, 0, 1, 1] 1 代表 满足
     * 问题转换为 需要找出 子数组中 元素和 % m == k 的个数
     * <p>
     * 元素和 通常使用 前缀和数组 技巧
     * [1, 0, 1, 1] 转为 前缀和数组 [0, 1, 1, 2, 3]
     * 题目转换为 [l, r] , 0 <= l < r <= n 满足 (sum[r] - sum[l]) % m == k 的个数
     * <p>
     * 题目中：0 <= k < modulo  所以 (sum[r] - sum[l]) % m == k % m
     * 移向 ： (sum[r] - k) % m == sum[l] % m
     * <p>
     * 枚举 r， 计算 s[l] % m 的个数
     */

    public static void main(String[] args) {
        Lc2845 lc2845 = new Lc2845();
        System.out.println(lc2845.countInterestingSubarrays(Arrays.asList(3, 1, 9, 6), 3, 0));
    }

    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + (nums.get(i) % modulo == k ? 1 : 0);
        }
        Map<Integer, Integer> map = new HashMap<>();
        long ans = 0;
        for (int s : sum) {
            if (s >= k) {
                ans += map.getOrDefault((s - k) % modulo, 0);
            }
            map.put(s % modulo, map.getOrDefault(s % modulo, 0) + 1);
        }
        return ans;
    }

    public long countInterestingSubarrays2(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + (nums.get(i) % modulo == k ? 1 : 0);
        }

        long ans = 0;
        // Map<Integer, Integer> map = new HashMap<>();
        // s[l] % m  <= min(n, m - 1)
        int[] cnt = new int[Math.min(n + 1, modulo)];
        for (int s : sum) {
            if (s >= k) {
                // ans += map.getOrDefault((s - k) % modulo, 0);
                ans += cnt[(s - k) % modulo];
            }
            // map.put(s % modulo, map.getOrDefault(s % modulo, 0) + 1);
            cnt[s % modulo]++;
        }
        return ans;
    }
}
