class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // dp[i][j] = max strings formed using i zeros and j ones
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            int zeros = 0, ones = 0;
            // Count zeros and ones in the current string
            for (char ch : str.toCharArray()) {
                if (ch == '0') zeros++;
                else ones++;
            }

            // Loop backwards so previous states are not overwritten
            for (int zero = m; zero >= zeros; zero--) {
                for (int one = n; one >= ones; one--) {
                    // Either skip the string or take it (if possible)
                    dp[zero][one] = Math.max(dp[zero][one],
                                            dp[zero - zeros][one - ones] + 1);
                }
            }
        }

        // Maximum strings using at most m zeros and n ones
        return dp[m][n];
    }
}
