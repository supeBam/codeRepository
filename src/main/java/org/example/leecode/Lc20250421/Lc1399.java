package org.example.leecode.Lc20250421;

public class Lc1399 {
    public int countLargestGroup(int n) {
        int[] cnt = new int[40];
        int max = 0;
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int v = i;
            int sum = 0;
            while (v > 0) {
                sum += v % 10;
                v /= 10;
            }
            cnt[sum]++;
            if (max < cnt[sum]) {
                max = cnt[sum];
                ans = 1;
            } else if (max == cnt[sum]) {
                ans++;
            }
        }

        return ans;
    }
}
