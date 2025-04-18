package org.example.leecode.Lc20250414;

import java.util.*;

public class Lc39 {
    public static void main(String[] args) {
        org.example.leecode.Lc20250414.Lc39 lc39 = new org.example.leecode.Lc20250414.Lc39();
        System.out.println(lc39.combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    int[] candidates;
    int target;
    int n;
    Set<List<Integer>> set = new HashSet<>();
    boolean[] flag;
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);
        this.candidates = candidates;
        this.target = target;
        this.n = candidates.length;
        this.flag = new boolean[n];

        dfs(0, 0);
        return ans;
    }

    public void dfs(int start, int sum) {
        // 边界条件
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < n; i++) {
            if (sum > target) {
                break;
            }
            path.add(candidates[i]);
            dfs(i, sum + candidates[i]);
            path.remove(path.size() - 1);
        }
    }

}
