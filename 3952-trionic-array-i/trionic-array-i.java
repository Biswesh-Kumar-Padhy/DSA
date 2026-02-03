class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int i = 0;
        while(i+1<n && nums[i] < nums[i+1])   i++;  // 1: strictly increasing
        // Increasing part must exist and not consume entire array
        if(i==0 || i==n-1)  return false;

        while(i+1<n && nums[i] > nums[i+1])   i++;  // 2: strictly decreasing
        if(i==n-1)  return false;   // Decreasing part must exist

        while(i+1<n && nums[i] < nums[i+1])   i++;  // 3: strictly increasing again
        return i==n-1;  // Valid only if we consumed the entire array
    }
}