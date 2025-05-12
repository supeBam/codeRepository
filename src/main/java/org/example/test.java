package org.example;

import java.util.Arrays;

public class test {

    public static void main(String[] args) {
        System.out.println(1 << 4);
        System.out.println(1101 >> 1);

        // 测试git提交， 测试

        System.out.println(96 + '0');

        int[]nums = new int[]{1,3,2,1,2,5};
        System.out.println(Arrays.stream(nums).distinct().count());

        System.out.println('9' - '0' + '1');
    }
}
