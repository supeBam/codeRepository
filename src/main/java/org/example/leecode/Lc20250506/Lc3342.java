package org.example.leecode.Lc20250506;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Lc3342 {

    public static void main(String[] args) {

    }

    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        // 是否走过
        boolean[][] visited = new boolean[n][m];
        // 记录距离
        int[][] dis = new int[n][m];

        int INF = 0x3f3f3f3f;
        // 初始化距离和访问状态
        for (int i = 0; i < n; i++) {
            Arrays.fill(dis[i], INF);
        }
        // 移动
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // 优先队列
        PriorityQueue<State> pq = new PriorityQueue<>();
        pq.offer(new State(0, 0, 0));
        while (!pq.isEmpty()) {
            State cur = pq.poll();

            // 访问过 返回
            if (visited[cur.x][cur.y]) {
                continue;
            }
            // 标记
            visited[cur.x][cur.y] = true;
            // 遍历四个方向
            for (int[] dir : dirs) {
                int nx = cur.x + dir[0];
                int ny = cur.y + dir[1];

                // 超出距离 返回
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                // 计算到达的距离
                int dist = Math.max(cur.dis, moveTime[nx][ny]) + (cur.x + cur.y) % 2 + 1;

                // 更新最小距离
                if (dist < dis[nx][ny]) {
                    dis[nx][ny] = dist;
                    pq.offer(new State(nx, ny, dist));
                }
            }
        }
        return dis[n - 1][m - 1];
    }

    static class State implements Comparable<State> {
        int x;
        int y;
        int dis;

        State(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(State o) {
            return Integer.compare(this.dis, o.dis);
        }
    }
}
