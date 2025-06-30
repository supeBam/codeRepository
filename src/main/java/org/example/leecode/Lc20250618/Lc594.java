package org.example.leecode.Lc20250618;

import java.util.HashMap;
import java.util.Map;

public class Lc594 {

    // 哈希表
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int ans = 0;
        for (int num : nums) {
            if (map.containsKey(num + 1)) {
                ans = Math.max(ans, map.get(num) + map.get(num + 1));
            }
        }
        return ans;
    }
}
