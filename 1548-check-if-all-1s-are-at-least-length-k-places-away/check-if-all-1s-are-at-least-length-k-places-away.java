// Approach 2
class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int steps = 0; // Tracks remaining required distance before next '1'

        for (int n : nums) {
            if (n == 0) {
                steps -= 1; // Move one step closer to allowing another '1'
            }
            else {
                if (steps <= 0) {
                    // '1' is valid here, reset required gap
                    steps = k;
                } else {
                    // Found a '1' too early → gap < k
                    return false;
                }
            }
        }
        return true; // All 1s are properly spaced
    }
}