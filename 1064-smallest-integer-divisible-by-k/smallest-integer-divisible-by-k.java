class Solution {
    public int smallestRepunitDivByK(int k) {
        // Numbers made of 1s (111...) is never  divisible by 2 or 5
        if (k % 2 == 0 || k % 5 == 0){
            return -1;
        }
        int rem = 0;

         // Try lengths from 1 to k (Pigeonhole Principle guarantees result ≤ k)
        for(int length = 1; length <= k; length++){
            rem = (rem * 10 + 1) % k; // rem always stays within [0, k-1]

            if(rem == 0){
                return length; // // divisible → return length
            }
        }
        return -1;
    }
}