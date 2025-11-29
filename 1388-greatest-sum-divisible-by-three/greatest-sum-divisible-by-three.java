class Solution {
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        // t[i][rem] stores best sum from index i with remainder rem
        int[][] t = new int[n+1][3];
        for(int[] row : t){ // Initialize with int min
            Arrays.fill(row, Integer.MIN_VALUE);
        }
        // Base case: i == n
        t[n][0] = 0;
        t[n][1] = Integer.MIN_VALUE;
        t[n][2] = Integer.MIN_VALUE;

        for(int i = n-1; i >= 0; i--){
            for(int rem = 0; rem < 3; rem++){
                int currRem = (rem + nums[i]) % 3;
                int take = nums[i] + t[i+1][currRem];
                int skip = t[i+1][rem];

                t[i][rem] = Math.max(take, skip);
            }
        }
        return t[0][0];
    }
}