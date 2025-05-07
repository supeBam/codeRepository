package org.example.leecode.Lc20250506;

import org.example.algorithm.Dijkstra;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Lc3341 {
    private static final int INF = 0x3f3f3f3f;

    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        // 距离
        int[][] d = new int[n][m];
        // 节点是否被访问过
        boolean[][] visited = new boolean[n][m];

        // 初始化距离和访问状态
        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], INF);
        }
        // 步伐
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        // 起点
        d[0][0] = 0;

        // 最小队列
        PriorityQueue<State> q = new PriorityQueue<>();
        q.offer(new State(0, 0, 0));

        while (!q.isEmpty()) {
            State cur = q.poll();
            // 如果已经访问过，跳过
            if (visited[cur.x][cur.y]) {
                continue;
            }
            // 标记为已访问
            visited[cur.x][cur.y] = true;
            // 遍历四个方向
            for (int[] dir : dirs) {
                int nx = cur.x + dir[0];
                int ny = cur.y + dir[1];
                // 超出范围，下一个方向
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                // 计算到达位置的距离
                int dist = Math.max(d[cur.x][cur.y], moveTime[nx][ny]) + 1;
                // 更新最小距离
                if (dist < d[nx][ny]) {
                    d[nx][ny] = dist;
                    q.offer(new State(nx, ny, dist));
                }
            }
        }
        return d[n - 1][m - 1];
    }

    static class State implements Comparable<State> {
        int x, y, dis;

        State(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.dis, other.dis);
        }
    }


    public static void main(String[] args) {
        int[][] graph = {
                {0, 4},
                {4, 4}
        };
        Lc3341 lc3341 = new Lc3341();
        System.out.println(lc3341.minTimeToReach(graph));
    }
}
