package org.example.leecode.Lc20250407;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.cn/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/description/?envType=daily-question&envId=2025-04-08
public class Lc3396 {
    public static void main(String[] args) {
        Lc3396 lc3396 = new Lc3396();
        int[] ints = {6, 7, 8, 9};
        System.out.println(lc3396.minimumOperations(ints));
    }

    public int minimumOperations(int[] nums) {
        int n = nums.length;
        int ans = n / 3 + (n % 3 != 0 ? 1 : 0);
        Set<Integer> set = new HashSet<>();
        for (int i = n - 1; i >= 0; i--) {
            if (!set.add(nums[i])){
                return ans;
            }
            if (i % 3 == 0){
                ans--;
            }
        }
        return ans;
    }

    // 优化
    public int minimumOperations2(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = n - 1; i >= 0; i--) {
            if (!set.add(nums[i])){
                // 三个一组，放不进去就算下一组的
                return i / 3 + 1;
            }
        }
        return 0;
    }
}
