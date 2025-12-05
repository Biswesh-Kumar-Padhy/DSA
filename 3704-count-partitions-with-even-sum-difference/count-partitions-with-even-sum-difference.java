class Solution {
    public int countPartitions(int[] nums) {
        int n = nums.length;
        int total = 0;
        for(int num : nums) total += num;

        // If total sum is even ➝ all partitions work (n-1)
        // If total sum is odd  ➝ no partitions work
        return (total % 2 == 0) ? (n - 1) : 0;
    }
}