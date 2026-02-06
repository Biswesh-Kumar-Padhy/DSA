class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int i = 0;  // Left pointer of window
        int maxLen = 1;

        // Expand window using right pointer j
        for (int j = 0; j < n; j++) {
            long maxEl = nums[j];   // Current maximum element
            long minEl = nums[i];   // Current minimum element
            // Shrink window until condition max <= k * min is satisfied
            while (i < j && maxEl > (long) k * minEl) {
                i++;
                minEl = nums[i];
            }
            maxLen = Math.max(maxLen, j - i + 1);   // Update maximum valid window size
        }
        // Minimum removals = total elements - largest valid subarray
        return n - maxLen;
    }
}