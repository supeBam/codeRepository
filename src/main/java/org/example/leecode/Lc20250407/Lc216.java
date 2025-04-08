package org.example.leecode.Lc20250407;

import java.util.ArrayList;
import java.util.List;

public class Lc216 {

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int k;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k;
        dfs(9, n);
        return ans;
    }

    public void dfs(int i, int n) {
        // 剩余几个
        int d = k - path.size();
        /**
         *  ((i - d + 1) + i) / 2 * d =  (i * 2 - d + 1) * d / 2
         *  代表 从 i 向前 取 d 个数， 求他们的和， 等差数列求和：最大值 + 最小值 / 2  * 个数
         *  如果 剩下的值 n 比 前d个数求和 还要大， 则剪掉
         */
        if (n < 0 || n > (i * 2 - d + 1) * d / 2){
            return;
        }
        // 边界条件
        if (d == 0 && n == 0){
            ans.add(new ArrayList<>(path));
            return;
        }
        // 不选 剪枝
        if (i > d){
            dfs(i - 1, n);
        }
        // 选
        path.add(i);
        dfs(i - 1, n - i);
        // 退回
        path.remove(path.size() - 1);
    }
}
