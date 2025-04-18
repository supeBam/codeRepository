package org.example.leecode.Lc20250414;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Lc2364 {
    public static void main(String[] args) {
        Lc2364 lc2364 = new Lc2364();
        System.out.println(lc2364.countBadPairs(new int[]{4, 1, 3, 3}));
    }

    /**
     * j - i != nums[j] - nums[i] 转换为 nums[j] - j != nums[i] - i
     * 可以用 n 的阶乘 减  ( nums[j] - j == nums[i] - i )的个数
     */
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        long ans = (long) n * (n - 1) / 2;
        Map<Integer, Integer> map = new HashMap<>();

        // 枚举 i
        for (int i = 0; i < n; i++) {
            Integer value = map.getOrDefault(nums[i] - i, 0);
            ans -= value;
            map.put(nums[i] - i, value + 1);
        }
        return ans;
    }
}
