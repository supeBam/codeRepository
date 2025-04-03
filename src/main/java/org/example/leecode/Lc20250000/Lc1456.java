package org.example.leecode.Lc20250000;


/**
 * 1456. 定长子串中元音的最大数目
 * https://leetcode.cn/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/
 * 滑动窗口
 */
public class Lc1456 {

    public static void main(String[] args) {

    }

    public int maxVowels(String s, int k) {
        char[] c = s.toCharArray();

        int ans = 0;
        int num = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 'a' || c[i] == 'e' || c[i] == 'i' || c[i] == 'o' || c[i] == 'u') {
                num++;
            }
            // 找到窗口最右边界, 把窗口大小撑到 k
            if (i < k - 1) {
                continue;
            }
            // 更新最大值
            ans = Math.max(ans, num);
            // 移动窗口, 最左边指针需要判断是否为元音
            char left = c[i - k + 1];
            if (left == 'a' || left == 'e' || left == 'i' || left == 'o' || left == 'u') {
                num--;
            }
        }
        return ans;
    }
}
