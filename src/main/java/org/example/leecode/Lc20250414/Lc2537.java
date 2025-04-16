package org.example.leecode.Lc20250414;

import java.util.HashMap;
import java.util.Map;

public class Lc2537 {
    public static void main(String[] args) {
        Lc2537 lc2537 = new Lc2537();
        System.out.println(lc2537.countGood(new int[]{3, 1, 4, 3, 2, 2, 4}, 2));
    }

    public long countGood(int[] nums, int k) {
        long ans = 0;
        Map<Integer, Integer> map = new HashMap<>();

        int pair = 0;
        for (int r = 0, l = 0; r < nums.length; r++) {
            Integer c = map.getOrDefault(nums[r], 0);
            // 组合数学， C (n , 2) 从n个数中任意取2个
            pair += c;
            map.put(nums[r], c + 1);

            // 满足对数
            while (pair >= k && l <= r) {
                ans += nums.length - r;
                map.put(nums[l], map.get(nums[l]) - 1);
                pair -= map.get(nums[l]);
                l++;
            }
        }
        return ans;
    }
}
