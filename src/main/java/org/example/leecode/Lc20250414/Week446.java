package org.example.leecode.Lc20250414;

import java.util.Arrays;

public class Week446 {
    public static void main(String[] args) {
        Week446 week446 = new Week446();
        // System.out.println(week446.calculateScore(new String[]{"jump", "add", "add", "jump", "add", "jump"}, new int[]{2, 1, 3, 1, -2, -3}));
        // System.out.println(week446.calculateScore(new String[]{"jump", "add", "add"}, new int[]{3, 1, 1}));
        // System.out.println(week446.calculateScore(new String[]{"jump"}, new int[]{0}));
        // System.out.println(week446.maximumPossibleSize(new int[]{4,2,5,3,5}));
        // System.out.println(week446.maximumPossibleSize(new int[]{1,2,3}));
        System.out.println(Arrays.toString(week446.resultArray(new int[]{1, 2, 3, 4, 5}, 3)));
        System.out.println(Arrays.toString(week446.resultArray(new int[]{1, 2, 4, 8, 16, 32}, 4)));
        System.out.println(Arrays.toString(week446.resultArray(new int[]{1, 1, 2, 1, 1}, 2)));
    }

    public long calculateScore(String[] instructions, int[] values) {
        int n = instructions.length;
        boolean[] flag = new boolean[n];
        long ans = 0;
        int i = 0;
        while (i < n && i >= 0) {
            // 来过
            if (flag[i]) {
                break;
            }
            flag[i] = true;
            int next = values[i];
            if (instructions[i].equals("jump")) {
                i += next;
            } else {
                ans += next;
                i++;
            }
        }
        return ans;
    }


    public int maximumPossibleSize(int[] nums) {
        int max = nums[0];
        int n = nums.length;
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] >= max) {
                max = nums[i];
                continue;
            }
            nums[i] = 0;
        }
        for (int num : nums) {
            if (num != 0) {
                ans++;
            }
        }
        return ans;
    }

    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        int n = nums.length;
        // x = 0, 找到 k 的倍数位置
        int kL = -1;
        int kR = -1;
        int index = 0;
        long sum = (long) n * (n + 1) / 2;
        // 找到 k 的倍数位置
        while (k > 0) {
            for (int i = 0; i < n; i++) {
                if (nums[i] % k == 0) {
                    kL = i;
                    break;
                }
            }
            if (kL != -1) {
                for (int i = n - 1; i >= kL; i--) {
                    if (nums[i] % k == 0) {
                        kR = i;
                        break;
                    }
                }
            }
            // 计算 x = 0 的情况
            if (kL != -1) {
                // 左边的个数
                long l = (long) kL;
                // 右边的个数
                long r = (long) n - kR - 1;
                // 求阶层
                long v = l * (l + 1) / 2 + r * (r + 1) / 2;
                ans[index] = sum - v;
                if (k == 1) {
                    ans[index] = sum - ans[index - 1];
                } else if (index != 0) {
                    ans[index] = sum - ans[index - 1] - v;
                    sum -= ans[index - 1];
                }
            } else {
                // n 的 阶层
                ans[index] = (long) n * (n + 1) / 2;
                break;
            }
            k--;
            index++;
        }
        return ans;
    }
}
