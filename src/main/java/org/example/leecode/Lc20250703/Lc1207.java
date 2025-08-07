package org.example.leecode.Lc20250703;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Lc1207 {

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i : arr) {
            map.merge(i, 1, Integer::sum);
        }
        for (Integer i : map.values()) {
            if (!set.add(i)) {
                return false;
            }
        }
        return true;
    }
}
