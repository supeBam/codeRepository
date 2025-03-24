package org.example.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/count-subarrays-where-max-element-appears-at-least-k-times/
 * 滑动窗口
 */
public class Lc2962 {
    public static void main(String[] args) {
        Lc2962 le2962 = new Lc2962();
        System.out.println(le2962.countSubarrays(new int[]{1,3,2,3,3}, 2));
    }


    public long countSubarrays(int[] nums, int k) {
        long ans = 0;
        int l = 0;
        int max = 0;
        int cntMax = 0; // 计算最大值有多少个
        for (int i : nums){
            max = Math.max(i, max);
        }

        for(int r : nums){
            if (max == r){
                cntMax++;
            }
            while(cntMax == k){
                //移动左指针
                if(nums[l] == max){
                    cntMax--;
                }
                l++;
            }
            // 左指针移动多少次就是多少个
            ans += l;
        }
        return ans;
    }
}
