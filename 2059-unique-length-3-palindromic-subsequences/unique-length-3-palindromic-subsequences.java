class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();

        // Collect all unique characters in the string
        Set<Character> letters = new HashSet<>();
        for(char ch : s.toCharArray()){
            letters.add(ch);
        }

        int result = 0;
        for(char letter : letters){
            int left_idx = -1;
            int right_idx = -1;

            // Find the first and last occurrence of this 'letter'
            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == letter) {
                    if (left_idx == -1) {
                        left_idx = i; // first occurrence
                    }
                    right_idx = i; // keeps updating till last occurrence
                }
            }
        
            Set<Character> set = new HashSet<>();
            // Collect distinct char in between left_idx and right_idx
            for(int i = left_idx + 1; i <=  right_idx - 1; i++){
                // each distinct char forms a palindrome letter + char + letter
                set.add(s.charAt(i));
            }
            // Number of unique palindromes contributed by this 'letter'
            result += set.size();
        }
        return result;
    }
}