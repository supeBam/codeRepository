package org.example.leecode;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.cn/problems/count-prefixes-of-a-given-string/description/?envType=daily-question&envId=2025-03-24
public class Lc2255 {

    public int countPrefixes(String[] words, String s) {
        Set<String> set = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            set.add(s.substring(0, i + 1));
        }
        int ans = 0;
        for (String word : words) {
            if (set.contains(word)) {
                ans++;
            }
        }

        return ans;
    }


    // 函数库
    public int countPrefixes2(String[] words, String s) {
        int ans = 0;
        for (String word : words) {
            if (s.startsWith(word)){
                ans++;
            }
        }
        return ans;
    }
}
