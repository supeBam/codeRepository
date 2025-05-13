package org.example.leecode.Lc20250512;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc3335 {
    /**
     * f(n) = f(n - 1)     2 <= n <= 25
     * f(0) = f(25)
     * f(1) = f(25) + f(0)
     */

    public int lengthAfterTransformations(String s, int t) {
        int MOD = 1_000_000_000 + 7;
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
        }


        for (int i = 0; i < t; i++) {
            int[] dp = new int[26];
            dp[0] = cnt[25];
            dp[1] = (cnt[0] + cnt[25]) % MOD;
            for (int j = 2; j < 26; j++) {
                dp[j] = cnt[j - 1];
            }
            cnt = dp;
        }
        // 计算结果
        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            ans = (ans + cnt[i]) % MOD;
        }
        return ans;
    }

    public static void main(String[] args) {
        Lc3335 lc3335 = new Lc3335();
        // System.out.println(lc3335.lengthAfterTransformations("abcyy", 2));
        // System.out.println(lc3335.lengthAfterTransformations("azbk", 1));
        // System.out.println(lc3335.lengthAfterTransformations("cu", 5));
        System.out.println(lc3335.lengthAfterTransformations("jqktcurgdvlibczdsvnsg", 7517));
    }
}
