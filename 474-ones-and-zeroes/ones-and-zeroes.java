class Solution {
    private int[][][] dp; // dp[m][n][idx] stores max strings using m zeros, n ones starting from index idx

    // Recursive function with memoization
    public int solve(int[][] count, int m, int n, int idx){
        // Base case: no more strings or no capacity left
        if(idx >= count.length || (m == 0 && n == 0)){
            return 0;
        }
        // Return memoized result if already computed
        if (dp[m][n][idx] != -1){
            return dp[m][n][idx];
        }

        int include = 0;
        // Option 1: Include current string if possible
        if(count[idx][0] <= m && count[idx][1] <= n){
            include = 1 + solve(count, m - count[idx][0], n - count[idx][1], idx + 1);
        }
        // Option 2: Skip current string
        int exclude = solve(count, m, n, idx + 1);

        // Store and return max of include/exclude
        return dp[m][n][idx] = Math.max(include, exclude);
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int N = strs.length;
        int[][] cnt = new int[N][2]; // stores zero/one count of each string

        // Count zeros and ones for each string
        for (int i = 0; i < N; i++){
            int zeroes = 0, ones = 0;
            for(char ch : strs[i].toCharArray()){
                if(ch == '1') ones++;
                else zeroes++;
            }
            cnt[i][0] = zeroes; // store zero count
            cnt[i][1] = ones;   // store one count
        }

        // Initialize DP array with -1
        dp = new int[m + 1][n + 1][N];
        for (int[][] layer : dp) {
            for (int[] row : layer) {
                Arrays.fill(row, -1);
            }
        }

        // Solve starting from index 0
        return solve(cnt, m, n, 0);
    }
}
