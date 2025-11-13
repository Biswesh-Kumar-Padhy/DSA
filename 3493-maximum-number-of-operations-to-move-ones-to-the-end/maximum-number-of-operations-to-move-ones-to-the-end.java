class Solution {
    public int maxOperations(String s) {
        // Count of '1's encountered so far
        int ones = 0, operations = 0;

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '1'){
                ones++; // Increment count when '1' is seen
            }
            else{
                // When current char is '0' and previous was '1'
                if(i > 0 && s.charAt(i-1) == '1'){
                    // Add all previous '1's as possible operations
                    operations += ones; 
                }
            }
        }
        return operations;
    }
}