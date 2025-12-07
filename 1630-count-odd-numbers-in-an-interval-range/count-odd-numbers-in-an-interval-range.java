class Solution {
    public int countOdds(int low, int high) {
        // (high + 1) / 2 gives total odd numbers from 0 to high
        // low / 2 gives total odd numbers from 0 to low-1
        return (high + 1) / 2 - (low / 2);
    }
}