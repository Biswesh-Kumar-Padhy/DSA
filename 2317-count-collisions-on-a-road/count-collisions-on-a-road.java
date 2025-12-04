class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();
        int left = 0, right = n - 1;

        while(left < n && directions.charAt(left) == 'L'){
            left++; // Skip all cars moving left at the start (they never collide)
        }
        while(right >= 0 && directions.charAt(right) == 'R'){
            right--; // Skip all cars moving right at the end (they never collide)
        }

        int cnt = 0;
        for(int i = left; i <= right; i++){
            // Count only moving cars('L'or'R')
            if(directions.charAt(i) != 'S'){
                cnt++;
            }
        }
        // Total collisions = number of moving cars in the middle segment
        return cnt;
    }
}