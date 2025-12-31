class Solution {
    int[] parent, rank;
    int rows, cols, top, bottom;
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int latestDayToCross(int row, int col, int[][] cells) {
        rows = row;
        cols = col;
        int total = row * col;
        top = total;
        bottom = total + 1;

        parent = new int[total + 2];
        rank = new int[total + 2];
        for (int i = 0; i < parent.length; i++) parent[i] = i;

        boolean[][] land = new boolean[row][col];

        // Traverse days in reverse
        for (int d = cells.length - 1; d >= 0; d--) {
            int r = cells[d][0] - 1;
            int c = cells[d][1] - 1;
            land[r][c] = true;
            int id = r * cols + c;

            // connect neighbors
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr >= 0 && nr < row && nc >= 0 && nc < col && land[nr][nc]) {
                    union(id, nr * cols + nc);
                }
            }

            if (r == 0) union(id, top);
            if (r == row - 1) union(id, bottom);

            if (find(top) == find(bottom))
                return d;
        }
        return 0;
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[a] = b;
    }
}
