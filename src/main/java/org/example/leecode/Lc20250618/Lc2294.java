package org.example.leecode.Lc20250618;

import java.util.Arrays;

public class Lc2294 {

    public int partitionArray(int[] nums, int k) {
        // 排序
        Arrays.sort(nums);
        int ans = 1;
        // 贪心
        int max = nums[0] + k;
        for (int num : nums) {
            if (num <= max){
                continue;
            }
            ans++;
            max = num + k;
        }
        return ans;
    }


}
