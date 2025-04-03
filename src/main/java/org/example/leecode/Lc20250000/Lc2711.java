package org.example.leecode.Lc20250000;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://leetcode.cn/problems/difference-of-number-of-distinct-values-on-diagonals/description/?envType=daily-question&envId=2025-03-25
public class Lc2711 {

    public static void main(String[] args) {
        Lc2711 lc2711 = new Lc2711();
        int[][] grid = {{1, 2, 3, 4}, {3, 1, 5, 5}, {3, 2, 1, 7}};
        System.out.println(Arrays.deepToString(lc2711.differenceOfDistinctValues2(grid)));
    }

    public int[][] differenceOfDistinctValues(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] res = new int[n][m];
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                for (int k = i - 1, l = j - 1; k >= 0 && l >= 0; k--, l--) {
                    set.add(grid[k][l]);
                }
                int topLeft = set.size();

                set.clear();

                for (int k = i + 1, l = j + 1; k < n && l < m; k++, l++) {
                    set.add(grid[k][l]);
                }
                int bottomRight = set.size();

                set.clear();

                res[i][j] = Math.abs(topLeft - bottomRight);
            }
        }
        return res;
    }

    public int[][] differenceOfDistinctValues2(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] ans = new int[m][n];

        // 统计
        Set<Integer> set = new HashSet<>();
        // 对角线 遍历，对角线性质每个节点(j - i = k)，设右上角 k 为 1，k = i - j + n  。左下角为 k = m + n - 1
        for (int k = 1; k < m + n; k++) {
            // 求对角线数组的最大值和最小值
            int min = Math.max(n - k, 0); // 不能为负数
            int max = Math.min(m + n - k - 1, n - 1); // 不能超过 n - 1

            set.clear();
            for (int j = min; j <= max; j++) { // 遍历对角线
                int i = k + j - n;
                // 该节点左上的不同值 个数
                ans[i][j] = set.size();
                set.add(grid[i][j]);
            }

            set.clear();
            for (int j = max; j >= min; j--) { // 遍历对角线
                int i = k + j - n;
                ans[i][j] = Math.abs(ans[i][j] - set.size());
                set.add(grid[i][j]);
            }
        }
        return ans;
    }
}
