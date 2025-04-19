package org.example.leecode.Lc20250414;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lc47 {

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    Set<List<Integer>> set = new HashSet<>();
    int n;
    boolean[] flag;
    int[] nums;

    public List<List<Integer>> permuteUnique(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        this.flag = new boolean[n];
        dfs(0);
        return ans;
    }

    public void dfs(int deep) {
        if (path.size() == n && !set.contains(path)) {
            set.add(path);
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
