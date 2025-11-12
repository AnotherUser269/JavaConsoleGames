package game;

import game.components.Board;
import game.game_enums.GameState;
import game.utils.ScreenManager;
import game.utils.InputParser;
import game.configs.GameConfig;

public class LightsOut {
    public static void main(String[] args) {
        Board board = new Board(
                GameConfig.OUTLINE_WIDTH,
                GameConfig.BOARD_HEIGHT,
                GameConfig.BOARD_WIDTH,
                GameConfig.OUTLINE_CHAR,
                GameConfig.IS_RANDOMIZED
        );

        InputParser inputParser = new InputParser(
                GameConfig.DELIMITER_CHAR
        );

        // Showing main screen
        ScreenManager screenManager = new ScreenManager();

        screenManager.clearScreen();
        screenManager.showStartScreen();

        inputParser.askForStart();

        // Actual game
        int moves = 0;
        int[] lastCoords = null;

        while (board.checkState() == GameState.CONTINUED) {
            screenManager.clearScreen();
            board.printBoard();
            System.out.println();

            int[] coords = inputParser.getCoords(lastCoords);

            if (coords != null) {
                if (coords[0] != -1 && coords[1] != -1) {
                    if (board.click(coords[0], coords[1])) {
                        lastCoords = coords;
                        ++moves;
                    }
                }
            } else {
                break;
            }

        }

        if (board.checkState() == GameState.WON) {
            // Showing end screen and quiting
            screenManager.clearScreen();

            board.printBoard();
            System.out.println();

            screenManager.showEndScreen(moves);
            System.out.println();

            inputParser.askForEnd();
        } else {
            screenManager.showExitScreen();
        }

        inputParser.close();
    }
}
