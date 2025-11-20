package game.configs;

import game.enums.ClearType;
import game.enums.GameType;

public record GameConfig() {
    // Game
    public static final GameType GAME_TYPE = GameType.ALL_ONES;

    // Tile
    public static final char TOGGLED_SYMBOL = 'I';
    public static final char NOT_TOGGLED_SYMBOL = 'O';

    // Board
    public static final int OUTLINE_WIDTH = 1;
    public static final int BOARD_HEIGHT = 3;
    public static final int BOARD_WIDTH = 3;
    public static final char OUTLINE_CHAR = '\u2591';

    // Custom start. 1 = Turned on, 0 - Turned off
    public static final int[][] CUSTOM_BOARD = {
            {1, 0, 1, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 0, 0, 1, 1},
            {1, 0, 1, 1, 0},
            {0, 0, 1, 0, 0}
    };

    // InputParser
    public static final char DELIMITER_CHAR = ' ';

    // Screens
    public static final ClearType CLEAR_TYPE = ClearType.RIS;
}
