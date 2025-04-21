package org.example.leecode.Lc20250421;

public class Lc2145 {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        // differences = [1,-3,4], lower = 1, upper = 6
        int min = lower;
        int max = upper;
        // 每次找到该值的最大值和最小值的范围
        for (int v : differences) {
            min += v;
            max += v;
            // 如果超过范围则不存在
            if (min > upper || max < lower) {
                return 0;
            }
            // 固定范围
            if (min < lower){
                min = lower;
            }
            if (max > upper){
                max = upper;
            }
        }
        return max - min + 1;
    }
}
