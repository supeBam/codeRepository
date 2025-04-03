package org.example.leecode.Lc20250000;

//https://leetcode.cn/problems/adding-spaces-to-a-string/description/?envType=daily-question&envId=2025-03-30
public class Lc2109 {

    public String addSpaces(String s, int[] spaces) {

        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int space : spaces) {
            sb.append(s, index, space).append(" ");
            index = space;
        }
        sb.append(s, index, s.length());
        return sb.toString();
    }

}
