package solver.utils;

import solver.configs.MatrixConfig;

import java.util.ArrayList;
import java.util.List;

public class MatrixManager {
    private final int[][] originalMatrix = MatrixConfig.originalConfiguration;
    private final static int BOARD_HEIGHT = MatrixConfig.BOARD_HEIGHT;
    private final static int BOARD_WIDTH = MatrixConfig.BOARD_WIDTH;

    /**
     * Generates an influence matrix based on the original configuration.

     * The original matrix is considered as a 2D grid, e.g., for a 3x3 matrix:
     *      0   1   2
     *  0 [x1, x2, x3]
     *  1 [x4, x5, x6]
     *  2 [x7, x8, x9]

     * The influence of each element is determined by its neighbours,
     * and each element affects itself as well.
     *
     * @return The influence matrix (2D array).
     */
    public int[][] getInfluenceMatrix() {
        int[][] influenceMatrix = new int[BOARD_HEIGHT * BOARD_WIDTH][BOARD_HEIGHT * BOARD_WIDTH];

        for (int y = 0; y < BOARD_HEIGHT * BOARD_WIDTH; ++y) {
            int[] currentCoords = numberToCoords(y);
            List<int[]> neighbours = getNeighbours(currentCoords[0], currentCoords[1]);

            // Mark the influence of neighbours
            for (int[] neighbour : neighbours) {
                influenceMatrix[y][coordsToNumber(neighbour[0], neighbour[1])] = 1;
            }
        }

        return influenceMatrix;
    }

    /**
     * Creates the extended matrix, which includes the influence matrix
     * with an extra column that contains the values from the original matrix.
     *
     * @return The extended matrix, which has size (N*M) x (N*M + 1).
     */
    public int[][] getExtendedMatrix() {
        int[][] influenceMatrix = getInfluenceMatrix();
        int[][] extendedMatrix = new int[BOARD_HEIGHT * BOARD_WIDTH][BOARD_HEIGHT * BOARD_WIDTH + 1];

        // Copy influence matrix to the extended matrix
        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; ++i) {
            for (int j = 0; j < BOARD_HEIGHT * BOARD_WIDTH; ++j) {
                extendedMatrix[i][j] = influenceMatrix[i][j];
            }

            // Add the value from the original matrix as the last column
            int[] currentCoords = numberToCoords(i);
            extendedMatrix[i][BOARD_HEIGHT * BOARD_WIDTH] = originalMatrix[currentCoords[0]][currentCoords[1]];
        }

        return extendedMatrix;
    }

    /**
     * Returns a list of neighbouring coordinates for the given position.
     * Neighbours are the adjacent elements in the grid (top, bottom, left, right).
     * The element itself is also considered a neighbour.
     *
     * @param y Row coordinate.
     * @param x Column coordinate.
     * @return A list of neighbouring coordinates (y, x).
     */
    private List<int[]> getNeighbours(int y, int x) {
        List<int[]> neighbours = new ArrayList<>();
        neighbours.add(new int[]{y, x});  // The element itself is considered a neighbour.

        if (y + 1 < BOARD_HEIGHT) neighbours.add(new int[]{y + 1, x});
        if (y - 1 >= 0) neighbours.add(new int[]{y - 1, x});
        if (x + 1 < BOARD_WIDTH) neighbours.add(new int[]{y, x + 1});
        if (x - 1 >= 0) neighbours.add(new int[]{y, x - 1});

        return neighbours;
    }

    /**
     * Converts (y, x) coordinates to a single index in the flattened matrix.
     *
     * @param y Row coordinate.
     * @param x Column coordinate.
     * @return The index corresponding to (y, x).
     */
    private int coordsToNumber(int y, int x) {
        return x + BOARD_WIDTH * y;
    }

    /**
     * Converts a 1D index to (y, x) coordinates in the matrix.
     *
     * @param index The index to convert.
     * @return A 2-element array: {y, x} coordinates.
     */
    private int[] numberToCoords(int index) {
        int y = index / BOARD_WIDTH;
        int x = index % BOARD_WIDTH;
        return new int[]{y, x};
    }
}
