// Approach 1: DFS + TreeSet
class Solution {
    // Map to store stations belonging to each connected component (power grid)
    Map<Integer, TreeSet<Integer>> componentStations = new HashMap<>();

    private void dfs(int node, Map<Integer, List<Integer>> adj, 
                    int[] visited, int componentId, int[] componentOf) {

        visited[node] = 1; // Mark current station as visited
        componentOf[node] = componentId; // Assign current station to this component

        // Initialize component set if not present
        componentStations.putIfAbsent(componentId, new TreeSet<>());
        // Add current node to its component's station set
        componentStations.get(componentId).add(node);

        // Explore all connected (neighbor) stations
        for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) {
            if (visited[neighbor] == 0) {
                dfs(neighbor, adj, visited, componentId, componentOf);
            }
        }

    }

    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        // // Step 1: Build adjacency list for undirected graph
        for (int[] edge : connections) {
            int u = edge[0];
            int v = edge[1];

            // Add both directions since cables are bidirectional
            adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        int[] visited = new int[c+1]; // To track visited stations
        // To map each station to its component ID
        int[] componentOf = new int[c + 1];

        // Step 2: Identify all connected components using DFS
        for (int node = 1; node <= c; node++){ // Start from 1 (1-based indexing)
            if(visited[node] == 0){
                int componentId = node;
                // Assign component ID = node
                dfs(node, adjList, visited, componentId, componentOf);
            }
        }

        List<Integer> resultList = new ArrayList<>();
        // Step 3: Process queries
        for (int[] q : queries) {
            int type = q[0]; // Query type: 1 (maintenance) or 2 (offline)
            int x = q[1]; // Station ID

            //Find which component the station belongs to
            int compId = componentOf[x];
            TreeSet<Integer> set = componentStations.get(compId);

            if (type == 1) {  // Maintenance check query
                if (set.contains(x)) {
                    // If station x is online → add it to result
                    resultList.add(x);
                } else if (!set.isEmpty()) {
        // If station x is offline → add the smallest online station in same grid
                    resultList.add(set.first());
                } else {
                    // If no online station exists in grid
                    resultList.add(-1);
                }
            } else {
                // Query type 2: Station goes offline → remove from TreeSet
                set.remove(x);
            }
        }

        // Step 4: Convert result list to integer array
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }
}