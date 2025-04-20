package org.example.leecode.Lc20250414;

import java.util.Arrays;

// https://leetcode.cn/problems/count-the-number-of-fair-pairs/?envType=daily-question&envId=2025-04-19
public class Lc2563 {

    /**
     * 0 <= i < j < n，且
     * lower <= nums[i] + nums[j] <= upper
     * <p>
     * lower - nums[j] <= nums[i] <= upper - nums[j]
     */
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long ans = 0;

        // 枚举 右端点
        for (int j = 0; j < nums.length; j++) {
            int l = lowerBound(nums, lower - nums[j], j);
            int r = lowerBound(nums, upper - nums[j] + 1, j);
            // 前缀和相减
            ans += r - l;
        }
        return ans;
    }

    public int lowerBound(int[] nums, int target, int r) {
        int l = -1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return r;
    }
}
