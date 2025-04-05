package org.example.leecode.Lc20250331;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/sum-of-all-subset-xor-totals/description/?envType=daily-question&envId=2025-04-05
public class Lc1863 {
    public static void main(String[] args) {
        Lc1863 lc1863 = new Lc1863();
        int[] ints = {5, 1, 6};
        System.out.println(lc1863.subsetXORSum(ints));
    }

    int n;
    List<List<Integer>> allList = new ArrayList<>();
    List<Integer> list = new ArrayList<Integer>();

    public int subsetXORSum(int[] nums) {
        n = nums.length;
        int ans = 0;
        backtrack(nums, 0);
        for (List<Integer> integers : allList) {
            if (integers.size() == 1) {
                ans += integers.get(0);
            } else if (integers.size() > 1) {
                int temp = integers.get(0);
                for (int i = 1; i < integers.size(); i++) {
                    temp ^= integers.get(i);
                }
                ans += temp;
            }
        }
        return ans;
    }


    public void backtrack(int[] nums, int start) {
        if (start == n) {
            allList.add(new ArrayList<>(list));
            return;
        }
        // 不选
        backtrack(nums, start + 1);
        // 选
        list.add(nums[start]);
        backtrack(nums, start + 1);
        // 还原
        list.remove(list.size() - 1);
    }


    /**
     * 优化： 位运算
     * 异或运算比较特殊： 当比特位有奇数个 1 时，结果为 1；当比特位有偶数个 1 时，结果为 0。
     * 需要 异或 所有的值， 子集的总数为 2 ^ (n-1)
     * 例如 nums=[3,2,8]，第 0,1,3 个比特位上有 1，
     * 每个比特位对应的「所有子集的异或和的总和」分别为 2^0 * 2^(n-1) + 2^1 * 2^(n-1) + 2^3 * 2^(n-1)
     *  = (2^0 + 2^1 + 2^3) * 2^(n-1)


     *  假设数组 nums 中元素均为 0 或 1，子集的异或和为 1 的条件是子集中有奇数个 1。我们需要计算满足条件的子集数量。
     * 推导过程：
     * 若有 n 个元素，其中至少一个 1：
     * 任选一个 1 作为“固定元素”，剩下 n-1 个元素可自由选择。
     * 对剩下的 n-1 个元素的子集：
     * 若子集中有偶数个 1：加入固定元素后总共有奇数个 1，异或和为 1。
     * 若子集中有奇数个 1：不加入固定元素，保持奇数个 1，异或和为 1。
     * 无论剩余子集如何选择，总存在唯一方式（加入或不加入固定元素）使异或和为 1。
     * 因此，满足条件的子集数为 2^(n-1)（剩余 n-1 个元素的子集总数）。
     * 若没有 1：无法得到异或和为 1 的子集，结果为 0。
     */
    public int subsetXORSum2(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i : nums){
            ans |= i;
        }
        return ans << (n - 1);
    }
}
