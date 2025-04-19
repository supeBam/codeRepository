package org.example.leecode.Lc20250414;

import cn.hutool.core.text.StrBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lc1593 {
    public static void main(String[] args) {
        Lc1593 lc1593 = new Lc1593();
        System.out.println(lc1593.maxUniqueSplit("abcde"));
    }


    public List<List<String>> maxUniqueSplit(String s) {
        this.c = s.toCharArray();
        this.n = c.length;
        this.s = s;
        dfs(0);
        return res;
    }

    char[] c;
    StrBuilder sb = new StrBuilder();
    int n;
    // int ans = 0;
    String s;
    Set<String> set = new HashSet<>();
    List<List<String>> res = new ArrayList<>();
    List<String> path = new ArrayList<>();

    public void dfs(int deep) {


        if (n == deep) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = deep; i < n; i++) {
            path.add(s.substring(0, i));
            dfs(deep + 1);
            path.remove(path.size()-1);
        }
    }
}
