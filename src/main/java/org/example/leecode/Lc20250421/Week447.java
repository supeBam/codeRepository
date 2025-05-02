package org.example.leecode.Lc20250421;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Week447 {

    public static void main(String[] args) {
        Week447 week447 = new Week447();
        /**
         * 输入: n = 5, buildings = [[1,3],[3,2],[3,3],[3,5],[5,3]]
         *
         * 输出: 1
         */
        // week447.countCoveredBuildings(5, new int[][]{{1, 3}, {3, 2}, {3, 3}, {3, 5}, {5, 3}});
        // System.out.println(Arrays.toString(week447.pathExistenceQueries(2, new int[]{1, 3}, 1, new int[][]{{0, 0}, {0, 1}})));

        // week447.concatenatedDivisibility(new int[]{3, 12, 45}, 5);
        week447.concatenatedDivisibility(new int[]{10, 5}, 10);
    }


    // Lc3531   统计被覆盖的建筑  模拟题
    public int countCoveredBuildings(int n, int[][] buildings) {
        // 统计每行每列的最小值和最大值
        int[] rowMin = new int[n + 1];
        int[] rowMax = new int[n + 1];

        int[] colMin = new int[n + 1];
        int[] colMax = new int[n + 1];

        Arrays.fill(rowMin, n + 1);
        Arrays.fill(colMin, n + 1);


        for (int[] p : buildings) {
            int x = p[0];
            int y = p[1];
            // 最小行（用于判断上下位置） 按照 节点的y
            rowMin[y] = Math.min(x, rowMin[y]);
            rowMax[y] = Math.max(x, rowMax[y]);

            colMin[x] = Math.min(y, colMin[x]);
            colMax[x] = Math.max(y, colMax[x]);
        }
        int ans = 0;
        for (int[] p : buildings) {
            int x = p[0];
            int y = p[1];
            // 如果当前节点 的行 和列 都比 最小节点行列 大， 比最大节点的行列 小，则结果++
            if (colMin[x] < y && colMax[x] > y && rowMin[y] < x && rowMax[y] > x) {
                ans++;
            }
        }
        return ans;
    }

    // Lc3532 路径存在性查询  并查集
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // 断点索引
        List<Integer> idxList = new ArrayList<>();
        // 找到断点
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] > maxDiff) {
                idxList.add(i);
            }
        }

        boolean[] ans = new boolean[queries.length];
        int idx = 0;
        for (int[] q : queries) {
            int x = q[0];
            int y = q[1];

            // 找到左边第一个断点是否相同，相同则代表在同一区间
            int idx1 = idxBreakpoint(idxList, x);
            int idx2 = idxBreakpoint(idxList, y);

            ans[idx] = idx1 == idx2;
            idx++;

        }
        return ans;
    }

    public int idxBreakpoint(List<Integer> idxList, int target) {
        int index = Collections.binarySearch(idxList, target);
        return index >= 0 ? index : -index - 1;
    }

    // Lc3533  连接数组的最大值  回溯
    public int[] concatenatedDivisibility(int[] nums, int k) {

        return new int[]{};
    }

    public void backTrack(int[] nums, int k, int start, List<List<Integer>> ans, List<Integer> path, boolean[] used) {
        if (k == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (k % nums[i] == 0) {
                path.add(nums[i]);
                used[i] = true;
                backTrack(nums, k / nums[i], i + 1, ans, path, used);
                used[i] = false;
                path.remove(path.size() - 1);
            }

        }
    }

}
