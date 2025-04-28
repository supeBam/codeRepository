package org.example.leecode.Lc20250428;

import java.util.ArrayList;
import java.util.List;

public class Lc2302 {


    public static void main(String[] args) {
        Lc2302 lc2302 = new Lc2302();
        System.out.println(lc2302.countSubarrays(new int[]{2, 1, 4, 3, 5}, 10));
        /**
         *  k / r = sum
         */
    }

    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        int sum = 0;
        long ans = 0;
        int l = 0;
        for (int r = 0; r < n; r++) {
            sum += nums[r];

            while ((long) sum * (r - l + 1) >= k) {
                sum -= nums[l];
                l++;
            }
            ans += r - l + 1;

        }
        return ans;
    }

}
