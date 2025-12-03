class Solution {
    int count = 0;
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        
        dfs(0, -1, adj, values, k); // Start DFS from node 0

        return count;
    }

    private int dfs(int curr,int p, List<List<Integer>> adj, int[] values,int k){
        int sum = values[curr] % k; // Start with this node's value

        for (int next : adj.get(curr)) { // Process each child
            if (next == p) { 
                continue; // Skip going back to parent
            }
            // Add child subtree value mod k
            sum += dfs(next, curr, adj, values, k); 
            sum %= k;
        }
        if (sum == 0) { // If subtree sum is divisible by k -> 
            count ++;   // this is one valid component
        }
        return sum; // Return this subtree sum mod k to parent
    }
}