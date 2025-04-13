package org.example.leecode.Lc20250407;

import com.sun.org.apache.xpath.internal.operations.Mod;

// https://leetcode.cn/problems/count-good-numbers/?envType=daily-question&envId=2025-04-13
public class Lc1922 {
    public static void main(String[] args) {
        Lc1922 lc1922 = new Lc1922();
        System.out.println(lc1922.countGoodNumbers(4));
    }

    private static final int MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {
        long o = pow(5, (n + 1) / 2);
        long j = pow(4, n / 2);
        return (int) (o * j % MOD);
    }

    private long pow(long x, long n) {
        // 结果
        long res = 1;
        // 二进制
        while (n > 0) {
            // 最低位
            if ((n & 1) == 1) {
                // 低位为1 则乘幂  幂为x
                res = res * x % MOD;
            }
            // 快速幂 不断对自己平方
            x = x * x % MOD;
            // 右移
            n = n >> 1;
        }
        return res;
    }
}
