package org.example.leecode.Lc20250407;

import java.util.Objects;

import static cn.hutool.poi.excel.sax.AttributeName.s;

public class Lc2999 {
    public static void main(String[] args) {
        Lc2999 lc2999 = new Lc2999();
        // System.out.println(lc2999.numberOfPowerfulInt(1, 6000, 4, "124"));
        // System.out.println(lc2999.numberOfPowerfulInt(15, 215, 6, "10"));
        // System.out.println(lc2999.numberOfPowerfulInt(1, 971, 9, "72"));
        // System.out.println(lc2999.numberOfPowerfulInt(1, 971, 9, "27"));
        System.out.println(lc2999.numberOfPowerfulInt(20, 1159, 5, "20"));
    }

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        if (finish < Long.parseLong(s)) {
            return 0;
        }

        long ans = 0;
        /**
         * 1. 后缀需要满足 s
         * 2. 每一位数需要 < limit
         * 3. 在区间 [start, finish] 内
         *
         * 如果第一个最小后缀满足， 则高位 + 1, 如果不满足，则后续都不满足
         */
        // 第一个后缀数(开始数)
        long startLong = Long.parseLong(s);

        // 一开始就在区间内，提高位数
        if (startLong >= start){
            ans++;
            startLong = addMaxBit(startLong);
        }
        // 如果他不在范围开始点，获取第一个在范围内的数
        while (start > startLong){
            startLong = addMaxBit(startLong);
        }
        // 获取finish 的最高位，判断f的最高位 是否大于 当前值的最高位
        Long finishBit = maxBit(finish);
        Long startBit = maxBit(startLong);
        // finish 位数 和 第一个满足后缀的 位数
        Long fCount = bitCount(finish);
        Long sCount = bitCount(startLong);

        // 在范围内 做计算结果
        while (start <= startLong && startLong <= finish){
            if (Objects.equals(fCount, sCount) && limit > finishBit ){
                ans += Math.max(finishBit, startBit);
            }else {
                ans += limit;
                // 如果当前位数得到 > finish 则 ans--
                int i = String.valueOf(startLong).length() - 1;
                long maxValue = (startLong % (long)Math.pow(10, i)) + (long)Math.pow(10, i) * limit;
                if (maxValue > finish){
                    ans--;
                }
            }
            // 增加个高位
            startLong = addMaxBit(startLong);
            // 获取当前位数 的 最大值后缀

            sCount++;
        }


        return ans;
    }

    // 求最高位 数字
    public Long maxBit(Long l){
        String s = String.valueOf(l);
        return (long) (l / Math.pow(10, s.length() - 1));
    }

    // 增加个高位
    public Long addMaxBit(Long l){
        return (long) Math.pow(10, String.valueOf(l).length()) + l;
    }

    public Long maxBitSuffer(Long l, int limit){
        return (long) Math.pow(10, String.valueOf(l).length()) * limit;
    }

    // 位数
    public Long bitCount(Long l){
        String s = String.valueOf(l);
        return (long) s.length();
    }


}
