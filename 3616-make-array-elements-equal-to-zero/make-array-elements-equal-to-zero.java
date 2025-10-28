// class Solution {
//     public int countValidSelections(int[] nums) {
        
//     }
// }
public class Solution {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int count = 0;

        // Try each index where nums[i] == 0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                // Try moving left (-1) and right (+1)
                if (isValid(nums, i, -1)) count++;
                if (isValid(nums, i, 1)) count++;
            }
        }
        return count;
    }

    private boolean isValid(int[] nums, int start, int direction) {
        int n = nums.length;
        int[] arr = nums.clone();  // Work with a copy
        int curr = start;

        while (curr >= 0 && curr < n) {
            if (arr[curr] == 0) {
                curr += direction;
            } else {
                arr[curr]--;
                direction = -direction; // reverse direction
                curr += direction;
            }
        }

        // Check if all elements became 0
        for (int num : arr) {
            if (num != 0) return false;
        }
        return true;
    }
}
