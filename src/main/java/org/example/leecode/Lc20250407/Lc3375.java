package org.example.leecode.Lc20250407;

import java.util.*;

public class Lc3375 {
    public static void main(String[] args) {
        Lc3375 lc3375 = new Lc3375();
        System.out.println(lc3375.minOperations(new int[]{5, 2, 5, 4, 5}, 2));
    }

    public int minOperations(int[] nums, int k) {
        int ans = 0;
        int[] ints = new int[101];

        for (int num : nums) {
            if (num < k) {
                return -1;
            }
            ints[num]++;
        }

        for (int i = 1; i < ints.length; i++) {
            if (ints[i] >= 1 && i != k) {
                ans++;
            }
        }
        return ans;
    }

    // 优化
    public int minOperations2(int[] nums, int k) {
        // 获取最小值
        int min = Arrays.stream(nums).min().getAsInt();
        if (k > min) {
            return -1;
        }
        // 去重后个数
        long count = Arrays.stream(nums).distinct().count();
        // 判断 nums 中是否 有和 k 相等的值，相等则 -1，变换次数少一次
        return (int) count - (min == k ? 1 : 0);
    }

}
