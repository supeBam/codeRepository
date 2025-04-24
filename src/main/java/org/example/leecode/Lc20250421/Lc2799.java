package org.example.leecode.Lc20250421;

import java.util.*;

public class Lc2799 {

    public static void main(String[] args) {
        Lc2799 lc2799 = new Lc2799();
        lc2799.countCompleteSubarrays(new int[]{1, 3, 1, 2, 2});
    }

    public int countCompleteSubarrays(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        // 统计总共有多少种元素
        long count = Arrays.stream(nums).distinct().count();
        // 移动右指针
        int l = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            while (count == map.size()) {
                map.merge(nums[l], -1, Integer::sum);
                if (map.get(nums[l]) == 0) {
                    map.remove(nums[l]);
                }
                l++;
            }
            ans += l;
        }
        return ans;
    }
}
