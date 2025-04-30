package org.example.leecode.Lc20250428;

public class Lc1295 {
    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            if (String.valueOf(num).length() % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }

    // 方法二：数学
    public int findNumbers2(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            if ((int)(Math.log10(num) + 1) % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }
}
