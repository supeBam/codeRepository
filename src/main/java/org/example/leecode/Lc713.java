package org.example.leecode;

/**
 * https://leetcode.cn/problems/subarray-product-less-than-k/description/
 * 滑动窗口练习
 */
public class Lc713 {

    public static void main(String[] args) {

    }

    public int numSubarrayProductLcssThanK(int[] nums, int k) {
        if (k < 1){
            return 0;
        }
        int ans = 0;
        int c = 1;
        int l = 0;
        int n = nums.length;
        for (int r = 0; r < n; r++){
            c *= nums[r];
            while(c >= k){
                c /= nums[l];
                l++;
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
