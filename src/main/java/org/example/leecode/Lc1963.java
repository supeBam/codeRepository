package org.example.leecode;

/**
 * https://leetcode.cn/problems/minimum-number-of-swaps-to-make-the-string-balanced/description/?envType=daily-question&envId=2025-03-17
 */
public class Lc1963 {


//   贪心，每次当遇到 ] 就 - 1，如果为0时 ，交换最右边的 [
    public int minSwaps(String s) {
        int count = 0;
        int ans = 0;
        int r = s.length() - 1;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '[') {
                count++;
            } else if (count > 0) {
                count--;
            } else  {
                while (chars[r] != '[') {
                    r--;
                }
                ans++;
                chars[r] = ']';
                count++;
            }
        }
        return ans;
    }

//    优化，不需要交换，不影响结果
    public int minSwaps2(String s) {
        int count = 0;
        int ans = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '[') {
                count++;
            } else if (count > 0) {
                count--;
            } else  {
                ans++;
                count++;
            }
        }
        return ans;
    }


    //    优化，去掉ans变量
    public int minSwaps3(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '[' || count == 0) {
                count++;
            } else{
                count--;
            }
        }
        return count / 2;
    }
}
