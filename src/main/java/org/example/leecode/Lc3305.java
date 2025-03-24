package org.example.leecode;

import java.util.*;

/**
 * https://leetcode.cn/problems/count-of-substrings-containing-every-vowel-and-k-consonants-i/?envType=daily-question&envId=2025-03-12
 * 3305. 元音辅音字符串计数 I
 */
public class Lc3305 {
    /**
     * 至少问题
     * 10个学生至少20岁，3个学生至少21岁，恰好20岁的学生有多少个？
     * 10 - 3 = 7 , 恰好的7个
     */

    public static void main(String[] args) {
        Lc3305 le3305 = new Lc3305();
        System.out.println(le3305.countOfSubstrings("ieaouqqieaouqq", 1));
        String s = "ieaouqqieaouqq";
        System.out.println(le3305.countOfSubstrings(s,1) - le3305.countOfSubstrings(s,2));
    }

    public int countOfSubstrings(String word, int k) {
        int ans = 0;
        int n = word.length();
        //辅音计数
        int count = 0;
        // 元音计数
        Map<Character, Integer> map = new HashMap<>();
        // l，r 左右指针
        int l = 0, r = 0;
        for (; r < n; r++) {
            if ("aeiou".indexOf(word.charAt(r)) >= 0) {
                map.put(word.charAt(r), map.getOrDefault(word.charAt(r), 0) + 1);
            } else {
                count++;
            }

            // 如果满足5个元音，且辅音个数 > k
            while (map.size() == 5 && count >= k) {
                // 移动左指针
                if ("aeiou".indexOf(word.charAt(l)) >= 0) {
                    map.put(word.charAt(l), map.get(word.charAt(l)) - 1);
                    if (map.get(word.charAt(l)) == 0) {
                        map.remove(word.charAt(l));
                    }
                } else {
                    count--;
                }
                l++;
            }
            // 移动左指针的步数就是个数
            ans += l;
        }
        return ans;
    }

}