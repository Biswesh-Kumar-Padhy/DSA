class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        char j = s.toCharArray()[0];
        if (j == ')' || j == '}' || j == ']' ){
            return false;
        }

        for(char i : s.toCharArray()){
            if (i == '(' || i == '{' || i == '['){
                stack.push(i);
            }
            else {
                if(!stack.isEmpty() && stack.peek() == '(' && i == ')'){
                    stack.pop();
                }
                else if(!stack.isEmpty() && stack.peek() == '[' && i == ']'){
                    stack.pop();
                }
                else if(!stack.isEmpty() && stack.peek() == '{' && i == '}'){
                    stack.pop();
                }
                else{
                    return false;
                }   
            }
        }
        return stack.isEmpty() ? true : false;
    }
}