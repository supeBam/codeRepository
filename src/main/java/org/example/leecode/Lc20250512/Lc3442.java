package org.example.leecode.Lc20250512;

import java.util.HashMap;
import java.util.Map;

public class Lc3442 {
    public static void main(String[] args) {
        Lc3442 lc3442 = new Lc3442();
        System.out.println(lc3442.maxDifference("aaaaabbc"));
    }

    public int maxDifference(String s) {
        int[] count = new int[26];
        int maxJ = 0;
        int minO = Integer.MAX_VALUE;
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) {
                continue;
            }
            if (count[i] % 2 == 0) {
                minO = Math.min(minO, count[i]);
            } else {
                maxJ = Math.max(maxJ, count[i]);
            }
        }

        return maxJ - minO;
    }
}
