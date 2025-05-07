package org.example.algorithm;

import java.util.*;

public class Dijkstra {
    private static final int INF = 0x3f3f3f3f;
    // 图的邻接矩阵表示
    private int[][] graph;
    private int[] dist; // 起点到其他节点的距离
    private boolean[] visited; // 节点是否被访问过

    public Dijkstra(int[][] graph, int start) {
        this.graph = graph;
        this.dist = new int[graph.length];
        this.visited = new boolean[graph.length];

        // 初始化距离和访问状态
        Arrays.fill(dist, INF);
        dist[start] = 0;
        Arrays.fill(visited, false);

        dijkstra(start);
    }

    private void dijkstra(int start) {
        for (int i = 0; i < graph.length; i++) {
            int minDistance = INF;
            int minIndex = -1;

            // 在未访问的节点中选择距离起点最短的节点
            for (int j = 0; j < graph.length; j++) {
                if (!visited[j] && dist[j] < minDistance) {
                    minDistance = dist[j];
                    minIndex = j;
                }
            }

            // 如果所有节点都已访问，则退出循环
            if (minIndex == -1) {
                break;
            }

            // 标记当前节点为已访问
            visited[minIndex] = true;

            // 更新邻居节点的距离值
            for (int j = 0; j < graph.length; j++) {
                if (!visited[j] && graph[minIndex][j] != 0 && dist[minIndex] + graph[minIndex][j] < dist[j]) {
                    dist[j] = dist[minIndex] + graph[minIndex][j];
                }
            }
        }

        // 打印起点到其他节点的最短距离
        for (int i = 0; i < graph.length; i++) {
            System.out.println("起点到节点" + i + "的最短距离为：" + dist[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 7, 9, INF, INF, 14},
                {7, 0, 10, 15, INF, INF},
                {9, 10, 0, 11, INF, 2},
                {INF, 15, 11, 0, 6, INF},
                {INF, INF, INF, 6, 0, 9},
                {14, INF, 2, INF, 9, 0}
        };

        Dijkstra dijkstra = new Dijkstra(graph, 0);
    }
}