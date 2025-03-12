package org.example.leecode;

import java.util.*;

/**
 * https://leetcode.cn/problems/count-of-substrings-containing-every-vowel-and-k-consonants-i/?envType=daily-question&envId=2025-03-12
 * 3305. 元音辅音字符串计数 I
 */
public class Le3305 {

    public static void main(String[] args) {
        Le3305 le3305 = new Le3305();
//        String s=  "aeiou";
        String s=  "ieaouqqieaouqq";
//        int k = 0;
        int k = 1;
        char[] c = s.toCharArray();
        System.out.println(le3305.f(c, k));
        System.out.println(le3305.f(c, k+1));
    }

    public int countOfSubstrings(String word, int k) {

        int n = word.length();
        // 记录原因字符的个数
        int[] words = new int[n];
        int[] rem = new int[5];
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            int x = word.charAt(i);
            if (x == 'a') {
                words[i] = 0;
            } else if (x == 'e') {
                words[i] = 1;
            } else if (x == 'i') {
                words[i] = 2;
            } else if (x == 'o') {
                words[i] = 3;
            } else if (x == 'u') {
                words[i] = 4;
            } else {
                words[i] = -1;
            }
        }
        int rIndex = 0;
        int ans = 0;
        int time = k;
        for (int i = 0; i < n; i++) {
            // 移动右指针
            for (int j = i; j < n; j++) {
                int x = words[j];
                if (x == -1) {
                    time--;
                } else {
                    set.add(x);
                    rem[x]++;
                }
                //找到了最右边位置
                if (time == 0) {
                    if (j == i){
                        time++;
                    }else {
                        rIndex = j;
                        break;
                    }
                }
               if (j == n - 1){
                   rIndex = j;
               }
            }
            for (int l = i; l < rIndex; l++) {
                if (set.size() == 5){
                    //计数
                    ans++;
                    // 获取最左边的元素
                    int leftValue = words[l];
                    if (leftValue != -1) {
                        rem[leftValue]--;
                        if (rem[leftValue] == 0) {
                            set.clear();
                            i = rIndex - 1;
                            time = k;
                            break;
                        }
                    }
                }else{
                    i = rIndex - 1;
                    time = k;
                }
            }
        }
        return ans;
    }


    private long f(char[] word, int k) {
        long ans = 0;
        // 这里用哈希表实现，替换成数组会更快
        HashMap<Character, Integer> cnt1 = new HashMap<>(); // 每种元音的个数
        int cnt2 = 0; // 辅音个数
        int left = 0;
        for (char b : word) {
            if ("aeiou".indexOf(b) >= 0) {
                cnt1.merge(b, 1, Integer::sum); // ++cnt1[b]
            } else {
                cnt2++;
            }
            while (cnt1.size() == 5 && cnt2 >= k) {
                char out = word[left];
                if ("aeiou".indexOf(out) >= 0) {
                    if (cnt1.merge(out, -1, Integer::sum) == 0) { // --cnt1[out] == 0
                        cnt1.remove(out);
                    }
                } else {
                    cnt2--;
                }
                left++;
            }
            ans += left;
        }
        return ans;
    }

}