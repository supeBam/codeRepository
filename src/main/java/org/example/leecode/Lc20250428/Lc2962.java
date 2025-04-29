package org.example.leecode.Lc20250428;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc2962 {

    public static void main(String[] args) {
        System.out.println(new Lc2962().countSubarrays(new int[]{28, 5, 58, 91, 24, 91, 53, 9, 48, 85, 16, 70, 91, 91, 47, 91, 61, 4, 54, 61, 49}, 1));
    }

    public long countSubarrays(int[] nums, int k) {

        int n = nums.length;
        long ans = 0;
        int maxValue = -1;
        for (int num : nums) {
            if (num > maxValue) {
                maxValue = num;
            }
        }
        // 最大值个数
        int maxCount = 0;

        int l = 0;
        for (int r = 0; r < n; r++) {
            if (nums[r] == maxValue) {
                maxCount++;
            }
            while (l <= r && maxCount >= k) {
                if (nums[l] == maxValue) {
                    maxCount--;
                }
                l++;
            }
            ans += l;
        }
        return ans;
    }
}
