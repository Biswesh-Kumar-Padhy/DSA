class Solution {
    private int[][][] dp;

    public int solve(int[][] count, int m, int n, int idx){
        if(idx >= count.length || (m == 0 && n == 0)){
            return 0;
        }

        if (dp[m][n][idx] != -1){
            return dp[m][n][idx];
        }

        int include = 0;
        if(count[idx][0] <= m && count[idx][1] <= n){
            include = 1 + solve(count, m-count[idx][0], n-count[idx][1], idx+1);
        }
        
        int exclude = solve(count, m, n, idx + 1);
        return dp[m][n][idx] = Math.max(include, exclude);
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int N = strs.length;
        int[][] cnt = new int[N][2];

        for (int i = 0; i < N; i++){
            int zeroes = 0, ones = 0;
            for(char ch : strs[i].toCharArray()){
                if(ch == '1') ones++;
                else zeroes++;
            }
            cnt[i][0] = zeroes;
            cnt[i][1] = ones;
        }

        dp = new int[m + 1][n + 1][N];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return solve(cnt, m, n, 0);
    }
}