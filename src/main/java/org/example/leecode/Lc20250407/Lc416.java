package org.example.leecode.Lc20250407;


import java.util.*;

//https://leetcode.cn/problems/partition-equal-subset-sum/?envType=daily-question&envId=2025-04-07
public class Lc416 {

    Set<Integer> set = new HashSet<Integer>();

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            return false;
        }
        int access = sum / 2;

        return true;
    }


}
