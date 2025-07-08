package org.example.leecode.Lc20250703;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Lc1353 {
    public static void main(String[] args) {
        Lc1353 lc1353 = new Lc1353();
        lc1353.maxEvents(new int[][]{{3, 4}, {1, 2}, {2, 3}});
    }

    public int maxEvents(int[][] events) {
        int n = events.length;
        int maxDay = 0;
        for (int[] event : events) {
            maxDay = Math.max(maxDay, event[1]);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int ans = 0;
        for (int i = 1, j = 0; i <= maxDay; i++) {
            while (j < n && events[j][0] <= i) {
                pq.offer(events[j][1]);
                j++;
            }
            while (!pq.isEmpty() && pq.peek() < i) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                pq.poll();
                ans++;
            }
        }
        return ans;
    }
}
