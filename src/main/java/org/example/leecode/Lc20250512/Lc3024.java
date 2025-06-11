package org.example.leecode.Lc20250512;

import java.util.Arrays;

public class Lc3024 {
    public String triangleType(int[] nums) {
        Arrays.sort(nums);
        if (nums[0] + nums[1] > nums[2]) {
            if (nums[0] == nums[1] && nums[1] == nums[2]) {
                return "equilateral";
            } else if (nums[0] == nums[1] || nums[1] == nums[2]) {
                return "isosceles";
            }
            return "scalene";
        }
        return "none";
    }
}
