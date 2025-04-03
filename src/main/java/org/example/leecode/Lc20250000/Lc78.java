package org.example.leecode.Lc20250000;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.cn/problems/subsets/

/**
 * 标签：回溯算法，子集回溯
 */
public class Lc78 {

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<Integer>();
    int[] nums;


    public static void main(String[] args) {
        Lc78 le78 = new Lc78();
        le78.subsets(new int[]{1,2,3});
    }

    public  List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        dfs(0);
//        dfs2(0);
        return ans;
    }

    // 方法一：从答案角度出发，所有都选
    public void dfs(int index){
        ans.add(new ArrayList<Integer>(path));
        for(int i = index; i < nums.length; i++){
            path.add(nums[i]);
            // 递归，等于套了 num.length 个 for 循环
            dfs(i + 1);
            // 返回添加现场，等于返回到第一个循环开始
            path.remove(path.size() - 1);
        }
    }

    // 方法二
    public void dfs2(int index){
        if(index == nums.length){
            ans.add(new ArrayList<Integer>(path));
            return;
        }
        // 不选的话直接跳到下一个元素
        dfs2(index + 1);

        // 选的话则添加进path集合
        path.add(nums[index]);
        dfs2(index + 1);
        // 恢复
        path.remove(path.size() - 1);
    }
}
