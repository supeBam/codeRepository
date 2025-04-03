package org.example.leecode.Lc20250000;

// https://leetcode.cn/problems/check-if-a-parentheses-string-can-be-valid/description/?envType=daily-question&envId=2025-03-23
public class Lc2116 {
    public static void main(String[] args) {
        System.out.println(new Lc2116().canBeValid("(((())", "111111"));
    }

    //Can never be valid
    public boolean canBeValid(String s, String locked) {
        int n = s.length();
        if (n % 2 != 0) {
            return false;
        }
        // 维护未匹配左括号的最大值和最小值
        int max = 0;
        int min = 0;
        for (int i = 0; i < n; i++) {
            if (locked.charAt(i) == '1') {
                char c = s.charAt(i);
                int item = c == '(' ? 1 : -1;
                max += item;
                // 最大值 < 0 ，代表左括号不够分
                if (max < 0) {
                    return false;
                }
                min += item;
            } else {
                max++;
                min--;
            }
            // 最小值 < 0，则代表都为奇数，设为最小值1
            if (min < 0) {
                min = 1;
            }
        }
        // 最小值 == 0， 则说明左括号刚匹配完
        return min == 0;
    }
}