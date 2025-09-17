class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int count1 = 0; int count2 = 0;
        int max1 = -1; int max2 = -1;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i<nums.length; i++){
            if(count1==0 && nums[i]!=max2){
                count1 = 1;
                max1 = nums[i];
            }
            else if(count2==0 && nums[i]!=max1){
                count2 = 1;
                max2 = nums[i];
            }
            else if(nums[i]==max1)  count1++;
            else if(nums[i]==max2)  count2++;
            else{
                count1--;
                count2--;
            }
        }

    // Verify the max1, max2 is more than n/3
        int cnt1 = 0;
        int cnt2 = 0;
        for (int num : nums) {
            if (num == max1)    cnt1++;
            if (num == max2)    cnt2++;
        }
        if (cnt1 > nums.length/3) {
            list.add(max1);
        }
        if(cnt2 > nums.length/3 && max1!=max2) {
            list.add(max2);
        }
        return list;
    }
}