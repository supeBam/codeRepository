package org.example.leecode.Lc20250512;

public class Lc2016 {
    // 方法一：暴力
    public int maximumDifference(int[] nums) {
        int ans = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    ans = Math.max(ans, nums[j] - nums[i]);
                }
            }
        }
        return ans;
    }


    // 方法二: 一次遍历
    public int maximumDifference2(int[] nums) {
        int ans = -1;
        int n = nums.length;
        int min = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > min) {
                ans = Math.max(ans, nums[i] - min);
            } else {
                min = nums[i];
            }
        }
        return ans;
    }
}
