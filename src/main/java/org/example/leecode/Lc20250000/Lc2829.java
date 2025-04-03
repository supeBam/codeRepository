package org.example.leecode.Lc20250000;

// https://leetcode.cn/problems/determine-the-minimum-sum-of-a-k-avoiding-array/description/?envType=daily-question&envId=2025-03-26
public class Lc2829 {
    /**
     * 如果 k = 10
     * 则 {1,2,3,4,5,6,7,8,9} 都可以相加
     * 且他们首尾相加为 k
     * 因为要和最小 所以每次只要取 [1, k/2]即可
     * 如果 n > k / 2 ， 则取 2/k + k开始顺数  n - k/2
     * 如果 n < k / 2 ,  则直接截断 n
     * 优化，不用数组
     */
    public int minimumSum(int n, int k) {
        int mid = k / 2;
        int ans = 0;
        int i = 1;
        while (mid > 0 && n > 0) {
            ans += i;
            i++;
            mid--;
            n--;
        }
        if (n == 0) {
            return ans;
        }
        i = k;
        while (n > 0) {
            ans += i;
            i++;
            n--;
        }
        return ans;
    }
}
