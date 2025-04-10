package org.example.leecode.Lc20250407;

import java.util.ArrayList;
import java.util.List;

public class Lc39 {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int[] candidates;
    int target;
    int n;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.target = target;
        this.candidates = candidates;
        this.n = candidates.length;
        dfs(n - 1, 0);
        return ans;
    }

    public void dfs(int i, int t) {
        // 还差多少
        int d = target - t;
        // 剪枝，如果 d < 0则返回(超标了)，如果 i 前面的总和 < d， 则返回
        // (i - 1)
        if (d < 0){
            return;
        }
        // 边界条件
        if (d == 0){
            ans.add(new ArrayList<>(path));
            return;
        }
        // 选自己
        path.add(i);
        dfs(i, t + candidates[i]);
        // 选下一个
        path.add(i);
        dfs(i - 1, t + candidates[i]);


    }
}
