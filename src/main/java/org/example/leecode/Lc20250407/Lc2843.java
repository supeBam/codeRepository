package org.example.leecode.Lc20250407;

import java.util.HashSet;
import java.util.Set;

public class Lc2843 {
    public static void main(String[] args) {
        Lc2843 lc2843 = new Lc2843();
        System.out.println(lc2843.countSymmetricIntegers(1200, 1230));
    }

    public int countSymmetricIntegers(int low, int high) {
        int ans = 0;
        for (int i = low; i < high; i++) {
            String s = String.valueOf(i);
            int n = s.length();
            if (n % 2 != 0) {
                continue;
            }
            int mid = n / 2;
            int v = 0;
            for (int j = 0; j < mid; j++) {
                v += (s.charAt(j) - '0');
            }
            for (int j = mid; j < n; j++) {
                v -= (s.charAt(j) - '0');
            }
            if (v == 0) {
                ans++;
            }
        }
        return ans;
    }
}
