public class Solution {
    public int subarraySum(int[] nums, int k) {
        // HashMap to store prefixSum → frequency of that prefix sum
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1); // Initialize with prefix sum 0 occurring once

        int count = 0; // To count number of subarrays with sum = k
        int sum = 0;   // Prefix sum variable

        // Traverse the array
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j]; // Update prefix sum with current element

            // Check if (current_sum - k) exists in map
            // If yes, it means a subarray ending at this index has sum = k
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k); // Add how many times we've seen (sum-k)
            }

            // Update the prefix sum
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}