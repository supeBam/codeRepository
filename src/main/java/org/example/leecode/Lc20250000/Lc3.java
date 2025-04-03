package org.example.leecode.Lc20250000;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * 滑动窗口练习
 */
public class Lc3 {


    public int lengthOfLongestSubstring(String s) {
        char[] c = s.toCharArray();
        int max = 0;
        int l = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int r = 0; r < c.length; r++) {
            map.put(c[r], map.getOrDefault(c[r], 0) + 1);
            // 控制左指针
            while (map.get(c[r]) > 1) {
                map.put(c[l], map.get(c[l]) - 1);
                l++;
            }
            max = Math.max(r - l + 1, max);
        }
        return max;
    }
}
