package org.example.leecode;

import java.util.Arrays;

//https://leetcode.cn/contest/weekly-contest-442/problems/maximum-containers-on-a-ship/
public class Week442 {
    public static void main(String[] args) {
        Week442 week442 = new Week442();
//        System.out.println(week442.maxContainers(3, 5, 20));
//        int[] skill = {1, 5, 2, 4};
        int[] skill = {3, 5, 3, 9};
//        int[] mana = {5, 1, 4, 2};
        int[] mana = {1, 10, 7, 3};
        System.out.println(week442.minTime(skill, mana));
    }


    // 第一题
    public int maxContainers(int n, int w, int maxWeight) {
        int nums = n * n;
        //理论上最多承受多少
        int weight = w * nums;
        while (weight > maxWeight) {
            nums--;
            weight -= w;
        }
        return nums;
    }


    // 第三题
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        long[] finish = new long[n];
        for (int m : mana) {
            long item = 0;
            // 每个巫师的完成时间
            for (int i = 0; i < n; i++) {
                // 每个巫师的时间必须比上一个药的完成时间晚
                item = Math.max(item, finish[i]) + skill[i] * m;
            }

            // 因为巫师需要立马工作，所以倒数第二个巫师的时间就是最后一个巫师的 - 他的制药时间
            finish[n - 1] = item;
            for (int i = n - 2; i >= 0; i--) {
                finish[i] = finish[i + 1] - skill[i + 1] * m;
            }
        }
        return finish[n - 1];
    }
}
