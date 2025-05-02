package org.example.leecode.Lc20250428;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Lc838 {

    /**
     * 根据题意，有四种情况：
     * <p>
     * L...L：中间的点全部变成 L。
     * R...R：中间的点全部变成 R。
     * R...L：前一半的点全部变成 R，后一半的点全部变成 L。特别地，如果有奇数个点，则正中间的点不变。
     * L...R：不变。
     */
    public String pushDominoes(String dominoes) {
        char[] charArray = ("L" + dominoes + "R").toCharArray();
        // 记录上个点的索引
        int pre = 0;
        for (int i = 1; i < charArray.length; i++) {
            if (charArray[i] == '.') {
                continue;
            }
            if (charArray[i] == charArray[pre]) {
                Arrays.fill(charArray, pre + 1, i, charArray[i]);
            } else if (charArray[i] == 'L') {
                // 前半部分为R
                Arrays.fill(charArray, pre + 1, (pre + i + 1) / 2, 'R');
                // 后半部分为L
                Arrays.fill(charArray, (pre + i) / 2 + 1, i, 'L');
            }
            pre = i;
        }

        return new String(charArray, 1, charArray.length - 2);
    }
}
