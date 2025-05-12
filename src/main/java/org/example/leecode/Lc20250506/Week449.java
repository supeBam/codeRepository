package org.example.leecode.Lc20250506;


import java.util.*;
import java.util.stream.Collectors;

public class Week449 {
    public static void main(String[] args) {
        Week449 week449 = new Week449();
        // System.out.println(week449.minDeletion("abc", 2));
        // System.out.println(week449.minDeletion("aabb", 2));
        // System.out.println(week449.minDeletion("yyyzz", 1));
        // System.out.println(week449.minDeletion("adx", 1));
        // System.out.println(week449.canPartitionGrid(new int[][]{{1, 4}, {2,3 }}));
        // System.out.println(week449.canPartitionGrid(new int[][]{{1, 3}, {2,4 }}));
        // System.out.println(week449.canPartitionGrid(new int[][]{{28443},{33959}}));
        System.out.println(week449.maxScore( 7, new int[][]{{0, 1}, {1, 2}, {2, 0}, {3, 4}, {4, 5}, {5, 6}}));
    }

    public int minDeletion(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            map.merge(c, 1, Integer::sum);
        }
        if (map.size() <= k) {
            return 0;
        }
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        int ans = 0;
        for (int i = 0; i < entryList.size() - k; i++) {
            ans += entryList.get(i).getValue();
        }
        return ans;
    }

    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        for (int i = 0; i < m; i++) {
            row[i] = Arrays.stream(grid[i]).sum();
        }
        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int[] ints : grid) {
                sum += ints[j];
            }
            col[j] = sum;
        }
        // 行 前缀和
        int [] rowPrefixSum = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            rowPrefixSum[i] = rowPrefixSum[i - 1] + row[i - 1];
        }
        // 对 前缀和数组进行分割，遍历分割两个区域，看是否存在相同情况
        for (int i = 1; i <= m; i++) {
            if (rowPrefixSum[i] == rowPrefixSum[m] - rowPrefixSum[i]) {
                return true;
            }
        }
        // 列 前缀和
        int [] colPrefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            colPrefixSum[i] = colPrefixSum[i - 1] + col[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            if (colPrefixSum[i] == colPrefixSum[n] - colPrefixSum[i]) {
                return true;
            }
        }
        return false;
    }



    public long maxScore(int n, int[][] edges) {
        UnionFind449 unionFind449 = new UnionFind449(n);
        // 计算每个节点的边数
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] edge : edges) {
            unionFind449.union(edge[0], edge[1]);
            map.merge(edge[0], 1, Integer::sum);
            map.merge(edge[1], 1, Integer::sum);
        }

        System.out.println(Arrays.toString(unionFind449.parent));
        System.out.println(unionFind449.count);

        return 0;
    }

    public class UnionFind449 {

        private final int[] parent; // 父节点
        public int count; // 连通分量个数

        // 初始化
        public UnionFind449(int n) {
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
}
