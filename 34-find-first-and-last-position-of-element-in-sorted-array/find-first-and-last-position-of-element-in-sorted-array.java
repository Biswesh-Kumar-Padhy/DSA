class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = binarySearch(nums, target, true);  // Find first occurrence
        int last = binarySearch(nums, target, false);  // Find last occurrence
        return new int[]{first, last};
    }

    // Binary Search to find the first or last occurrence
    //firstIndex = true→ find first occurrence |or| false→ find last occurrence
    static int binarySearch(int[] arr, int target, boolean firstIndex) {
        int left = 0, right = arr.length - 1;
        int ans = -1;   // Stores index if target is found

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (target == arr[mid]) {
                ans = mid;  // store the index
                if (firstIndex) {     // Continue searching in required direction
                    right = mid - 1;  // Move left for first occurrence
                } else {
                    left = mid + 1;  // Move right for last occurrence
                }
            }
            else if (target < arr[mid]) {
                right = mid - 1;     // search left half
            }
            else {
                left = mid + 1;     // search right half
                
            }
        }
        return ans;
    }
}
