class Solution {
    public int minimumOneBitOperations(int n) {
        int binary = 0;
        // Continue while n has set(1) bits
        while (n > 0) {
            // XOR accumulates the toggling pattern for current bit level
            binary = binary ^ n;  // b3 =  g0 ^ g1 ^ g3 (gray code to binary)
            n = n >> 1;    // Right shift to move to next higher bit
        }

        return binary;     // Final XOR value gives minimum operations      
    }
}