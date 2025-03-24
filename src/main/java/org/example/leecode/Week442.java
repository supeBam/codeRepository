package org.example.leecode;

import org.example.entity.UnionFind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    //第二题
    public int numberOfComponents(int[][] properties, int k) {
        int n = properties.length;
        int m = properties[0].length;
        // 每行的节点数组，set[]去重
        Set<Integer>[] sets = new HashSet[n];
        Arrays.setAll(sets, i -> new HashSet<>(m));
        for (int i = 0; i < n; i++) {
            for (int x : properties[i]) {
                sets[i].add(x);
            }
        }
        // 构建并查集
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            // 每个节点的数组
            Set<Integer> set = sets[i];
            // 遍历所有行
            for (int j = 0; j < i; j++) {
                int cnt = 0;
                for (int x : set) {
                    if (sets[j].contains(x)) {
                        cnt++;
                    }
                }
                if (cnt >= k) {
                    unionFind.union(j, i);
                }
            }
        }
        return unionFind.count;
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
