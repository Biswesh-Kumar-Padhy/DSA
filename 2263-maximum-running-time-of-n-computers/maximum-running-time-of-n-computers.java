class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long sum = 0;
        for(int b : batteries)  sum += b;   // total battery time

        // Each computer can run at most sum/n minutes
        long low = 0, high = sum / n;
        while(low < high){
            long mid = (low + high + 1) / 2;
            long total = 0;

            for(int b : batteries){
                // total usable time if each battery can contribute up to mid
                total += Math.min(mid, b); 
            }

            // If total supply is enough to run all n computers for mid minutes
            if(total >= mid * n){ 
                low = mid;  // mid is feasible → try higher(mid) time
            } else{
                high = mid - 1; // mid not feasible → try lower time
            }
        }
        return low; // maximum feasible runtime
    }
}