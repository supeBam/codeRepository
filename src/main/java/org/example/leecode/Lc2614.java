package org.example.leecode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/prime-in-diagonal/?envType=daily-question&envId=2025-03-18
 */
public class Lc2614 {
    public static void main(String[] args) {
    }

    //    方法一：枚举每个树
    public int diagonalPrime(int[][] nums) {
        int max = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || i + j == n - 1) {
                    if (isPrime(nums[i][j])) {
                        max = Math.max(max, nums[i][j]);
                    }
                }
            }
        }
        return max;
    }

    // 判断质数
    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    //埃氏筛
    public boolean[] countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        // 从 2 开始枚举到 sqrt(n)。
        for (int i = 2; i * i < n; i++) {
            // 如果当前是素数
            if (isPrim[i]) {
                // 就把从 i*i 开始，i 的所有倍数都设置为 false。
                for (int j = i * i; j < n; j += i) {
                    isPrim[j] = false;
                }
            }
        }
        return isPrim;
    }
}
