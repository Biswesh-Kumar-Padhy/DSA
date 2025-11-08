class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ans = new int[n-k+1];
        Map<Integer, Integer> freq = new HashMap<>();
        
        // Build frequency map for first window
        for(int i = 0; i < k; i++){
            int num = nums[i];
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        ans[0] = calculateSum(freq, x); // First x-sum

        // Slide the window
        for(int i = k; i < n; i++){
            int newEle = nums[i]; // Incoming element
            int oldEle = nums[i-k]; // Outgoing element

            // Add new element
            freq.put(newEle, freq.getOrDefault(newEle, 0) + 1); 
            // Remove outgoing element
            freq.put(oldEle, freq.getOrDefault(oldEle, 0) - 1); 
            if (freq.getOrDefault(oldEle, 0) == 0) {
                freq.remove(oldEle); //Remove from map, if oldEle freq is 0
            }

            // Store result for current window
            ans[i - k + 1] = calculateSum(freq, x);
        }
        return ans;
    }
    
    public int calculateSum(Map<Integer, Integer> freq, int x){
        // Convert to list for sorting
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(freq.entrySet());

        // Sort by frequency (desc), then value (desc)
        Collections.sort(list, (a, b) -> {
            if (!a.getValue().equals(b.getValue())) {
                return b.getValue() - a.getValue();
            }
            return b.getKey() - a.getKey();
        });

        int sum = 0;
        int count = 0;
        // Pick top x elements
        for(Map.Entry<Integer, Integer> e : list){
            if(count >= x){
                break;
            }
            //Calc top x-sum
            sum += e.getKey() * e.getValue();
            count += 1;

        }
        return sum;
    }
}