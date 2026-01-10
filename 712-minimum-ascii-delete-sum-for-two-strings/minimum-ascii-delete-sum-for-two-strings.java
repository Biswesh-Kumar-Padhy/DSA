class Solution {
    int m, n;        // lengths of both strings
    int[][] t;      // t[i][j] = min delete sum for s1[i..] and s2[j..]

    public int minimumDeleteSum(String s1, String s2) {
        m = s1.length();
        n = s2.length();

        t = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                t[i][j] = -1;   // Initialize DP table with -1
            }
        }

        // Start DP from index 0 of both strings
        return solve(s1, s2, 0, 0);
    }

    private int solve(String s1, String s2, int i, int j) {

        if (i >= m && j >= n) return 0; // If both strings are fully processed, no cost left
        if (t[i][j] != -1) return t[i][j];  // Return cached result if already computed

        // If s1 is finished, must delete remaining chars of s2
        if (i >= m) {
            return t[i][j] = s2.charAt(j) + solve(s1, s2, i, j + 1);
        }
        // If s2 is finished, must delete remaining chars of s1
        if (j >= n) {
            return t[i][j] = s1.charAt(i) + solve(s1, s2, i + 1, j);
        }

        // If characters match, no deletion needed here
        if (s1.charAt(i) == s2.charAt(j)) {
            return t[i][j] = solve(s1, s2, i + 1, j + 1);
        }

        int takeS1 = s1.charAt(i) + solve(s1, s2, i + 1, j);    // Option 1: delete s1[i]
        int takeS2 = s2.charAt(j) + solve(s1, s2, i, j + 1);    // Option 2: delete s2[j]

        // Take minimum delete cost
        return t[i][j] = Math.min(takeS1, takeS2);
    }
}
