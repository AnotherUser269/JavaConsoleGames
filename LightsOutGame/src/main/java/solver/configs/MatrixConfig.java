package solver.configs;

public record MatrixConfig() {
    public static int[][] originalConfiguration = {
            {0, 0, 0, 0},
            {1, 0, 1, 1},
            {1, 1, 0, 0},
            {0, 1, 0, 1},
    };

    public static int[][] originalConfigurationHard = {
            {0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0},
            {1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1},
            {0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0}
    };

    public static int BOARD_HEIGHT = originalConfiguration.length;
    public static int BOARD_WIDTH = originalConfiguration[0].length;
}
