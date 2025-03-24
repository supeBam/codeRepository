package org.example.leecode;

/**
 * https://leetcode.cn/problems/minimum-size-subarray-sum/
 */
public class Lc209 {

    public static void main(String[] args) {
        Lc209 le209 = new Lc209();
        System.out.println(le209.minSubArrayLcn(4, new int[]{1, 4, 4}));
    }

    public int minSubArrayLcn(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        int l = 0;
        int n = nums.length;
        for (int r = 0; r < n; r++){
            sum += nums[r];
            // while(sum - nums[l] >= target){
            //     sum -= nums[l];
            //     l++;
            // }
            // if(sum >= target){
            //     ans = Math.min(ans, r - l + 1);
            // }
            while(sum >= target){
                ans = Math.min(ans, r - l + 1);
                sum -= nums[l];
                l++;
            }
        }
        return ans <= n ? ans : 0;
    }
}
