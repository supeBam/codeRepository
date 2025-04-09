package org.example.leecode.Lc20250407;

import java.util.ArrayList;
import java.util.List;

public class Lc22 {

    List<String> ans = new ArrayList<>();
    char[] c;
    int n;
    int len;

    public List<String> generateParenthesis(int n) {
        this.len = n * 2;
        this.n = n;
        c = new char[len];
        dfs(0, 0);
        return ans;
    }


    public void dfs(int i, int left) {
        // 边界条件
        if (i == len){
            ans.add(new String(c));
            return;
        }
        // 如果 左括号个数 < n; 选左括号
        if (left < n) {
            c[i] = '(';
            dfs(i + 1, left + 1);
        }

        // 如果 右括号个数 < 左括号 选右括号
        if (i - left < left) {
            c[i] = ')';
            dfs(i + 1, left);
        }
    }
}
