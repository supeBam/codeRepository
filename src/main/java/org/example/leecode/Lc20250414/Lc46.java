package org.example.leecode.Lc20250414;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lc46 {
    public static void main(String[] args) {
        Lc46 lc46 = new Lc46();
        System.out.println(lc46.permute(new int[]{1,1,3}));
    }

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int n;
    boolean[] flag;
    int[] nums;

    public List<List<Integer>> permute(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        this.flag = new boolean[n];
        dfs(0);
        return ans;
    }

    public void dfs(int deep) {
        if (path.size() == n) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!flag[i]) {
                flag[i] = true;
                path.add(nums[i]);

                dfs(deep + 1);

                flag[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
