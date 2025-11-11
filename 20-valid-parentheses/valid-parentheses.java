class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for(char c : s.toCharArray()){
            // Push opening brackets
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // Check for valid closing bracket
            else {
                if (stack.isEmpty()) return false;

                char top = stack.pop();
                // Mismatch case
                if (( top != '(' && c == ')') ||
                    (top != '[' && c == ']') ||
                    (top != '{' && c == '}')) {
                    return false;
                }
            }
        }
        return stack.isEmpty() ? true : false;
    }
}