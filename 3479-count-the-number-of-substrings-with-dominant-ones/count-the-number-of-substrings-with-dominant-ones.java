import java.util.*;

class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        // collect positions of '0'
        ArrayList<Integer> zeroPos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') zeroPos.add(i);
        }

        long answer = 0L;
        int Z = zeroPos.size();
        int B = (int) Math.sqrt(n) + 1; // upper bound for useful z

        // 1) Count substrings that contain zero '0' (i.e., all ones runs)
        for (int i = 0; i < n; ) {
            if (s.charAt(i) == '1') {
                int j = i;
                while (j < n && s.charAt(j) == '1') j++;
                long len = j - i;
                answer += len * (len + 1) / 2; // number of substrings inside this run
                i = j;
            } else {
                i++;
            }
        }

        // 2) For z = 1 .. B (number of zeros in substring), slide over zero blocks
        for (int z = 1; z <= B && z <= Z; z++) {
            long minLen = 1L * z * z + z; // required length >= z^2 + z
            // iterate all windows of z consecutive zeros
            for (int start = 0; start + z - 1 < Z; start++) {
                int firstZero = zeroPos.get(start);
                int lastZero = zeroPos.get(start + z - 1);

                // how many choices for the substring start?
                // it can start anywhere from leftBound .. firstZero
                int leftBound = (start == 0) ? 0 : zeroPos.get(start - 1) + 1;
                int leftChoices = firstZero - leftBound + 1;

                // how many choices for substring end?
                // it can end anywhere from lastZero .. rightBound
                int rightBound = (start + z >= Z) ? n - 1 : zeroPos.get(start + z) - 1;
                int rightChoices = rightBound - lastZero + 1;

                long coreLen = (long) lastZero - firstZero + 1; // length covering zeros themselves
                long requiredExtra = minLen - coreLen; // extra length needed from left + right

                long totalPairs = 1L * leftChoices * rightChoices;
                if (requiredExtra <= 0) {
                    // every start/end pair already meets length requirement
                    answer += totalPairs;
                } else {
                    // count pairs (lExt, rExt) where lExt in [0, leftChoices-1],
                    // rExt in [0, rightChoices-1] and lExt + rExt >= requiredExtra
                    // equivalently subtract pairs with sum < requiredExtra
                    long bad = countPairsWithSumLessThan(leftChoices, rightChoices, (int) requiredExtra);
                    answer += (totalPairs - bad);
                }
            }
        }

        // safe to cast to int because n <= 4e4 => total substrings <= n*(n+1)/2 < 2^31
        return (int) answer;
    }

    /**
     * Count number of pairs (x,y) where:
     *   x in [0..a-1], y in [0..b-1], and x + y < need
     * This uses simple arithmetic (no loops), runs in O(1).
     */
    private long countPairsWithSumLessThan(int a, int b, int need) {
        if (need <= 0) return 0L;
        long A = a - 1L;
        long B = b - 1L;
        long total = 1L * a * b;
        long sMax = need - 1L; // we want sums ≤ sMax

        // If sMax >= A + B then all pairs satisfy x+y <= sMax
        if (sMax >= A + B) return total;

        // We'll count number of pairs with x+y <= sMax (same as < need)
        // The distribution of counts by sum is: 1,2,3,...,min(A,B)+1,
        // then (min(A,B)+1) repeated |A-B| times, then decreasing tail.
        long small = Math.min(A, B);
        long large = Math.max(A, B);

        long count = 0L;
        if (sMax <= small) {
            // sums 0..sMax are in the increasing region: sum_{t=0..sMax} (t+1)
            count = (sMax + 1) * (sMax + 2) / 2;
            return count;
        }

        // full increasing part
        count = (small + 1) * (small + 2) / 2;

        if (sMax <= large) {
            // middle plateau: (sMax - small) sums each contribute (small+1)
            count += (sMax - small) * (small + 1);
            return count;
        }

        // full plateau
        count += (large - small) * (small + 1);

        // decreasing tail: sums from (large+1) .. sMax
        long s1 = large + 1;
        long s2 = sMax;
        long terms = s2 - s1 + 1; // number of sums in decreasing tail
        // For sum = t in this tail, contribution = (A + B - t + 1)
        // Sum over t = s1..s2 of (A+B+1) - t = terms*(A+B+1) - sum(t)
        long sumT = (s1 + s2) * terms / 2;
        long tailSum = terms * (A + B + 1) - sumT;
        count += tailSum;

        return count;
    }
}
