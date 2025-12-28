class Solution {
    public int countNegatives(int[][] grid) {
        int cnt = 0;
        for(int[] row : grid){
            for(int val : row){
                if(val < 0){
                    cnt++;
                }
            }
        }
        return cnt;
    }
}