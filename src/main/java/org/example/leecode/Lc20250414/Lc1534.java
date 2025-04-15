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
    // 解法：前缀和
    public int countGoodTriplets2(int[] arr, int a, int b, int c) {

        /**
         * 条件
         * |arr[i] - arr[j]| <= a
         * |arr[j] - arr[k]| <= b
         * |arr[i] - arr[k]| <= c
         *
         * 等价于
         * arr[j] - a <= arr[i] <= arr[j] + a
         * arr[k] - b <= arr[j] <= arr[k] + b
         * arr[k] - c <= arr[i] <= arr[k] + c
         *
         * 枚举 j, k 位置， 找出 满足 条件的 i 的个数
         *
         *  Math.max(arr[j] - a, arr[k] - c) 比两个数最大值还要大，则满足
         *  Math.min(arr[j] + a, arr[k] + c) arr[i] 满足比两个数最小的还要小，则都满足
         *
         *  找出了 最小值 和 最大值的范围， 则取 [min , max] 的个数
         */
        int ans = 0;
        int x = 0;
        for (int i = 0; i < arr.length; i++) {
            x = Math.max(arr[i], x);
        }

        // 前缀和数组 (去最大的数)
        int[] s = new int[x + 2];

        // 枚举 j
        for (int j = 0; j < arr.length; j++) {
            // 枚举 k
            for (int k = j + 1; k < arr.length; k++) {
                // 不满足条件
                if (Math.abs(arr[j] - arr[k]) > b) {
                    continue;
                }
                // 找出满足条件的值, max 最大值不会超过 arr[]中的最大值， min 不会为 负数
                int max = Math.min(Math.min(arr[j] + a, arr[k] + c), x);
                int min = Math.max(Math.max(arr[j] - a, arr[k] - c), 0);
                // 取出范围 [min, max] 的个数, 因为是前缀和数组， 所以 s[max + 1] - s[min]
                // 如果 min > max ， 则为负数， 条件不成立，
                ans += Math.max(s[max + 1] - s[min], 0);
            }
            // 前缀和数组，s[i] 代表 < i 位置的数字个数
            for (int index = arr[j] + 1; index < s.length; index++) {
                s[index]++;
            }
        }
        return ans;
    }
}
