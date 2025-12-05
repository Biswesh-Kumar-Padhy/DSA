class Solution {
    public long maxSum(int[][] grid, int[] limits, int k) {
        // List of pairs {value, rowIndex}
        List<int[]> pairs = new ArrayList<>(); 
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                pairs.add(new int[]{grid[i][j], i}); // store value + row
            }
        }
        pairs.sort((a, b) -> Integer.compare(b[0], a[0])); // sort descending 

        long maxSum = 0, len = 0;
        // Greedily pick the largest values while checking row limits
        for(int[] p : pairs){
            if(len == k)    break; // stop once we have taken k elements

            int key = p[0], row = p[1];
            if(limits[row] > 0){
                maxSum += key; // add the value
                limits[row]--; // reduce that row limit
                len++; // increment total count
            }
        }
        return maxSum;
    }
}