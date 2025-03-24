package org.example.leecode;

/**
 * https://leetcode.cn/problems/find-the-k-beauty-of-a-number/?envType=daily-question&envId=2025-03-10
 * 2269. 查找数组的第 K 大整数
 */
public class Lc2269 {

    public static void main(String[] args) {
        Lc2269 le2269 = new Lc2269();
//        int num = 430043;
        int num = 240;
        int k = 2;
//        int k = 2;
        System.out.println(le2269.divisorSubstrings2(num, k));
    }

    // 方法一 滑动窗口
    public int divisorSubstrings(int num, int k) {
        String s = String.valueOf(num);
        int n = s.length();
        int ans = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            // 入窗
            sb.append(s.charAt(i));
            // 窗口长度达到k
            if (i < k - 1) {
                continue;
            }
            // 计算
            String string = sb.substring(i - k + 1, i + 1);
            if (Integer.parseInt(string) != 0 && num % Integer.parseInt(string) == 0) {
                ans++;
            }
        }
        return ans;
    }

    // 方法二， 数学
    public int divisorSubstrings2(int num, int k) {
        int n = num;
        // k决定了多少个 % 多少
        int ans = 0;
        int pow = (int) Math.pow(10, k);
        while (n > 0 &&  ((Math.log10(n) + 1) >= k)) {
            // 获取最右边的k个数
            int t = n % pow;
            // 0 的情况直接去下一个
            if (t == 0) {
                n /= 10;
                continue;
            }
            // 如果满足条件
            if (num % t == 0) {
                ans++;
            }
            // 去除最右边的一个数
            n /= 10;
        }
        return ans;
    }
}
