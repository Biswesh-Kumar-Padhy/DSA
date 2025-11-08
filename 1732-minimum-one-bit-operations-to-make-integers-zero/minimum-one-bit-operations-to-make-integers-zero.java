class Solution {
    public int minimumOneBitOperations(int n) {
        int ans = 0;
        // Continue while n has set(1) bits
        while (n > 0) {
            // XOR accumulates the toggling pattern for current bit level
            ans = ans ^ n;  // b3 =  g0 ^ g1 ^ g3 (gray code to binary)
            n = n >> 1;    // Right shift to move to next higher bit
        }

        return ans;     // Final XOR value gives minimum operations      
    }
}