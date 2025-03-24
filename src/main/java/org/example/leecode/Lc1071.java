package org.example.leecode;

public class Lc1071 {
    public String gcdOfStrings(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        if (n < m) {
            return gcdOfStrings(str2, str1);
        }
        // str1 > str2
        String s = str2.substring(0, gcd(n, m));
        if (check(str1, s) && check(str2, s)) {
            return s;
        }
        return "";
    }

    // 检查
    public boolean check(String str, String s) {
        int count = str.length() / s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(s);
        }
        return sb.toString().equals(str);
    }


    public int gcd(int a, int b) {
        int ans = a % b;
        // 求最大公因数
        while (ans != 0) {
            a = b;
            b = ans;
            ans = a % b;
        }
        return b;
    }

}
