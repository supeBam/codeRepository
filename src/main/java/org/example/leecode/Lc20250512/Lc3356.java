package org.example.leecode.Lc20250512;

/**
 * [4,3,2,1]
 * 1,3  2
 * 0,2  1
 * <p>
 * 0,2,2,2,-2
 * 1,1,1,-1,0
 */
public class Lc3356 {

    public int minZeroArray(int[] nums, int[][] queries) {
        int n = queries.length;
        // 二分法
        int l = -1, r = n + 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            // 检查是否能完成 0 数组
            if (check(nums, queries, mid)) {
                // 够
                r = mid;
            }else{
                // 不够
                l = mid;
            }
        }
        return r <= n ? r : -1;
    }

    private boolean check(int[] nums, int[][] queries, int mid) {
        int n = nums.length;
        // 差分数组
        int[] diff = new int[n + 1];
        for (int i = 0; i < mid; i++) {
            int l = queries[i][0], r = queries[i][1], v = queries[i][2];
            diff[l] += v;
            diff[r + 1] -= v;
        }
        // 差分值
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += diff[i];
            if (sum < nums[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Lc3356 lc3356 = new Lc3356();
        // int[] nums = {4, 3, 2, 1};
        int[] nums = {2, 0, 2};

        // int[][] queries = {{1, 3, 2}, {0, 2, 1}};
        int[][] queries = {{0, 2, 1}, {1, 1, 1}, {0, 2, 1}};
        System.out.println(lc3356.minZeroArray(nums, queries));
    }
}
