package org.example.leecode.Lc20250331;

public class Lc2278 {
    public static void main(String[] args) {
        Lc2278 lc2278 = new Lc2278();
        System.out.println(lc2278.percentageLetter("foobar", 'o'));
    }

    public int percentageLetter(String s, char letter) {
        int n = s.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == letter) {
                count++;
            }
        }
        return count * 100 / n ;
    }
}
