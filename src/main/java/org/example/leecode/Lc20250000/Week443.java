package org.example.leecode.Lc20250000;

public class Week443 {
    public static void main(String[] args) {
        //int[] nums = new int[]{5,3,4,1,3,2};
        //int[] nums = new int[]{1, 2, 4, 6, 7};

        String s = "b", t = "aaaa";
        //String s = "abc", t = "def";
        //System.out.println(Arrays.toString(new Week443().minCosts(nums)));
    }

    public int[] minCosts(int[] cost) {
        int n = cost.length;
        int[] ListA = new int[n];
        ListA[0] = cost[0];
        int max = ListA[0];
        for (int i = 1; i < n; i++) {
            // 比较最大值
            max = Math.min(max, cost[i]);
            ListA[i] = max;
        }
        return ListA;
    }

}
