package org.example.leecode;

// https://leetcode.cn/problems/palindrome-partitioning/  题目地址

import java.util.ArrayList;
import java.util.List;

/**
 * 标签： 回溯算法 ，子集回溯
 */
public class Lc131 {
    List<List<String>> ans = new ArrayList<>();
    List<String> path = new ArrayList<String>();
    String s;

    public static void main(String[] args) {
        Lc131 le131 = new Lc131();
        le131.partition("aab");
    }

    public List<List<String>> partition(String s) {
        this.s = s;
        dfs(0);
        return ans;
    }

    public void dfs(int index) {
        if (index == s.length()) {
            ans.add(new ArrayList<String>(path));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(index, i)) {
                path.add(s.substring(index, i + 1));
                dfs(i + 1);
                path.remove(path.size() - 1); // 恢复现场
            }
        }
    }

    // 判断是否回文
    public boolean isPalindrome(int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
