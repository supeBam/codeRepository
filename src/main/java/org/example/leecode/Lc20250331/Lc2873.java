package org.example.leecode.Lc20250331;

public class Lc2873 {


    //下标三元组 (i, j, k) 的值等于 (nums[i] - nums[j]) * nums[k]
    //如果所有满足条件的三元组的值都是负数，则返回 0

    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[] lMax = new int[n];
        int[] rMax = new int[n];
        for (int i = 0; i < n; i++) {
            lMax[i] = i == 0 ? nums[i] : Math.max(nums[i], lMax[i - 1]);
        }
        for (int i = n - 1; i >= 0; i--) {
            rMax[i] = i == n - 1 ? nums[i] : Math.max(nums[i], rMax[i + 1]);
        }

        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            int lmax = lMax[i - 1];
            int rmax = rMax[i + 1];
            if (lmax - cur < 0) {
                continue;
            }
            ans = Math.max(ans, (lmax - cur) * rmax);
        }
        return ans;
    }
}
