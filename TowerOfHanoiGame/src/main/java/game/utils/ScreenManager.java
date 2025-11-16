package game.utils;

import game.configs.GameConfig;
import game.game_enums.ClearType;

public class ScreenManager {
    private static final String LOGO =
            """       
                              0                       0               0
                              0                       0               0
                              0                       0               0
                       1111111111111111               0               0
                      11Tower Of Hanoi11          111111111          111
                     11111111111111111111        11111111111        11111
                     2222222222222222222222222222222222222222222222222222
                     """.replace('0', GameConfig.STICK_CHAR)
                    .replace('1', GameConfig.DISK_CHAR)
                    .replace('2', GameConfig.DESK_CHAR);

    private static final String RULES =
            """
                    [Rules]:
                    * Goal: move all disks from the start peg to the target peg following the rules.
                    * Move: specify a move as "from%cto" (e.g., "1%c3" - move the top disk from peg 1 to peg 3).
                    * Rules: move only one disk at a time; never place a larger disk on a smaller one;
                    a move transfers only the top disk of the chosen peg.
                    """;

    private static final String IMPORTANT = """
            [Important]:
            * If the console text appears garbled or illegible, try changing the font.
            * Do not use high numbers of disks. It wont fit into the terminal window.""";

    private static final String GAME_CONFIG = """                
            [Game Config]:
            * Disks: %d,
            * Pegs: %d,
            * Goal index: %d""";

    public void showStartScreen() {
        // Logo
        System.out.println(LOGO);
        System.out.println();

        // Rules
        System.out.printf(RULES, GameConfig.DELIMITER_CHAR, GameConfig.DELIMITER_CHAR);
        System.out.println();

        // Important
        System.out.printf(IMPORTANT);
        System.out.println("\n");

        // Config
        System.out.printf(GAME_CONFIG,
                GameConfig.DISK_AMOUNT,
                GameConfig.TOWER_AMOUNT,
                GameConfig.GOAL_INDEX);
        System.out.println("\n");
    }

    public void showEndScreen(int moves, long time) {
        int hours = (int) time / 3600;
        int minutes = (int) (time % 3600) / 60;
        int seconds = (int) time % 60;

        System.out.println("* Congrats! You won.");
        System.out.printf("* You spent %d moves.\n", moves);
        System.out.printf("* You spent %d hours, %d minutes, %d seconds.\n", hours, minutes, seconds);
    }

    public void clearScreen() {
        if (GameConfig.CLEAR_TYPE == ClearType.RIS) System.out.println("\u001Bc");
        else if (GameConfig.CLEAR_TYPE == ClearType.PRINTLN) System.out.println("\n");
        else if (GameConfig.CLEAR_TYPE == ClearType.CURSOR_HOME_CLEAR) System.out.println("\u001b[H\u001b[2J");
        else throw new IllegalArgumentException("Wrong clear type provided.");
    }

    public void showExitScreen() {
        System.out.println("\n* Quiting...");
    }
}
