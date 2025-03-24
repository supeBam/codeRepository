package org.example.entity;

/**
 * 并查集模板
 */
public class UnionFind {

    private final int[] parent; // 父节点
    public int count; // 连通分量个数

    // 初始化
    public UnionFind(int n) {
        this.parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // 路径压缩 （找到根节点）
    public int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    // 合并
    public void union(int from, int to) {
        int f = find(from);
        int t = find(to);
        if (f == t) {
            return;
        }
        parent[f] = t;
        count--;
    }
}
