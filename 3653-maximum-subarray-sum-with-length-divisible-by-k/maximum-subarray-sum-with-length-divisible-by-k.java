class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        // Build prefix sum array to get sum of any subarray in O(1)
        long[] prefSum = new long[n];
        prefSum[0] = nums[0];
        for(int i = 1; i < n; i++){
            prefSum[i] = prefSum[i - 1] + nums[i]; // prefix sum update
        }

        long maxSubSum = Long.MIN_VALUE;
        // Try all possible starting points within first k positions
        for(int start = 0; start < k; start++){
            long currSum = 0;
            int i = start;

            // Move in jumps of size k (i, i+k, i+2k...)
            while(i < n && i + k - 1 < n){
                int j = i + k - 1; // end index of current block of size k
                // sum of nums[i..j] using prefix sum
                long subSum = prefSum[j] - (i > 0 ? prefSum[i - 1] : 0);

                // Kadane’s: choose starting fresh vs extending
                currSum = Math.max(subSum, currSum + subSum);
                maxSubSum = Math.max(currSum, maxSubSum); // Track global max

                i += k; // jump to next block of size k
            }
        }
        return maxSubSum;
    }
}