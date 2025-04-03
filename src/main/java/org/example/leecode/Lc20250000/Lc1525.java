package org.example.leecode.Lc20250000;

import java.util.HashSet;

public class Lc1525 {
    public int numSplits(String s) {
        int n = s.length();
        HashSet<Object> set = new HashSet<>();
        int ans = 0;
        // 当前位置有几个不同的数，顺着遍历
        int[] count1 = new int[n];
        for (int i = 0; i < n; i++) {
            set.add(s.charAt(i));
            count1[i] = set.size();
        }
        set.clear();
        // 当前位置有几个不同的数，倒着遍历
        int[] count2 = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            set.add(s.charAt(i));
            count2[i] = set.size();
        }

        for (int i = 1; i < n; i++) {
            if (count1[i - 1] == count2[i]) {
                ans++;
            }
        }
        return ans;
    }
}
