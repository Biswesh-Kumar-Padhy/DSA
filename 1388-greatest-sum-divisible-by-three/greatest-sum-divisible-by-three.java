class Solution {
    public int solve(int i, int rem, int[] nums, int[][] t){
        if(i>= nums.length){
            if(rem == 0){
                return 0;
            }
            return Integer.MIN_VALUE;
        }
        if(t[i][rem] != -1){
            return t[i][rem];
        }

        int take = nums[i] + solve(i+1, (rem + nums[i]) % 3, nums, t);
        int skip = solve(i+1, rem, nums, t);

        return t[i][rem] = Math.max(take, skip);
    }
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] t = new int[n][3];
        for(int[] row : t){
            Arrays.fill(row, -1);
        }
        return solve(0, 0, nums, t);
    }
}