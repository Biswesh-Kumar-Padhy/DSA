class Solution {
    public long maxSum(int[][] grid, int[] limits, int k) {
        PriorityQueue<int[]> pairs = new PriorityQueue<>(
            (a, b) -> b[0] - a[0]  // max-heap by value that sort descending 
        );
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                pairs.offer(new int[]{grid[i][j], i}); // store value + row
            }
        }

        long maxSum = 0, len = 0;
        // Greedily pick the largest values while checking row limits
        while (len < k && !pairs.isEmpty()){
            int[] top = pairs.poll();   // get max value
            int value = top[0], row = top[1];

            if(limits[row] > 0){
                maxSum += value; // add the value
                limits[row]--; // reduce that row limit
                len++; // increment total count
            }
        }
        return maxSum;
    }
}