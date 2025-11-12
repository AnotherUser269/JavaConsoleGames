package solver;

import solver.configuration.MatrixConfig;
import solver.status.SystemStatus;
import solver.utils.MatrixManager;


public class Solver {
    private final static int BOARD_HEIGHT = MatrixConfig.BOARD_HEIGHT;
    private final static int BOARD_WIDTH = MatrixConfig.BOARD_WIDTH;

    public static void main(String[] args) {
        // Initializing the solution vector
        int[][] x = new int[BOARD_HEIGHT * BOARD_WIDTH][1];
        for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; ++i) x[i][0] = -1;

        // Getting an extended matrix
        MatrixManager matrixManager = new MatrixManager();
        int[][] extendedMatrix = matrixManager.getExtendedMatrix();

        // Solving the extended matrix using Gauss method
        int pivot = 0;

        boolean allPivotsFound = true;
        boolean found;

        for (int y = 0; y < BOARD_HEIGHT * BOARD_WIDTH; ++y) {
            // Finding first line such as extendedMatrix[y][pivot] == 1
            if (extendedMatrix[y][pivot] != 1) {
                found = false;

                for (int dy = y + 1; dy < BOARD_HEIGHT * BOARD_WIDTH; ++dy) {
                    if (extendedMatrix[dy][pivot] == 1) {

                        // Swapping strings
                        int[] temp = extendedMatrix[dy];
                        extendedMatrix[dy] = extendedMatrix[y];
                        extendedMatrix[y] = temp;
                        found = true;

                        break;
                    }
                }
                
            } else {
                found = true;
            }

            // If doesn't exist such string, skipping
            if (found) {
                for (int dy = y + 1; dy < BOARD_HEIGHT * BOARD_WIDTH; ++dy) {
                    if (extendedMatrix[dy][pivot] == 1) {
                        // XOR, to make every one below extendedMatrix[y][j] equals zero
                        for (int j = 0; j < BOARD_HEIGHT * BOARD_WIDTH + 1; ++j) {
                            extendedMatrix[dy][j] ^= extendedMatrix[y][j];
                        }
                    }
                }

                pivot++;

            } else {
                allPivotsFound = false;
            }
        }

        SystemStatus systemStatus = getStatus(allPivotsFound, extendedMatrix);

        if(systemStatus == SystemStatus.IMPOSSIBLE) {
            System.out.println("[INFO] This is an impossible configuration, that cannot be solved.");
        } else if (systemStatus == SystemStatus.ONE_SOLUTION) {
            System.out.println("[INFO] This system has only one solution.");

            for (int i = BOARD_HEIGHT * BOARD_WIDTH - 1; i >= 0; --i) {
                int answer = extendedMatrix[i][extendedMatrix[0].length - 1];
                int preAns = 0;

                for (int j = BOARD_HEIGHT * BOARD_WIDTH - 1; j > i; --j) {
                    preAns ^= (extendedMatrix[i][j] * x[j][0]);
                }

                if ((1 ^ (preAns)) == answer) {
                    x[i][0] = 1;
                } else {
                    x[i][0] = 0;
                }
            }

            printAns(x);

        } else {
            System.out.println("[INFO] This system has more than one solution.");
            System.out.println("[INFO] Going to set all free variables to 0, so it can be not the best solution.");

            for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; ++i) x[i][0] = 0;

            for (int i = BOARD_HEIGHT * BOARD_WIDTH - 1; i >= 0; --i) {
                int answer = extendedMatrix[i][extendedMatrix[0].length - 1];
                int preAns = 0;

                for (int j = BOARD_HEIGHT * BOARD_WIDTH - 1; j > i; --j) {
                    preAns ^= (extendedMatrix[i][j] * x[j][0]);
                }

                if ((1 ^ (preAns)) == answer) {
                    x[i][0] = 1;
                } else {
                    x[i][0] = 0;
                }
            }

            printAns(x);
        }
    }

    private static SystemStatus getStatus(boolean allPivotsFound, int[][] extendedMatrix) {
        if (allPivotsFound) return SystemStatus.ONE_SOLUTION;

        // Checking for 0,0,0...0 | 1
        for (int i = BOARD_HEIGHT * BOARD_WIDTH - 1; i >= 0; --i) {
            boolean isRowZero = true;
            for (int j = 0; j < BOARD_HEIGHT * BOARD_WIDTH; j++) {
                if (extendedMatrix[i][j] != 0) {
                    isRowZero = false;
                    break;
                }
            }

            if (isRowZero && extendedMatrix[i][extendedMatrix[0].length - 1] == 1) {
                return SystemStatus.IMPOSSIBLE;
            }
        }

        return SystemStatus.MULTIPLE_SOLUTION;
    }

    private static void printAns(int[][] x) {
        // Output answer
        System.out.println("[INFO] \"X\" is where you should click.\n");

        for(int i = 0; i < x.length; ++ i) {
            if(i != 0 && i % BOARD_WIDTH == 0) {
                System.out.println();
            }

            if(x[i][0] == 1) {
                System.out.print('X');
            } else {
                System.out.print('O');
            }
        }
    }
}
