package com.fox_lee.yunwen.lolinfimobile_struct.Utility;

/**
 * Created by jili on 5/26/17.
 *
 * all the comm method
 *
 */

public class MyUtility {

    private String getLongestString(String[] array) {
        int maxLength = 0;
        String longestString = null;
        for (String s : array) {
            if (s.length() > maxLength) {
                maxLength = s.length();
                longestString = s;
            }
        }
        return longestString;
    }

}
