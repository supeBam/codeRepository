package org.example.leecode.Lc20250512;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Lc75 {

    public void sortColors(int[] nums) {
        LinkedList<Integer> list = new LinkedList<>();
        int index = 0;
        for (int num : nums) {
            if (num == 0) {
                list.addFirst(num);
                index++;
            } else if (num == 2) {
                list.addLast(num);
            } else {
                list.add(index, num);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = list.get(i);
        }
    }


    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Lc75 lc75 = new Lc75();
        // int[] nums = {2, 0, 2, 1, 1, 0};
        int[] nums = {1, 0, 2};
        // int[] nums = {2, 1};
        lc75.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
