class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        // Count how many 1's are already in the array
        int ones = 0;
        for(int num : nums){
            if(num == 1){
                ones++;
            }
        }
        // If array has 1's, only need to convert remaining elements
        if(ones > 0){ //gcd(ele , 1) = 1
            return n - ones;
        }
        
        int minSteps = Integer.MAX_VALUE;
        // Find the smallest num(i,i+1) subarray with GCD = 1
        for(int i = 0; i < n-1; i++){
            int gcd = nums[i];
            for(int j = i+1; j < n; j++){
                gcd = computeGCD(gcd, nums[j]);
                if(gcd == 1){
                    minSteps = Math.min(minSteps, j - i);
                    break;
                }
            }
        }

        // If no subarray gives GCD = 1, impossible -> return -1.
        if(minSteps == Integer.MAX_VALUE){
            return -1;
        }

        // Total = steps to create one '1' + steps to spread it
        return minSteps + (n-1);
    }

    private int computeGCD(int a, int b){ //Helper func to compute gcd
        return b==0 ? a : computeGCD(b , a%b);
    }
}