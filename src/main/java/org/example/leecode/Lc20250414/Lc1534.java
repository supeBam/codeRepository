package org.example.leecode.Lc20250414;

import java.util.Arrays;

public class Lc1534 {
    public static void main(String[] args) {
        Lc1534 lc1534 = new Lc1534();
        System.out.println(lc1534.countGoodTriplets2(new int[]{3, 0, 1, 1, 9,7}, 7, 2, 3));
    }

    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int ans = 0;
        // 枚举 a
        for (int i = 0; i < arr.length - 2; i++) {
            // 枚举 b
            for (int j = i + 1; j < arr.length - 1; j++) {
                // 枚举 c
                for (int k = j + 1; k < arr.length; k++) {
                    if (Math.abs(arr[i] - arr[j]) <= a && Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    // 优化
    // arr[i] -
    public int countGoodTriplets2(int[] arr, int a, int b, int c) {
        int ans = 0;
        return ans;
    }
}
