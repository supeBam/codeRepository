package org.example.leecode.Lc20250703;

public class Lc3136 {

    public boolean isValid(String word) {
        char[] c = word.toCharArray();
        int n = c.length;
        if (n < 3) {
            return false;
        }
        String s = "AEIOUaeiou";
        boolean flag1 = false, flag2 = false;
        for (char c1 : c) {
            if (s.contains(String.valueOf(c1))) {
                flag1 = true;
            } else if (!Character.isDigit(c1)) {
                flag2 = true;
            }
        }
        return flag1 && flag2;
    }

    public static void main(String[] args) {
        Lc3136 lc3136 = new Lc3136();
        System.out.println(lc3136.isValid("234Adas"));
    }
}

