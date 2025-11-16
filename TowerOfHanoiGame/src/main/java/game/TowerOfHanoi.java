package game;

import game.components.Board;
import game.configs.GameConfig;
import game.game_enums.GameState;
import game.utils.InputParser;
import game.utils.ScreenManager;

public class TowerOfHanoi {
    public static void main(String[] args) {
        Board board = new Board(
                GameConfig.DISK_AMOUNT,
                GameConfig.TOWER_AMOUNT,
                GameConfig.START_INDEX,
                GameConfig.GOAL_INDEX
        );

        InputParser inputParser = new InputParser(GameConfig.DELIMITER_CHAR);

        ScreenManager screenManager = new ScreenManager();

        screenManager.clearScreen();
        screenManager.showStartScreen();

        inputParser.askForStart();

        // Actual game
        long startTime = System.currentTimeMillis();
        int moves = 0;
        int[] lastCoords = null;

        while (board.checkState() != GameState.WON) {
            screenManager.clearScreen();
            board.printBoard();
            int[] newCoords = inputParser.getMove(lastCoords);

            if (newCoords == null) {
                break;
            }

            if (board.changePosition(newCoords[0], newCoords[1])) {
                lastCoords = newCoords;
                moves++;
            }
        }

        if (board.checkState() == GameState.WON) {
            // Showing end screen and quiting
            long endTime = System.currentTimeMillis();
            screenManager.clearScreen();

            board.printBoard();
            System.out.println();

            screenManager.showEndScreen(moves, (endTime - startTime)/1000L);
            System.out.println();

            inputParser.askForEnd();
        } else {
            screenManager.showExitScreen();
        }

        inputParser.close();
    }
}