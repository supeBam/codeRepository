package org.example.leecode.Lc20250414;

import java.util.*;

public class Lc40 {
    public static void main(String[] args) {
        Lc40 lc40 = new Lc40();
        // System.out.println(lc40.combinationSum2(new int[]{10,1,2,7,6,1,5},8));
        System.out.println(lc40.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }

    int[] candidates;
    int target;
    int n;
    Set<List<Integer>> set = new HashSet<>();
    boolean[] flag;
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        Arrays.sort(candidates);
        this.candidates = candidates;
        this.target = target;
        this.n = candidates.length;
        this.flag = new boolean[n];

        dfs(0, 0);
        return ans;
    }

    public void dfs(int deep, int sum) {
        // 边界条件
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = deep; i < n; i++) {
            if (sum > target) {
                break;
            }
            if (i > deep && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            dfs(i + 1, sum + candidates[i]);
            path.remove(path.size() - 1);
        }
    }
}
