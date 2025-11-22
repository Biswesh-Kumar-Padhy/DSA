class Solution {
    public int minimumOperations(int[] nums) {
        int[] arr = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            arr[i] = nums[i] % 3;
        }

        int count = 0;
        for(int a : arr){
            if(a > 0){
                count++;
            }
        }
        return count;

    }
}