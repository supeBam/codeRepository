package org.example.leecode.Lc20250000;

public class Lc3110 {

    public int scoreOfString(String s) {
        int ans = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            ans += Math.abs(s.charAt(i) - s.charAt(i + 1));
        }
        return ans;
    }
}
