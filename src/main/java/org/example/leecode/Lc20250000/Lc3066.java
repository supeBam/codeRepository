package org.example.leecode.Lc20250000;

import java.util.PriorityQueue;
import java.util.Queue;

public class Lc3066 {
    public int minOperations(int[] nums, int k) {
        int ans = 0;
        Queue<Long> pq = new PriorityQueue<>();
        for (long num : nums) {
            pq.offer(num);
        }
        while (pq.peek() < k) {
            long min = pq.poll();
            long max = pq.poll();
            pq.offer((min << 1) + max);
            ans++;
        }
        return ans;
    }
}
