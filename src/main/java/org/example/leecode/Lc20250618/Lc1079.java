package org.example.leecode.Lc20250618;

import java.util.*;

public class Lc1079 {
    public static void main(String[] args) {
        Lc1079 lc1079 = new Lc1079();
        System.out.println(lc1079.numTilePossibilities2("AAB"));
        // System.out.println(lc1079.numTilePossibilities2("AAABBC"));
    }

    public int numTilePossibilities(String tiles) {
        char[] chars = tiles.toCharArray();
        Set<String> set = new HashSet<>();
        List<String> path = new ArrayList<>();
        boolean[] used = new boolean[tiles.length()];

        dfs(0, chars, path, set, used);

        return set.size() - 1;
    }

    // 方法一
    public void dfs(int deep, char[] chars, List<String> path, Set<String> set, boolean[] used) {

        String join = String.join("", path);
        set.add(join);

        for (int i = 0; i < chars.length; i++) {
            if (!used[i]) {
                used[i] = true;
                path.add(String.valueOf(chars[i]));

                dfs(deep + 1, chars, path, set, used);

                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }



    // 方法二
    int ans = 0;
    public int numTilePossibilities2(String tiles) {
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        boolean[] used = new boolean[tiles.length()];
        dfs2(chars, used);

        return ans;
    }

    public void dfs2(char[] chars, boolean[] used) {
        char last = '*';
        for (int i = 0; i < chars.length; i++) {
            if (!used[i] && chars[i] != last ) {
                ans++;
                used[i] = true;

                dfs2(chars, used);

                used[i] = false;
                last = chars[i];
            }
        }
    }
}
