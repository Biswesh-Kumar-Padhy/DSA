//Imp Q
class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int sum = 0;
        for(int num : nums){ // Calculate total sum and take % p
            sum = (sum + num) % p;
        }

        int target = sum % p;
        // If sum is already divisible by p, no need to remove any subarray
        if(target == 0)    return 0;

        // Map: remainder → latest index where this prefix remainder occurred
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // Initialize with remainder 0 at index -1
        int curr = 0;   // current prefix sum modulo p
        int result = n; // initialize result with max possible length

        for(int j= 0; j < n; j++){
            curr  = (curr + nums[j]) % p; // Build the current prefix remainder

            // Calculate the remainder needed to achieve the target
            int rem = (curr - target + p) % p;
            
            // If the required remainder exists in the map, update the result
            if (map.containsKey(rem)) {
                result = Math.min(result, j - map.get(rem));
            }
            
            map.put(curr, j); // Store the current rem with its index
        }

        return result == n ? -1 : result; // If no valid subarray found, return -1
    }
}