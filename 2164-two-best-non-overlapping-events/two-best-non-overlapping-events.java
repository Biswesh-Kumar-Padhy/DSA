class Solution {
    private int n;
    private int[][] dp = new int[100001][3];

    public int maxTwoEvents(int[][] events) {
        n = events.length;
        // Sort events by start time
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Start recursion from index 0 with 0 events taken
        return solve(events, 0, 0); 
    }

    private int solve(int[][] events, int i, int count){
        // Base case: picked 2 events or reached end
        if(count == 2 || i >= n )   return 0;
        // Return memoized result
        if (dp[i][count] != -1)     return dp[i][count];

        // Find next event that starts after current event ends
        int nextIdx = binarySearch(events, events[i][1]);
        int take = events[i][2] + solve(events, nextIdx, count + 1);
        int skip = solve(events, i+1, count);

        return dp[i][count] = Math.max(take, skip);
    }

    // BS for the next event start time > current event end time
    private int binarySearch(int[][] events, int endTime){
        int left = 0, right = n - 1, result = n;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if (events[mid][0] > endTime) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}