package org.example.leecode.Lc20250331;

public class Lc2140 {
    public static void main(String[] args) {
        Lc2140 lc2140 = new Lc2140();
        //int[][] ints = {{3, 2}, {4, 3}, {4, 4}, {2, 5}};
        //int[][] ints = {{1,1},{2,2},{3,3},{4,4},{5,5}};
        int[][] ints = {{12,46},{78,19},{63,15},{79,62},{3,10}};
        //int[][] ints = {{21,5},{92,3},{74,2},{39,4},{58,2},{5,5},{49,4},{65,3}};
        System.out.println(lc2140.mostPoints(ints));
    }
    /**
     * 转移方程:
     * ( i + questions[i][1] ) 下一个坐标位置前一个
     *
     * dp[i] = dp[i + questions[i][1] + 1]
     * dp[0] = questions[0][0]
     *
     */
    public long mostPoints(int[][] questions) {
        // questions = [[3,2],[4,3],[4,4],[2,5]]
        int n = questions.length;
        long[][] dp = new long[n][n];
        //  int[][] ints = {{1,1},{2,2},{3,3},{4,4},{5,5}};
        // [21,5],[92,3],[74,2],[39,4],[58,2],[5,5],[49,4],[65,3]
        // {{12,46},{78,19},{63,15},{79,62},{3,10}};
        for (int i = 0; i < n; i++) {
            dp[i][0] = questions[i][0];
            int j = questions[i][1] + 1 + i;
            while (j < n) {
                dp[i][j] = Math.max(dp[i][j], dp[i][0] + questions[j][0]);
                j++;
            }
        }

        long ans = 0L;
        for (int i = 0; i < n; i++) {
            int j = n - 1;
            while (dp[i][j] == 0 && j > 0){
                j--;
            }
            ans = Math.max(ans, dp[i][j]);
        }
        return ans;
    }
}
