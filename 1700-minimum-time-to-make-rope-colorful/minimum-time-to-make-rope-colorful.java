class Solution {
    public int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        int prevMax = 0; // Stores the previous max time 
        int time = 0; // Total time to remove balloons
         
        for (int i = 0; i < n; i++){
            // If color changes, reset prevMax for new color group
            if(i>0 && colors.charAt(i) != colors.charAt(i - 1)){
                prevMax = 0;
            }
            int curr = neededTime[i];
            // If curr balloon same as prev → remove the smaller one
            time += Math.min(curr, prevMax);
            // Keep the larger removal time balloon
            prevMax = Math.max(curr, prevMax);
        }
        return time;
        
    }
}