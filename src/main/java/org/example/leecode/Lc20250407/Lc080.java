package org.example.leecode.Lc20250407;

import java.util.ArrayList;
import java.util.List;

public class Lc080 {
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> inList = new ArrayList<>();
    int k;
    public List<List<Integer>> combine(int n, int k) {
        this.k = k;
        dfs(n);
        return list;
    }

    public void dfs(int i) {
        // 剩余多少个 可选元素
        int item = k - inList.size();
        if (item == 0) {
            list.add(new ArrayList<>(inList));
            return;
        }
        // 如果剩余所有的数 不足 k 个，则剪枝
        if(i > item){
            // 不选
            dfs(i - 1);
        }
        // 选
        inList.add(i);
        dfs(i - 1);

        // 还原
        inList.remove(inList.size() - 1);
    }

}
