class Solution {
    public int[] twoSum(int[] nums, int target) {
        // HashMap to store number → its index
        HashMap<Integer, Integer> map = new HashMap<>(); 
        for (int i = 0; i < nums.length; i++) { // Traverse the array
            // Find the value needed to reach the target
            int complement = target - nums[i];

            // If complement already exists, solution found
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            map.put(nums[i], i); // Store current number with its index
        }

        return new int[] {};    // Return empty array if no valid pair exists
    }
}