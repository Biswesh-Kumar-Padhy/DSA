class Solution {
    public double separateSquares(int[][] squares) {
        double low = Double.MAX_VALUE, high = Double.MIN_VALUE;
        double totalArea = 0.0;

        // find search range and total area
        for (int[] s : squares) {
            double y = s[1], len = s[2];

            low = Math.min(low, y);         // lowest bottom
            high = Math.max(high, y + len); // highest top
            totalArea += len * len;
        }

        // binary search for y where bottom area == top area
        while (high - low > 1e-5) {
            double midY = low + (high - low) / 2.0;

            if (check(squares, midY, totalArea)) {
                high = midY;    // too much area below → move line down
            } else {
                low = midY;     // too much area above → move line up
            }
        }
        return low; // y-coordinate of separating line
    }

    // Check if area BELOW midY is at least half of total area
    private boolean check(int[][] squares, double midY, double total) {
        double bottomArea = 0.0;

        for (int[] s : squares) {
            double y = s[1];      // bottom y of square
            double len = s[2];      // side length
            double bottomY = y, topY = y + len;

            if (midY >= topY) {
                bottomArea += len * len;    // whole square is below the line
            } else if (midY > bottomY) {    // line cuts the square → take only bottom part
                bottomArea += (midY - bottomY) * len; 
            }
            // else: square is completely above the line → contributes 0
        }
        // if bottom area >= half, we need to move line downward
        return bottomArea >= total / 2.0;
    }
}
