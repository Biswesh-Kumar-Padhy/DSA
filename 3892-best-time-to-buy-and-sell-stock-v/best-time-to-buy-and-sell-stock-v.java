class Solution {
    public long maximumProfit(int[] prices, int K) {
        int n = prices.length;

        long[][][] t = new long[n + 1][K + 1][3];

        // Base case: i == n
        for (int k = 0; k <= K; k++) {
            t[n][k][0] = 0;
            t[n][k][1] = Long.MIN_VALUE / 2;
            t[n][k][2] = Long.MIN_VALUE / 2;
        }

        // Fill table bottom-up
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0; k <= K; k++) {

                // CASE 0: no open transaction
                t[i][k][0] = t[i + 1][k][0]; // do nothing
                if (k > 0) {
                    t[i][k][0] = Math.max(
                            t[i][k][0],
                            Math.max(
                                    -prices[i] + t[i + 1][k][1], // buy
                                     prices[i] + t[i + 1][k][2]  // short sell
                            )
                    );
                }

                // CASE 1: holding long
                t[i][k][1] = t[i + 1][k][1]; // hold
                if (k > 0) {
                    t[i][k][1] = Math.max(
                            t[i][k][1],
                            prices[i] + t[i + 1][k - 1][0] // sell
                    );
                }

                // CASE 2: holding short
                t[i][k][2] = t[i + 1][k][2]; // hold
                if (k > 0) {
                    t[i][k][2] = Math.max(
                            t[i][k][2],
                            -prices[i] + t[i + 1][k - 1][0] // buy back
                    );
                }
            }
        }

        // Start from day 0, K transactions, no open position
        return t[0][K][0];
    }
}