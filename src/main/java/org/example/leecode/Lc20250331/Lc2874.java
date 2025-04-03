package org.example.leecode.Lc20250331;

public class Lc2874 {

    public long maximumTripletValue(int[] nums) {
        long ans = 0;
        long lmax = nums[0];
        long sub = 0;
        for (int i = 2; i < nums.length; i++) {
            sub = Math.max(sub, lmax - nums[i - 1]);
            lmax = Math.max(lmax, nums[i - 1]);
            ans = Math.max(ans, sub * nums[i]);
        }
        return ans;
    }
}
