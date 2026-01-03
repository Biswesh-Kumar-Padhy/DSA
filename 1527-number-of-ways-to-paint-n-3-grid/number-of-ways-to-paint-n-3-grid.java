class Solution {
    int M = 1_000_000_007;
    int[][] t;  // DP table: t[rowsLeft][prevPattern]

    // All valid color patterns for one row (no adjacent same colors)
    String[] states = {"RYG", "RYR", "RGY", "RGR","YRG", "YGR", "YGY", "YRY",
    "GRY", "GYR", "GRG", "GYG"};

    public int numOfWays(int n) {
        t = new int[n][12];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 12; j++) {
                t[i][j] = -1;
            }
        }

        int result = 0;
        // Choose each possible pattern for the first row
        for (int i = 0; i < 12; i++) {
            result = (result + solve(n - 1, i)) % M;
        }

        return result;
    }

    // DP: count ways to color remaining n rows, given previous row = prev
    int solve(int n, int prev) {
        if (n == 0) return 1;   //Base Case: All rows filled
        if (t[n][prev] != -1)   return t[n][prev];

        int result = 0;
        String prevPat = states[prev];

        // Try all possible current row patterns
        for (int curr = 0; curr < 12; curr++) {
            if (curr == prev)   continue; // Same pattern not allowed

            String currPat = states[curr];
            boolean conflict = false;

            // Check vertical conflicts (same color in same column)
            for (int col = 0; col < 3; col++) {
                if (currPat.charAt(col) == prevPat.charAt(col)) {
                    conflict = true;
                    break;
                }
            }

            if (!conflict) {    // If no conflict, recurse
                result = (result + solve(n - 1, curr)) % M;
            }
        }

        return t[n][prev] = result; // Memoize result
    } 
}