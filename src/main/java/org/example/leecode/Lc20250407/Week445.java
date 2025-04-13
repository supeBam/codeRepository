package org.example.leecode.Lc20250407;

import cn.hutool.core.lang.hash.Hash;

import java.util.*;


public class Week445 {

    public static void main(String[] args) {
        Week445 week445 = new Week445();
        // System.out.println(week445.findClosest(2,7,4));
        // System.out.println(week445.findClosest(2,5,6));
        // System.out.println(week445.findClosest(1,5,3));
        // System.out.println(week445.smallestPalindrome("daccad"));
        // System.out.println(week445.smallestPalindrome("z"));
        // System.out.println(week445.smallestPalindrome("babab"));
    }

    // 第一题
    public int findClosest(int x, int y, int z) {
        int r1 = Math.abs(x - z);
        int r2 = Math.abs(y - z);
        return r1 == r2 ? 0 : r1 < r2 ? 1 : 2;
    }

    // 第二题
    public String smallestPalindrome(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        char t = ' ';
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) {
                continue;
            }
            // 如果 > 1, 则放前面
            if (count[i] == 1) {
                t = (char) (i + 97);
                continue;
            }
            // 个数
            int c = count[i];
            for (int j = 0; j < c / 2; j++) {
                sb.append((char) (i + 97));
            }
            if (c % 2 == 1) {
                t = (char) (i + 97);
            }

        }
        String l = sb.toString();
        String r = sb.reverse().toString();
        if (t != ' ') {
            return l + t + r;
        }
        return l + r;
    }
}
