package org.example.leecode.Lc20250421;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc784 {
    public List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        dfs(0, chars, ans);
        return ans;
    }

    public void dfs(int i, char[] chars, List<String> ans) {

        if (i == chars.length) {
            ans.add(new String(chars));
            return;
        }

        // 不选
        dfs(i + 1, chars, ans);

        // 选
        if (Character.isLetter(chars[i])) {
            chars[i] = chars[i] == Character.toLowerCase(chars[i]) ? Character.toUpperCase(chars[i]) : Character.toLowerCase(chars[i]);

            dfs(i + 1, chars, ans);

            // 还原
            chars[i] = chars[i] == Character.toLowerCase(chars[i]) ? Character.toUpperCase(chars[i]) : Character.toLowerCase(chars[i]);
        }
    }
}
