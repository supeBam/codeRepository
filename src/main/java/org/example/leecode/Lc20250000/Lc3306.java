package org.example.leecode.Lc20250000;


/**
 * https://leetcode.cn/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/?envType=daily-question&envId=2025-03-13
 * 滑动窗口，比3305字符串长度更长
 */
public class Lc3306 {
    public static void main(String[] args) {
        // 创建Lc3306对象
        Lc3306 le3306 = new Lc3306();
        // 输出字符串"aeioqq"中长度为1的子串个数
        System.out.println(le3306.countOfSubstrings("aeioqq", 1));

    }

    public long countOfSubstrings(String word, int k) {
        long ans = 0;
        int[] cnt = new int[5];
        // 辅音个数
        int cntF = 0;
        // 元音个数
        int cntY = 0;

        // 初始化左右指针
        int r = 0, l = 0;
        for (; r < word.length(); r++) {
            int index = "aeiou".indexOf(word.charAt(r));
            if (index > -1) {
                cnt[index]++;
                if (cnt[index] == 1) {
                    cntY++;
                }
            }else {
                cntF++;
            }
            // 元音个数为5， 且辅音个数 >= k
            while (cntY == 5 && cntF >= k) {
                int lIndex = "aeiou".indexOf(word.charAt(l));
                if (lIndex > -1) {
                    cnt[lIndex]--;
                    if (cnt[lIndex] == 0) {
                        cntY--;
                    }
                } else {
                    cntF--;
                }
                l++;
            }
            // 计算左指针移动的步数
            ans += l;
        }
        return ans;
    }
}
