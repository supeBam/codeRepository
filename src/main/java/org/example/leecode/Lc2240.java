package org.example.leecode;

public class Lc2240 {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        // 减少时间复杂度，先买贵的
        if (cost1 < cost2) {
            return waysToBuyPensPencils(total, cost2, cost1);
        }
        long ans = 0;
        int cnt = 0;
        // 枚举
        while (cost1 * cnt <= total) {
            ans += (total - (long) cost1 * cnt) / cost2 + 1;
            cnt++;
        }
        return ans;
    }
}
