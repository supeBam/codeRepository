package org.example.leecode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/maximum-total-beauty-of-the-gardens/description/?envType=daily-question&envId=2025-03-08
public class Lc2234 {
    public static void main(String[] args) {
        Lc2234 le2234 = new Lc2234();
        System.out.println(le2234.maximumBeauty(new int[]{1, 1, 1 ,3}, 7, 6, 12, 1));
//        System.out.println(le2234.maximumBeauty(new int[]{2, 4, 5, 3}, 10, 5, 2, 6));
//        int[] flowers = new int[]{1056, 73246, 24730, 45592, 2076, 47062, 51329, 30904, 77941, 93853, 99362, 35655, 37069, 29547, 75748, 19177, 15083, 62416, 38012, 63192, 22196, 66038, 70061, 61813, 17744, 75195, 91086, 16455, 62569, 376, 99843, 75705, 63131, 64017, 90656, 79076, 69391, 39275, 70667, 87360, 86690, 42416, 99339, 7827, 5112, 93538, 31350, 75264, 72815, 97536, 76295, 8700, 35630, 99048, 9193, 71976, 66667, 41722, 9016, 83118, 22486, 93533, 11894, 22471, 69541, 34613, 2290, 50824, 77760, 89037, 71688, 91386, 41314, 63112, 74778, 97032, 64333, 11903, 42272, 46057, 48163, 72195, 44974, 14659, 94983, 29406, 75337, 83095, 87638, 13317, 53059, 87590, 3989, 80643, 9239, 94515, 22388, 87053, 33189};
//        int newFlowers = 4141897;
//        int target = 100000;
//        int full = 62283;
//        int partial = 17678;
//        System.out.println(le2234.maximumBeauty(flowers, newFlowers, target, full, partial));
    }

    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        int n = flowers.length;

        // 如果全部种满，还剩下多少朵花？
        long leftFlowers = newFlowers - (long) target * n; // 先减掉
        for (int i = 0; i < n; i++) {
            flowers[i] = Math.min(flowers[i], target);
            leftFlowers += flowers[i]; // 把已有的加回来
        }

        // 没有种花，所有花园都已种满
        if (leftFlowers == newFlowers) {
            return (long) n * full; // 答案只能是 n*full（注意不能减少花的数量）
        }

        // 可以全部种满
        if (leftFlowers >= 0) {
            // 两种策略取最大值：留一个花园种 target-1 朵花，其余种满；或者，全部种满
            return Math.max((long) (target - 1) * partial + (long) (n - 1) * full, (long) n * full);
        }

        Arrays.sort(flowers); // 时间复杂度的瓶颈在这，尽量写在后面

        long ans = 0;
        long preSum = 0;
        int j = 0;
        // 枚举 i，表示后缀 [i, n-1] 种满（i=0 的情况上面已讨论）
        for (int i = 1; i <= n; i++) {
            // 撤销，flowers[i-1] 不变成 target
            leftFlowers += target - flowers[i - 1];
            if (leftFlowers < 0) { // 花不能为负数，需要继续撤销
                continue;
            }

            // 满足以下条件说明 [0, j] 都可以种 flowers[j] 朵花
            while (j < i && (long) flowers[j] * j <= preSum + leftFlowers) {
                preSum += flowers[j];
                j++;
            }

            // 计算总美丽值
            // 在前缀 [0, j-1] 中均匀种花，这样最小值最大
            long avg = (leftFlowers + preSum) / j; // 由于上面特判了，这里 avg 一定小于 target
            long totalBeauty = avg * partial + (long) (n - i) * full;
            ans = Math.max(ans, totalBeauty);
        }
        return ans;
    }
}
