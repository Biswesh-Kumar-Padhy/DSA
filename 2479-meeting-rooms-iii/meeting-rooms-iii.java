class Solution {
    public int mostBooked(int n, int[][] meetings) {
        // sort by starting time of meetings
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        int[] roomsUsedCount = new int[n]; // count of meetings per room

        // usedRooms: [endTime, room], ordered by earliest endTime, then room id
        PriorityQueue<long[]> usedRooms=new PriorityQueue<>((a, b) -> a[0]!=b[0]
                                            ? Long.compare(a[0], b[0])
                                            : Long.compare(a[1], b[1]));

        // unusedRooms: available rooms ordered by room number
        PriorityQueue<Integer> unusedRooms = new PriorityQueue<>();
        
        for (int room = 0; room < n; room++) {
            unusedRooms.add(room); // All rooms are unused in the beginning
        }

        for (int[] meet : meetings) {
            int start = meet[0], end = meet[1];

            // Free rooms whose meetings have ended by current start time
            while (!usedRooms.isEmpty() && usedRooms.peek()[0] <= start) {
                int room = (int) usedRooms.poll()[1];
                unusedRooms.add(room);
            }

            if (!unusedRooms.isEmpty()) {
                int room = unusedRooms.poll(); // Assign earliest available room
                usedRooms.add(new long[]{end, room});
                roomsUsedCount[room]++;
            } else { // Delay meeting in the room that ends earliest
                int room = (int) usedRooms.peek()[1];
                long endTime = usedRooms.poll()[0];
                usedRooms.add(new long[]{endTime + (end - start), room});
                roomsUsedCount[room]++;
            }
        }

        // Find room with maximum usage (smallest index on tie)
        int resultRoom = 0;
        for (int room = 0; room < n; room++) {
            if (roomsUsedCount[room] > roomsUsedCount[resultRoom]){
                resultRoom = room;
            }
        }
        return resultRoom;
    }
}