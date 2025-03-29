package org.example.leecode;

//https://leetcode.cn/problems/longest-cycle-in-a-graph/description/?envType=daily-question&envId=2025-03-29
public class Lc2360 {

    public static void main(String[] args) {
        Lc2360 lc2360 = new Lc2360();
        int[] nms = new int[]{3,3,4,2,3};

        System.out.println(lc2360.longestCycle(nms));
    }

    public int longestCycle(int[] edges) {
        int n = edges.length;
        int[] time = new int[n];
        int ans = -1;
        int curTime = 1;
        for (int i = 0; i < n; i++) {
            // 当前时间，从1开始
            int startTime = curTime;
            // 起点位置（枚举每个起点位置）
            int index = i;
            while (edges[index] != -1 && time[index] == 0) {
                // 开始, time[index] 表示到达index需要花费多少时间
                time[index] = curTime;
                curTime++;
                index = edges[index];
            }
            // 当有走过重复的位置做记录, 如果走过则会有到达过这里的时间
            if (edges[index] != -1 && time[index] >= startTime) {
                // 总路程花费的时间 - 到达重复位置的时间（最后的位置index）
                ans = Math.max(ans, curTime - time[index]);
            }
        }
        return ans;
    }
}
