package org.example.leecode.Lc20250000;

/**
 *https://leetcode.cn/problems/check-balanced-string/?envType=daily-question&envId=2025-03-14
 */
public class Lc3340 {

    public static void main(String[] args) {
        Lc3340 le3340 = new Lc3340();
        System.out.println(le3340.isBalanced("24123"));
    }
    public boolean isBalanced(String num) {
        int ans = 0;
        for(int i = 0; i < num.length(); i++){
            int x = num.charAt(i) - '0';
            if (x == 0){
                continue;
            }
            if (i % 2 != 0){
                ans += x;
            }else {
                ans -= x;
            }
        }
        return ans == 0;
    }
}
