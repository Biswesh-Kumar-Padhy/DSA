//Approach 2
class Solution {
    static final int M = 1_000_000_007;

    public int numSub(String s) {
        long ones = 0, result = 0;
        for (char i : s.toCharArray()){
            if(i == '1'){ // 
                ones++; //Extend the current streak
                // Add count of new substrings formed by this streak
                result = (result + ones) % M;
            }
            else{
                ones = 0; // Reset count
            }
        }
        return (int)result;
    }
}