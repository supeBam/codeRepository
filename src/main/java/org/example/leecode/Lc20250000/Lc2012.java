package org.example.leecode.Lc20250000;


/**
 * https://leetcode.cn/problems/sum-of-beauty-in-the-array/?envType=daily-question&envId=2025-03-11
 * 2012. 数组美丽值求和
 */
public class Lc2012 {

    public static void main(String[] args) {
        Lc2012 le2012 = new Lc2012();
//        int[] ints = {2,4,6,4};
        int[] ints = {8, 3, 7, 8, 9, 10, 14};
//        int[] ints = {6, 3, 3, 9, 9};
        System.out.println(le2012.sumOfBeauties(ints));
    }


    public int sumOfBeauties(int[] nums) {
        int ans = 0;
        int n = nums.length;
        // 情况一：比前面所有数大，比后面全部数小 为2
        // 情况二：连续三个数存在单调递增 为 1。 情况一 包含 情况二
        // 情况三：三个数不满足单调性，不存在情况二的情况

        // 记录左边最大值， 右边最小值。比左边最大值大则比左边都要大，比右边最小值小则比右边都小
        int lMax = nums[0];
        int [] sum = new int[n];
        // 前缀和记录最小值
        sum[n - 1] = nums[n - 1];
        for (int i = n - 2; i > 1; i--) {
            sum[i] = Math.min(sum[i + 1], nums[i]);
        }

        for (int i = 1; i < nums.length - 1; i++) {
            // 判断是否满足情况一
            if ((nums[i] > lMax) && (nums[i] < sum[i + 1])) {
                ans = ans + 2;
            }
            // 满足情况二
            else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) {
                ans++;
            }
            //更新最大值
            lMax = Math.max(lMax, nums[i]);
        }
        return ans;
    }
}
