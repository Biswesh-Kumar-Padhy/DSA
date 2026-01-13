class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean contains1 = false;

         // Step 1: Check if 1 exists and replace invalid numbers with 1
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1)   contains1 = true;               // track if 1 is present
            if (nums[i] <= 0 || nums[i] > n)    nums[i] = 1;    // keep values in range [1, n]
        }
        if(!contains1)  return 1;   // If 1 is missing, it's the smallest positive

        // Step 2: mark presence using index
        for(int i = 0; i < n; i++){
            int idx = Math.abs(nums[i]) - 1;   // convert value to index

            if (nums[idx] > 0)
                nums[idx] *= -1;    // negative means present
        }

        // Step 3: First positive index means missing number
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0)
                return i + 1;       // index + 1 is missing
        }
        return n + 1;               // If all 1..n are present, answer is n+1
    }
}