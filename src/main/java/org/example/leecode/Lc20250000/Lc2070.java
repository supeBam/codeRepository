package org.example.leecode.Lc20250000;

import java.util.Arrays;

/**
 * 2070. 每一个查询的最大美丽值
 * https://leetcode.cn/problems/most-beautiful-item-for-each-query/description/?envType=daily-question&envId=2025-03-09
 */
public class Lc2070 {

    public static void main(String[] args) {
        Lc2070 le2070 = new Lc2070();
        int[][] array = {
                {1, 2},
                {3, 5},
                {3, 2},
                {2, 4},
                {5, 6},

        };
//        le2070.maximumBeauty(array, new int[]{1, 2, 3, 4, 5, 6});
        le2070.maximumBeauty(array, new int[]{6, 5, 4, 3, 2, 1});
        System.out.println(Arrays.deepToString(array));
    }

    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> a[0] - b[0]);
        Integer[] idx = new Integer[queries.length];
        Arrays.setAll(idx, i -> i);
        // 根据queries的顺序 来 改变 idx数组的顺序
        Arrays.sort(idx, (i, j) -> queries[i] - queries[j]);

        int[] ans = new int[queries.length];
        int maxBeauty = 0;
        int j = 0;
        for (int i : idx) {
            int q = queries[i];
            // 增量地遍历满足 queries[i-1] < price <= queries[i] 的物品
            while (j < items.length && items[j][0] <= q) {
                maxBeauty = Math.max(maxBeauty, items[j][1]);
                j++;
            }
            ans[i] = maxBeauty;
        }
        return ans;

    }
}
