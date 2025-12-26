class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        long result = 0;
        int count = 0;
        Arrays.sort(happiness);

        for (int i = happiness.length - 1; i>=0 && k>0; i--, k--){
            int val = happiness[i] - count;
            if(val < 0) break; // stop if no positive contribution
            result += val;
            count ++;
        }
        return result;
    }
}