package org.example.leecode.Lc20250421;

import cn.hutool.core.text.StrBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Lc51 {
    public static void main(String[] args) {
        Lc51 lc51 = new Lc51();
        System.out.println(lc51.solveNQueens(4));
    }

    int n;
    boolean[] flag;
    boolean[] leftFlag;
    boolean[] rightFlag;
    int[] queen;
    List<List<String>> ans;
    List<String> path;
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        flag = new boolean[n];
        leftFlag = new boolean[2 * n - 1];
        rightFlag = new boolean[2 * n - 1];
        queen = new int[n];
        ans = new ArrayList<>();
        dfs(0);
        return ans;
    }

    public void dfs(int c) {
        // 边界
        if (c == n){
            List<String> path = new ArrayList<>();
            for (int i : queen) {
                char[] chars = new char[n];
                Arrays.fill(chars, '.');
                chars[i] = 'Q';
                path.add(new String(chars));
            }
            ans.add(path);
            return;
        }

        for (int i = 0; i < n; i++) {
            int left = c - i + n - 1;
            if ((!flag[i] && !rightFlag[i + c] && !leftFlag[left] ) ) {
                flag[i] = true;
                rightFlag[i + c] = true;
                leftFlag[left] = true;
                queen[c] = i;

                dfs(c + 1);

                flag[i] = false;
                rightFlag[i + c] = false;
                leftFlag[left] = false;
            }
        }

    }

}
