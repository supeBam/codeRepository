package org.example.leecode;

// https://leetcode.cn/problems/minimum-cost-to-make-all-characters-equal/solutions/2286922/yi-ci-bian-li-jian-ji-xie-fa-pythonjavac-aut0/?envType=daily-question&envId=2025-03-27
public class Lc2712 {
    public static void main(String[] args) {
        Lc2712 lc2712 = new Lc2712();
        System.out.println(lc2712.minimumCost("010101"));
    }

    /**
     * 反转只能改变相邻的两个元素是否相等
     * <p>
     * 问题转换为 求出不相等的相邻字符的最小成本
     *
     * @param s
     * @return
     */
    public long minimumCost(String s) {
        int n = s.length();
        char[] charArray = s.toCharArray();
        long ans = 0;
        for (int i = 1; i < n; i++) {
            if (charArray[i] != charArray[i - 1]) {
                ans += Math.min(i, n - i);
            }
        }
        return ans;
    }
}
