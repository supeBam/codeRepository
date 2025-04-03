package org.example.leecode.Lc20250000;


/**
 * 643.子数组最大
 */
public class Lc643 {

    public static void main(String[] args) {
        Lc643 le643 = new Lc643();
        System.out.println(le643.findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
    }

    public double findMaxAverage(int[] nums, int k) {
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // 窗口
            if (i < k - 1) {
                continue;
            }
            // 计算机结果
            ans = Math.max(ans, sum);
            // 窗口左边框
            sum -= nums[i - k + 1];
        }
        return (double) ans / k;
    }
}
