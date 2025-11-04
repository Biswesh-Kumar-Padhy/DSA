class Solution {
    public long minimumOperations(int[] nums, int[] target) {
        int n = nums.length;
        int prev = 0, curr = 0;
        long operations = 0;
        

        for(int i = 0; i < n; i++){
            curr = target[i] - nums[i];

            if(prev<0 && curr>0 || prev>0 && curr<0){ //sign change(-to+, +to-)
                operations += Math.abs(curr);
            }
            else if(Math.abs(curr) > Math.abs(prev)){
                operations += Math.abs(curr-prev);
            }
            prev =curr;
        }
        return operations;
        
    }
}