class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // Sort by end ascending; if equal end, sort start descending
        Arrays.sort(intervals, (a,b) ->{
            if(a[1] ==  b[1]){
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });

        int p1 = -1, p2 = -1; // last two chosen points
        int result = 0;

        for (int[] interval : intervals){
            int start = interval[0], end = interval[1];

            boolean hasP1 = p1 >= start && p1 <= end; // Is p1 inside interval?
            boolean hasP2 = p2 >= start && p2 <= end; // Is p2 inside interval?

            if(hasP1 && hasP2){
                continue; // interval already has two points
            }
            else if(hasP2){ 
                // only p2 inside → add one new point (end)
                p1 = p2;
                p2 = end;
                result += 1;
            }
            else{
                // none inside → add two points (end−1, end)
                p1 = end - 1;
                p2 = end;
                result += 2;
            }
        }
        return result;
    }
}