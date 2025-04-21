package org.example.leecode.Lc20250414;

import java.util.HashMap;
import java.util.Map;

public class Lc781 {
    public static void main(String[] args) {
        Lc781 lc781 = new Lc781();
        System.out.println(lc781.numRabbits(new int[]{0,0,1,1,1}));
    }
    /**
     * 求最少数量的兔子
     * 求相同的个数有几个：
     *      1、如果相同个数只有 1 个， 则代表颜色相同的为 1 + 个数
     *      2、相同个数 > 1的情况
     *          1、如果 个数 = 元素 + 1   则 颜色相同 为 元素 + 1
     *          2、如果 个数 < 元素 + 1  则 颜色相同 为 元素 + 1   (有兔子不在数组内)
     *          3、如果 个数 > 元素 + 1    则 多出来的 代表其他颜色，遵循前两个规则
     *      3、 元素为0，则数量 + 1
     *
     */
    public int numRabbits(int[] answers) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer count = entry.getValue();
            if (key == 0) {
                ans += count;
            } else if (count <= key + 1) {
                ans += key + 1;
            } else {
                // 分组
                int group = count / (key + 1);
                if (count % (key + 1) != 0) {
                    group++;
                }
                ans += group * (key + 1);
            }
        }
        return ans;
    }
}
