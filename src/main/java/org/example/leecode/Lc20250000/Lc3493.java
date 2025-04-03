package org.example.leecode.Lc20250000;

import org.example.entity.UnionFind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Lc3493 {

    public int numberOfComponents(int[][] properties, int k) {
        int n = properties.length;
        int m = properties[0].length;
        // 每行的节点数组，set[]去重
        Set<Integer>[] sets = new HashSet[n];
        Arrays.setAll(sets, i -> new HashSet<>(m));
        for (int i = 0; i < n; i++) {
            for (int x : properties[i]) {
                sets[i].add(x);
            }
        }
        // 构建并查集
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            // 每个节点的数组
            Set<Integer> set = sets[i];
            // 遍历所有行
            for (int j = 0; j < i; j++) {
                int cnt = 0;
                for (int x : set) {
                    if (sets[j].contains(x)) {
                        cnt++;
                    }
                }
                if (cnt >= k) {
                    unionFind.union(j, i);
                }
            }
        }
        return unionFind.count;
    }
}
