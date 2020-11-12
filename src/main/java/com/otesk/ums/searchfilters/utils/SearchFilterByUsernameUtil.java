package com.otesk.ums.searchfilters.utils;

import java.util.Arrays;

public class SearchFilterByUsernameUtil {
    public static int calculateLevenshteinDistance(String str1, String str2) {
        int[][] matrix = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0) matrix[i][j] = j;
                else if (j == 0) matrix[i][j] = i;
                else {
                    matrix[i][j] = min(
                            matrix[i - 1][j - 1] + costOfSubstitution(str1.charAt(i - 1), str2.charAt(j - 1)),
                            matrix[i - 1][j] + 1,
                            matrix[i][j - 1] + 1);
                }
            }
        }

        return matrix[str1.length()][str2.length()];
    }

    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }
}
