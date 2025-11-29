class Solution {
    public int countPalindromicSubsequence(String s) {
        // Arrays to store the first and last occurrence of each char (a–z)
        int[] first_idx = new int[26];
        int[] last_idx = new int[26];
        // Initialize all to -1 (i.e; character has not appeared yet)
        Arrays.fill(first_idx, -1); 
        Arrays.fill(last_idx, -1);

        // Traverse string to record first & last occurrence for each char
        for(int i = 0; i < s.length(); i++){
            int curr = s.charAt(i) - 'a'; //a=0, b=1, c=3,...
            if (first_idx[curr] == -1) {
                first_idx[curr] = i; // first occurrence
            }
            last_idx[curr] = i; // keeps updating till last occurrence
        }

        int result = 0;
        for(int idx = 0; idx < 26; idx++){
            // If this character never appeared, skip it
            if (first_idx[idx] == -1) {
                continue;
            }
            
            Set<Character> set = new HashSet();
            // Collect distinct char in between first_idx and last_idx
            for(int i = first_idx[idx] + 1; i <=  last_idx[idx] - 1; i++){
                // each distinct char forms a palindrome: letter + char + letter
                set.add(s.charAt(i));
            }
            // Add no of unique valid palindromes contributed by this letter
            result += set.size();
        }
        return result;
    }
}