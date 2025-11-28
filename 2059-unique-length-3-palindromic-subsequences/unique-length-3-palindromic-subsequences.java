class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);

        // record first and last positions
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) first[c] = i;
            last[c] = i;
        }

        int ans = 0;
        // for each letter, count distinct middle chars between first and last
        for (int ch = 0; ch < 26; ch++) {
            if (first[ch] == -1 || last[ch] - first[ch] <= 1) continue;
            boolean[] seen = new boolean[26];
            for (int i = first[ch] + 1; i < last[ch]; i++) {
                seen[s.charAt(i) - 'a'] = true;
            }
            for (boolean b : seen) if (b) ans++;
        }
        return ans;
    }
}
