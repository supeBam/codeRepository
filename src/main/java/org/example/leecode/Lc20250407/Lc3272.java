package org.example.leecode.Lc20250407;

import cn.hutool.core.text.StrBuilder;

import java.util.*;

public class Lc3272 {
    public static void main(String[] args) {
        Lc3272 lc3272 = new Lc3272();
        // System.out.println(lc3272.countGoodIntegers(2, 5));
        System.out.println(lc3272.countGoodIntegers(2, 1));
    }

    public long countGoodIntegers(int n, int k) {
        int start = (int) Math.pow(10, (n - 1) / 2);
        int end = start * 10;
        // 组合数学没搞懂
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        long ans = 0;
        Set<String> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            // 枚举回文数 左边
            String s = String.valueOf(i);
            // 反转后 补充右半边， %2  如果是偶数则直接拼接， 奇数取 / 的互补
            s += new StringBuilder(s).reverse().substring(n % 2);
            // 不能被 k 整除
            if (Long.parseLong(s) % k != 0){
                continue;
            }
            // 重排列， 去掉重复组合
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            if (!set.add(new String(chars))) {
                continue;
            }

            // 组合数学没搞懂
            int[] cnt = new int[10];
            for (char c : chars) {
                cnt[c - '0']++;
            }
            int res = (n - cnt[0]) * factorial[n - 1];
            for (int c : cnt) {
                res /= factorial[c];
            }
            ans += res;
        }
        return ans;
    }
}
