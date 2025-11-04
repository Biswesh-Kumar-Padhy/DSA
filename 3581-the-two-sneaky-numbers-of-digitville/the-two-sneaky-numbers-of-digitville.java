class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        int n = nums.length - 2;
        int XOR = 0;

        for (int num : nums){
            XOR ^= num;
        }
        for (int num = 0; num <= n-1; num++){
            XOR ^= num;
        }

        // Find rightmost set bit
        int trailZeroCount = Integer.numberOfTrailingZeros(XOR);
        int mask = 1 << trailZeroCount;
        int G1 = 0, G2 = 0;

        // Separate into two groups based on mask bit
        for(int num : nums){
            if((num & mask) == 0){
                G1 ^= num;
            }
            else{
                G2 ^=num;
            }
        }
        for (int num = 0; num <= n-1; num++){
            if((num & mask) == 0){
                G1 ^= num;
            }
            else{
                G2 ^=num;
            }
        }
        return new int[]{G1, G2};
    }
}