package org.example.leecode;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.cn/problems/minimize-string-length/?envType=daily-question&envId=2025-03-28
public class Lc2718 {
    public int minimizedStringLength(String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        return set.size();
    }
}
