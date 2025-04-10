package org.example.leecode.Lc20250407;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.cn/problems/3sum/
public class Lc15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            // 相同的不放
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 这两个操作太强了  + 最小两个 都 > 0 后面都没有了
            if (nums[i] + nums[i+1] + nums[i+2] > 0) {
                break;
            }
            // + 最大两个 都 < 0 , 枚举下一个 i
            if (nums[i] + nums[n-1] + nums[n-2] < 0) {
                continue;
            }
            int l = i + 1;
            int r = n - 1;
            int target = nums[i];
            while (l < r) {
                int sum = target + nums[l] + nums[r];
                if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                } else {
                    res.add(Arrays.asList(target, nums[l], nums[r]));
                    do {
                        l++;
                    } while (l < r && nums[l] == nums[l - 1]);
                    do {
                        r--;
                    } while (l < r && nums[r] == nums[r + 1]);
                }
            }
        }
        return res;
    }

}
