package org.example.leecode.Lc20250618;

import java.util.ArrayList;
import java.util.List;

public class Lc2200 {

    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        //未统计的最小左边起始点
        int r = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == key) {
                int left = Math.max(r, i - k);
                // 右边界
                r = Math.min(n - 1, i + k) + 1;
                for (int j = left; j < r; j++) {
                    ans.add(j);
                }
            }
        }
        return ans;
    }
}
