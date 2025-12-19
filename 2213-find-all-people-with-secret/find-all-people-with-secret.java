class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {

        // Group Meetings in increasing order of time
        Map<Integer, List<int[]>> timeMeetings = new TreeMap<>();
        for (int[] meeting : meetings) {
            int x = meeting[0], y = meeting[1], t = meeting[2];
            timeMeetings.computeIfAbsent(t, k -> new ArrayList<>()).add(new int[]{x, y});
        }
        
        // Boolean Array to mark if a person knows the secret or not
        boolean[] knowsSecret = new boolean[n];
        knowsSecret[0] = true;
        knowsSecret[firstPerson] = true;

        // Process in increasing order of time
        for (int t : timeMeetings.keySet()) {
            // For each person, save all the people whom he/she meets at time t
            Map<Integer, List<Integer>> meet = new HashMap<>();
            for (int[] meeting : timeMeetings.get(t)) {
                int x = meeting[0], y = meeting[1];
                meet.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
                meet.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
            }

            // Start traversal from those who already know the secret at time t
            // Set to avoid redundancy
            Set<Integer> start = new HashSet<>();
            for (int[] meeting : timeMeetings.get(t)) {
                int x = meeting[0], y = meeting[1];
                if (knowsSecret[x]) {
                    start.add(x);
                }
                if (knowsSecret[y]) {
                    start.add(y);
                }
            }
            
            // Do BFS
            Queue<Integer> q = new LinkedList<>(start);
            while (!q.isEmpty()) {
                int person = q.poll();
                for (int nextPerson : meet.getOrDefault(person, new ArrayList<>())) {
                    if (!knowsSecret[nextPerson]) {
                        knowsSecret[nextPerson] = true;
                        q.offer(nextPerson);
                    }
                }
            }
        }
        
        // List of people who know the secret
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (knowsSecret[i]) {
                result.add(i);
            }
        }
        return result;
    }
}