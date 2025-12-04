class Solution {
    public int countTrapezoids(int[][] points) {
        int mod = 1_000_000_007;
        // Count how many points lie on same y-coordinate (horizontal line)
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] p : points){
            int y = p[1];
            map.put(y, map.getOrDefault(y, 0) + 1);
        }

        long result = 0; // total number of trapezoids
        long prevLine = 0; // all previous horizontal lines

        for(int cnt : map.values()){ // Iterate over the hashmap
            // Count horizontal line on this y-line
            long hozLine = (long)cnt * (cnt - 1) / 2;
            // Each horizontal line pairs (2-points) with all previous lines 
            result += hozLine * prevLine;
            result %= mod;
            // Update prefix sum for next iteration
            prevLine = (prevLine + hozLine) % mod;
        }
        return (int)result;
    }
}