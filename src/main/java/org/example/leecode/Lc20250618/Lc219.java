package org.example.leecode.Lc20250618;

import java.util.HashSet;
import java.util.Set;

public class Lc219 {
    /**
     * 判断数组中是否存在两个 不同的索引 i 和 j
     * 满足 nums[i] == nums[j] 且 abs(i - j) <= k
     *
     * abs(i - j) <= k 代表 滑动窗口大小为 k
     *
     * 题目转为 在窗口内 有重复的数
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int n = nums.length;
        int l = 0;
        Set<Integer> set = new HashSet<>();
        for (int r = 0; r < n; r++) {
            // 窗口大小为 k
            if (r <= k){
                if (!set.add(nums[r])){
                    return true;
                }
                continue;
            }
            // 移除左边,添加右边
            set.remove(nums[l++]);
            if (!set.add(nums[r])){
                return true;
            }
        }
        return false;
    }
}
