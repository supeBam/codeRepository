package org.example.leecode.Lc20250414;

import java.util.*;

public class Lc2176 {
    public static void main(String[] args) {
        Lc2176 lc2176 = new Lc2176();
        System.out.println(lc2176.countPairs(new int[]{3, 1, 2, 2, 2, 1, 3}, 2));
    }

    // 枚举
    public int countPairs(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j] && ((i * j) % k) == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
