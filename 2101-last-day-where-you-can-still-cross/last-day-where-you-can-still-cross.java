class Solution {
    int ROW, COL;
    // 4 possible movement directions: down, up, right, left
    int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    // Binary search on days to find latest possible crossing day
    public int latestDayToCross(int row, int col, int[][] cells) {
        ROW = row;
        COL = col;

        int left = 0, right = cells.length - 1;
        int lastDay = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canCross(cells, mid)) {
                lastDay = mid + 1; // days are 1-based
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return lastDay;
    }

    // Check if crossing is possible on given day
    boolean canCross(int[][] cells, int day) {
        int[][] grid = new int[ROW][COL];

        for (int i = 0; i <= day; i++) { // Mark flooded cells up to 'day'
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            grid[r][c] = 1;
        }

        for (int j = 0; j < COL; j++) { // Try to start DFS from top row
            if (grid[0][j] == 0 && dfs(grid, 0, j))
                return true;
        }
        return false;
    }

    // DFS to check if we can reach bottom row starting from (i, j)
    boolean dfs(int[][] grid, int i, int j) {
        // Out of bounds or water cell
        if (i < 0 || i >= ROW || j < 0 || j >= COL || grid[i][j] == 1)
            return false;

        // Reached bottom row → path exists
        if (i == ROW - 1)   return true;

        grid[i][j] = 1; // mark as visited (water)

        for (int[] dir : directions) { // Explore all 4 directions
            if (dfs(grid, i + dir[0], j + dir[1]))
                return true;
        }
        return false;
    }   
}