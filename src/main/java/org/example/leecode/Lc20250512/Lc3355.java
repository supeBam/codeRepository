package org.example.leecode.Lc20250512;

import java.util.*;

public class Lc3355 {

    public boolean isZeroArray(int[] nums, int[][] queries) {
        // 标记数组， 找到queries的前后坐标, 标记每一段的
        int[] d = new int[nums.length + 1];
        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            d[l]++;
            d[r + 1]--;
        }
        // 前缀和
        int[] preSum = new int[d.length];
        // 和值
        int sum = 0;
        for (int i = 0; i < d.length; i++) {
            sum += d[i];
            preSum[i] = sum;
        }
        // 遍历nums
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > preSum[i]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Lc3355 lc3355 = new Lc3355();
        int[] nums = {1, 0, 1};
        // int[] nums = {4, 3, 2, 1};
        int[][] queries = {{0, 2}};
        // int[][] queries = {{1, 3}, {0, 2}};
        System.out.println(lc3355.isZeroArray(nums, queries));
    }
}