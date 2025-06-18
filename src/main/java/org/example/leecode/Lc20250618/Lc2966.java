package org.example.leecode.Lc20250618;

import java.util.Arrays;

public class Lc2966 {

    public static void main(String[] args) {
        Lc2966 lc2966 = new Lc2966();
        // lc2966.divideArray(new int[]{4, 2, 9, 8, 2, 12, 7, 12, 10, 5, 8, 5, 5, 7, 9, 2, 5, 11}, 14);
        lc2966.divideArray(new int[]{2, 4, 2, 2, 5, 2}, 2);
    }

    public int[][] divideArray(int[] nums, int k) {
        // 排序
        Arrays.sort(nums);

        int[][] ints = new int[nums.length / 3][];
        int index = 0;
        for (int i = 0; i < nums.length; i += 3) {
            if (nums[i + 2] - nums[i] > k) {
                return new int[][]{};
            }
            ints[index] = Arrays.copyOfRange(nums, i, i + 3);
            index++;
        }

        return ints;
    }
}
