package org.example.leecode.Lc20250703;

public class Lc3201 {

    public static void main(String[] args) {
        Lc3201 lc3201 = new Lc3201();
        System.out.println(lc3201.maximumLength(new int[]{1, 2, 1, 2, 1, 2}, 2));
    }

    /**
     * (a+b) mod k = (b+c) mod k
     * (a+b−(b+c)) mod k = 0
     * (a−c) mod k = 0
     * 求最长子序列的长度，该子序列的奇数项都相同，偶数项都相同。
     */
    public int maximumLength(int[] nums, int k) {
        int ans = 0;
        int[][] f = new int[k][k];
        // {1, 2, 1, 2, 1, 2}
        for (int x : nums) {
            x %= k;
            for (int y = 0; y < k; y++) {
                f[y][x] = f[x][y] + 1;
                ans = Math.max(ans, f[y][x]);
            }
        }
        return ans;
    }

}
