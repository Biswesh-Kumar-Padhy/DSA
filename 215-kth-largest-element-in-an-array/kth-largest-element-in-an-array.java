class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Min-Heap to store k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int n : nums){
            minHeap.offer(n); // Add element to heap

            if(minHeap.size() > k){
                minHeap.poll(); // remove smallest if size exceeds k
            }
        }
        // Root of heap is kth largest element
        return minHeap.peek(); 
    }
}