package org.example.leecode.Lc20250512;

public class Lc2894 {
    public int differenceOfSums(int n, int m) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (i % m == 0) {
                ans -= i;
            }else {
                ans += i;
            }
        }
        return ans;
    }
}
