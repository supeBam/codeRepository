package org.example.leecode.Lc20250407;

import java.util.ArrayList;
import java.util.List;

public class Lc22 {

    List<String> ans = new ArrayList<>();
    char[] c = new char[]{'(', ')'};

    public List<String> generateParenthesis(int n) {
        int len = n * 2;
        dfs(0, n, len, 0, 0);
        return ans;
    }

    StringBuilder sb = new StringBuilder();

    public void dfs(int i, int n, int len, int l, int r) {
        int d = len - sb.length();
        // 左括号 < 右括号 或则 左括号个数 > n

        if (d == 0) {
            ans.add(sb.toString());
            return;
        }
        // 不选
        dfs(i, n, len, l, r);
        // 选
        sb.append(c[i % 2]);
        dfs(i + 1, n, len - 1, );

    }
}
