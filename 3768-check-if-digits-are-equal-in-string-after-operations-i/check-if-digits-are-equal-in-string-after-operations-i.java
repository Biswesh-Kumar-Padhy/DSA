class Solution {
    public boolean hasSameDigits(String s) {
        int n = s.length();
        int[] a = new int[n];
        for (int i = 0; i < n; i++){
            a[i] = s.charAt(i) - '0';
        }

        // Reduce length from n down to 2 in-place:
        for (int len = n; len > 2; len--) {
            // compute next row into a[0..len-2]
            for (int i = 0; i <= len - 2; i++) {
                a[i] = (a[i] + a[i + 1]) % 10;
            }
            // conceptual length reduced by 1; next iteration uses a[0..len-2]
        }

        return a[0] == a[1];
    }
}