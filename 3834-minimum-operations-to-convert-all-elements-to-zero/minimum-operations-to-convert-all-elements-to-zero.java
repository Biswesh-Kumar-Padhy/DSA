//Approach (Monotonic Increasing Stack, Optimal)
class Solution {
    public int minOperations(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int ops = 0;
        
        for(int num : nums){
            // Remove larger ele from stack, as we can’t increase them back
            while(!stack.isEmpty() && stack.peek() > num ){
                stack.pop();
            }
            if(num == 0) continue; // Skip zeros, as they’re already processed

            // If stack is empty or top < new element, new operation needed
            if(stack.isEmpty() || stack.peek() < num ){
                stack.push(num);
                ops++;
            }
        }
        return ops;
    }
}