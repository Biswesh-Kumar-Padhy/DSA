class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();

        // Initial penalty: count of 'Y' (closing at hour 0)
        int penalty = 0;
        for (char c : customers.toCharArray()) {
            if (c == 'Y') penalty++;
        }

        int minPenalty = penalty;
        int minHour = 0;
        for (int i = 0; i < n; i++) { // Try closing at every hour from 0 to n
            if (customers.charAt(i) == 'Y') {
                penalty--;   //desc penalty for closed-while-Y
            } else {
                penalty++;   //inc penalty for open-while-N
            }

            // Update minimum penalty and hour
            if (penalty < minPenalty) {
                minPenalty = penalty;
                minHour = i + 1; // closing after hour i
            }
        }
        return minHour;
    }
}
