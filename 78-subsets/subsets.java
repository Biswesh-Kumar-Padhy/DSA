class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generate(0, nums, new ArrayList<>(), result);
        return result;

    }

    public void generate(int idx, int[] nums, List<Integer> curr, List<List<Integer>> result) {
        if (idx == nums.length) {
            result.add(new ArrayList<>(curr)); // add a copy of current subset
            return;

        }
        // Choice 1: INCLUDE nums[idx]
        curr.add(nums[idx]);
        generate(idx + 1, nums, curr, result);
        // Choice 2: EXCLUDE nums[idx]
        curr.remove(curr.size() - 1);
        generate(idx + 1, nums, curr, result);

    }
}