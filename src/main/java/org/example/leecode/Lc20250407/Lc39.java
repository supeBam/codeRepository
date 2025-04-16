package org.example.leecode.Lc20250407;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lc39 {
    public static void main(String[] args) {
        Lc39 lc39 = new Lc39();
        System.out.println(lc39.combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int[] candidates;
    int target;
    int n;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.target = target;
        this.candidates = candidates;
        this.n = candidates.length;
        Arrays.sort(candidates);
        dfs(n - 1, 0);
        return ans;
    }

    public void dfs(int i, int t) {
        // 还差多少
        int d = target - t;
        // 边界条件
        if (d == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 剪枝，如果 d < 0则返回(超标了)，如果 i 前面的总和 < d， 则返回
        if (d < 0 || i >= n) {
            return;
        }

        int candidate = candidates[i];
        // 选的下则选自己
        path.add(candidate);
        dfs(i, t + candidate);
        path.remove(path.size() - 1);
        // 选不下选下一个
        dfs(i + 1, t);
    }

    // 优化，利用了排序后的性质
    public void dfs2(int i, int t) {
        // 还差多少
        int d = target - t;
        // 边界条件
        if (d == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        // 剪枝，如果 d < 0则返回(超标了)，如果 i 前面的总和 < d， 则返回
        if (d < 0 || i < 0) {
            return;
        }

        int candidate = candidates[i];
        // 选的下则选自己
        path.add(candidate);
        dfs(i, t + candidate);
        path.remove(path.size() - 1);
        // 选不下选下一个
        dfs(i - 1, t);
    }
}
