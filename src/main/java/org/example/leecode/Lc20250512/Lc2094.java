package org.example.leecode.Lc20250512;

import lombok.val;

import java.util.*;

public class Lc2094 {

    public int[] findEvenNumbers(int[] digits) {
        int n = digits.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (digits[k] % 2 != 0 || k == i || k == j) {
                        continue;
                    }
                    int v = digits[i] * 100 + digits[j] * 10 + digits[k];
                    set.add(v);
                }
            }
        }
        return set.stream().sorted().mapToInt(Integer::intValue).toArray();
    }

}
