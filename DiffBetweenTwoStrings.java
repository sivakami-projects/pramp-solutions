import java.io.*;
import java.util.*;

class Solution {
    static String[] diffBetweenTwoStrings(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for(int i = 0; i <= word1.length(); i++)
            dp[i][len2] = len1 - i;
        for(int j = 0; j <= word2.length(); j++)
            dp[len1][j] = len2 - j;
      
        for (int i = len1 - 1; i >= 0; i--) 
            for (int j = len2 - 1; j >= 0; j--) 
                if (word1.charAt(i) == word2.charAt(j))
                    dp[i][j] = dp[i + 1][j + 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i][j + 1], dp[i + 1][j]);

        List<String> path = new ArrayList<>();
        int i = 0, j = 0;

        while (i < len1 && j < len2) {
            if (word1.charAt(i) == word2.charAt(j)) {
                path.add(String.valueOf(word1.charAt(i)));
                i++;
                j++;
            } else {
                if (dp[i][j + 1] < dp[i + 1][j]) {
                    path.add("+" + word2.charAt(j));
                    j++;
                } else {
                    path.add("-" + word1.charAt(i));
                    i++;
                }
            }
        }


        while (j < len2) {
            path.add("+" + word2.charAt(j));
            j++;
        }

        //Collections.reverse(path);
        return path.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String word1 = "ABCDEFG";
        String word2 = "ABDFFGH";
        String[] result = diffBetweenTwoStrings(word1, word2);
        System.out.println(Arrays.toString(result));
    }
}
