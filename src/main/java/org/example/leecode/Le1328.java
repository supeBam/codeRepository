package org.example.leecode;


import java.lang.reflect.Array;
import java.util.Arrays;

// https://leetcode.cn/problems/break-a-palindrome/ 题目
public class Le1328 {
    String palindrome;
    public static void main(String[] args) {
        Le1328 le1328 = new Le1328();
        System.out.println(le1328.breakPalindrome("abccba"));
    }

//    贪心算法
    public String breakPalindrome(String palindrome) {
        this.palindrome = palindrome;
        if (palindrome.length() == 1) {
            return "";
        }
        return change();
    }

    public String change(){
        char[] c = palindrome.toCharArray();
        int l = 0, r = c.length - 1;
        while(l < r){
            // 贪心，字典序最小值的 'a'元素必定在前面
            if (c[l] != 'a'){
                c[l] = 'a';
                return new String(c);
            }
            l++;
            r--;
        }
        // 如果除了中心位置其他都为元素'a'，则最后一个元素改为'b'
        c[c.length - 1] = 'b';
        return new String(c);
    }
}
