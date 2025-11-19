class Solution {
    public int findFinalValue(int[] nums, int original) {
        // Store all numbers in a set for quick lookup
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        // Keep doubling original while it exists in the set
        while (set.contains(original)) {
            original *= 2;
        }
        return original;
    }
}