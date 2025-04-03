package org.example.leecode.Lc20250000;


import java.util.Arrays;

//https://leetcode.cn/problems/row-with-maximum-ones/description/?envType=daily-question&envId=2025-03-22
public class Lc2643 {

    // 5s
    public int[] rowAndMaximumOnes(int[][] mat) {
        int[] ans = new int[]{0, 0};
        int max = 0;
        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
                ans = new int[]{i, count};
            }
        }
        return ans;
    }

    // 优化
    public int[] rowAndMaximumOnes2(int[][] mat) {
        int[] ans = new int[]{0, 0};
        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) {
                    count++;
                }
            }
            ans = count > ans[1] ? new int[]{i, count} : ans;
        }
        return ans;
    }

    // 优化
    public int[] rowAndMaximumOnes3(int[][] mat) {
        int row = -1;
        int max = -1;
        for (int i = 0; i <  mat.length; i++) {
            int sum = Arrays.stream(mat[i]).sum();
            if (sum > max) {
                max = sum;
                row = i;
            }
        }
        return new int[]{row, max};
    }
}

