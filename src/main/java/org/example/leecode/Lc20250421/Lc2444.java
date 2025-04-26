package org.example.leecode.Lc20250421;

// https://leetcode.cn/problems/count-subarrays-with-fixed-bounds/description/?envType=daily-question&envId=2025-04-26
public class Lc2444 {

    public long countSubarrays(int[] nums, int minK, int maxK) {
        // 分别 记录 minK 和 maxK 的位置，以及不在 [minK, maxK] 范围内的位置
        int minIndex = -1;
        int maxIndex = -1;
        int lo = -1;

        long ans = 0;
        // 枚举 右端点， 当 min 和 max 都存在时， 计算个数 [lo + 1, min(minIndex, maxIndex)]
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] == minK) {
                minIndex = r;
            }
            if (nums[r] == maxK) {
                maxIndex = r;
            }
            // 不在 [minK, maxK] 范围内的索引
            if (nums[r] < minK || nums[r] > maxK) {
                lo = r;
            }
            // 如果minIndex 和 maxIndex 都存在，则计算个数
            if (minIndex != -1 && maxIndex != -1) {
                // 需要使用 max（min(minIndex, maxIndex) - lo, 0） 来把 lo 位于 minIndex 和 maxIndex 中间排除，在中间则证明lo右边没有同时存在 minK和maxK
                ans += Math.max(Math.min(minIndex, maxIndex) - lo, 0);
            }
        }

        return ans;
    }
}
