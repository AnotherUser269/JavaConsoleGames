package game.configs;

import game.game_enums.ClearType;

public record GameConfig() {
    // GOAL
    public static final int GOAL_INDEX = 2;
    public static final int START_INDEX = 0;

    // TOWER
    public static final int TOWER_AMOUNT = 3;

    // DISK
    public static final int DISK_AMOUNT = 20;
    public static final char DISK_CHAR = '\u2591';

    public static final char STICK_CHAR = '\u2502';

    // InputParser
    public static final char DELIMITER_CHAR = ' ';

    // UI
    public static final ClearType CLEAR_TYPE = ClearType.RIS;

    // Desk
    public static final  int DISTANCE = 2;
    public static final char DESK_CHAR = '\u2588';
}
