package org.example.leecode.Lc20250331;

public class Lc2873 {

    public static void main(String[] args) {
        Lc2873 lc2873 = new Lc2873();
        //int[] ints = {1000000, 1, 1000000};
        int[] ints = {1, 10, 3, 4, 19};
        System.out.println(lc2873.maximumTripletValue2(ints));
    }

    //下标三元组 (i, j, k) 的值等于 (nums[i] - nums[j]) * nums[k]
    //如果所有满足条件的三元组的值都是负数，则返回 0

    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long ans = 0;
        long[] lMax = new long[n];
        long[] rMax = new long[n];
        for (int i = 0; i < n; i++) {
            lMax[i] = i == 0 ? nums[i] : Math.max(nums[i], lMax[i - 1]);
        }
        for (int i = n - 1; i >= 0; i--) {
            rMax[i] = i == n - 1 ? nums[i] : Math.max(nums[i], rMax[i + 1]);
        }

        for (int i = 1; i < n - 1; i++) {
            long cur = nums[i];
            long lmax = lMax[i - 1];
            long rmax = rMax[i + 1];
            if (lmax - cur < 0) {
                continue;
            }
            ans = Math.max(ans, (lmax - cur) * rmax);
        }
        return ans;
    }

    // 1,10,3,4,19
    // 优化1：能不能不用数组，而是使用变量 (减少空间)
    public long maximumTripletValue2(int[] nums) {
        int n = nums.length;
        long ans = 0;
        long lmax = nums[0];
        long[] rMax = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            rMax[i] = i == n - 1 ? nums[i] : Math.max(nums[i], rMax[i + 1]);
        }
        for (int i = 1; i < n - 1; i++) {
            long cur = nums[i];
            if (lmax - cur < 0) {
                lmax = Math.max(nums[i], lmax);
                continue;
            }
            long rmax = rMax[i + 1];
            ans = Math.max(ans, (lmax - cur) * rmax);
            lmax = Math.max(nums[i], lmax);
        }
        return ans;
    }

    // 优化2： 枚举  Lmax - nums[i] 最大值(每次更新Lmax)， 更新ans值， ans需要 当前的 和 上一个 做过乘法的 结果作比较
    public long maximumTripletValue3(int[] nums) {
        int n = nums.length;
        long ans = 0;
        long subMax = 0;
        long lmax = nums[0]; // 枚举 i 左边的 最大值
        // 三个数，每次比较最大值，过程中更新减法最大值
        for (int i = 2; i < n; i++) {
            // 左边最大值 - j的值
            subMax = Math.max(subMax, lmax - nums[i - 1]);
            // 更新左边最大值
            lmax = Math.max(lmax, nums[i - 1]);
            ans = Math.max(ans, subMax * nums[i]);
        }
        return ans;
    }

}
