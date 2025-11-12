package game.configs;

import game.game_enums.ClearType;

public record GameConfig() {
    // Tile
    public static final char TOGGLED_SYMBOL = 'I';
    public static final char NOT_TOGGLED_SYMBOL = 'O';

    // Board
    public static final int OUTLINE_WIDTH = 1;
    public static final int BOARD_HEIGHT = 5;
    public static final int BOARD_WIDTH = 5;
    public static final char OUTLINE_CHAR = '\u2591';
    public static final boolean IS_RANDOMIZED = true;

    // InputParser
    public static final char DELIMITER_CHAR = ' ';

    // Screens
    public static final ClearType CLEAR_TYPE = ClearType.RIS;
}
