class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();

        // prefixN[i] = number of 'N' customers in hours [0 .. i-1]
        int[] prefixN = new int[n+1];
        prefixN[0] = 0;
        for(int i=1; i<=n; i++){
            if(customers.charAt(i-1) == 'N') {
                prefixN[i] = prefixN[i-1] + 1; //add penalty for open-while-N
            } else {
                prefixN[i] = prefixN[i-1];
            }
        }

        // suffixY[i] = number of 'Y' customers in hours [i .. n-1]
        int[] suffixY = new int[n+1];
        suffixY[n] = 0;
        for(int i=n-1; i>=0; i--){
            if(customers.charAt(i) == 'Y') {
                suffixY[i] = suffixY[i+1] + 1; //add penalty for closed-while-Y
            } else {
                suffixY[i] = suffixY[i+1];
            }
        }

        int minPenalty = Integer.MAX_VALUE;
        int minHour    = 0; // best closing hour
        for(int i=0; i<=n; i++){ // Try closing at every hour from 0 to n
            // Total penalty = open-while-N + closed-while-Y
            int penalty = prefixN[i] + suffixY[i];
            if (penalty < minPenalty) { // Update min hour
                minPenalty = penalty;
                minHour = i;
            }
        }
        return minHour;
    }
}