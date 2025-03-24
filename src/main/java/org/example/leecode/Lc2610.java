package org.example.leecode;

import java.util.*;

/**
 * https://leetcode.cn/problems/convert-an-array-into-a-2d-array-with-conditions/description/?envType=daily-question&envId=2025-03-19
 */
public class Lc2610 {

    public static void main(String[] args) {
        Lc2610 le2610 = new Lc2610();
        System.out.println(le2610.findMatrix3(new int[]{1, 3, 4, 1, 2, 3, 1}));
    }

    public List<List<Integer>> findMatrix(int[] nums) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        int[] count = new int[201];
        // 代表有多少行
        int max = 0;
        for (int num : nums) {
            count[num]++;
            max = Math.max(max, count[num]);
        }
        for (int i = 0; i < max; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i1 = 0; i1 < 201; i1++) {
                if (count[i1] > 0) {
                    list.add(i1);
                    count[i1]--;
                }
            }
            ans.add(list);
        }
        return ans;
    }


    public List<List<Integer>> findMatrix2(int[] nums) {
        ArrayList<List<Integer>> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        // 代表有多少行
        int max = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            max = Math.max(max, map.get(num));
        }
        while (max-- > 0) {
            ArrayList<Integer> list = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() > 0) {
                    list.add(entry.getKey());
                    map.put(entry.getKey(), entry.getValue() - 1);
                }
            }
            ans.add(list);
        }
        return ans;
    }

    /**
     * 一个数出现了多少次，就加到第几行。
     *
     * 首次遇到 nums[i]=1，加到第一行。
     * 再次遇到 nums[i]=1，加到第二行。
     * 第三次遇到 nums[i]=1，加到第三行。
     * @param nums
     * @return
     */
    public List<List<Integer>> findMatrix3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] cnt = new int[nums.length + 1];
        for (int x : nums) {
            if (cnt[x] == ans.size()) { // 需要加一行
                ans.add(new ArrayList<>());
            }
            ans.get(cnt[x]).add(x);
            cnt[x]++;
        }
        return ans;
    }
}
