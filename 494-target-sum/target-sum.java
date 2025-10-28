class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int total = 0;
        for(int i=0; i<n;i++){
            total += nums[i];
        }
        
        if ((total + target) % 2 != 0 || Math.abs(target) > total) {
            return 0;
        }

        int sum = (target + total)/2;
        int[][] dp = new int[n + 1][sum + 1];

        dp[0][0] = 1; // Base case: One way to reach sum 0 with 0 elements
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (nums[i - 1] == 0) {
                    // Zero contributes twice (either +0 or -0)
                    dp[i][j] = dp[i - 1][j] * 2;
                } else if (nums[i - 1] <= j) {
                    // Include current element or exclude it
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
                } else {
                    // Cannot include element → only exclude
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][sum];
    }
}