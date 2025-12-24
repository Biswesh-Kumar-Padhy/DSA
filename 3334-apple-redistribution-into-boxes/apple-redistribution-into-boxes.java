class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int total = 0;
        int box = 0;
        for (int a : apple){
            total += a;
        }

        Arrays.sort(capacity);
        int n = capacity.length;
        for (int i = n-1; i >= 0; i--){
            if(total <= 0)  break;
            total -= capacity[i];
            box++;
        }
        return box;
    }
}