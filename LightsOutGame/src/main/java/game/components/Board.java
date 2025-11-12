package game.components;

import game.game_enums.GameState;

import java.util.Random;

public class Board {
    private final int outlineWidth;
    private final int boardHeight;
    private final int boardWidth;
    private final char outlineSymbol;

    private final Random random = new Random();

    private final Tile[][] board;

    public Board(
            int outlineWidth,
            int boardHeight,
            int boardWidth,
            char outlineSymbol,
            boolean isRandomized
    ) {
        if (outlineWidth <= 0) {
            throw new IllegalArgumentException("outlineWidth must be > 0");
        } else if (!(boardWidth > 0 && boardWidth <= 36)) {
            throw new IllegalArgumentException("boardWidth must be in 1..36 range");
        } else if (!(boardHeight > 0 && boardHeight <= 36)) {
            throw new IllegalArgumentException("boardHeight must be in 1..36 range");
        } else {
            this.outlineWidth = outlineWidth;
            this.outlineSymbol = outlineSymbol;
            this.boardWidth = boardWidth;
            this.boardHeight = boardHeight;

            this.board = generateBoard(isRandomized);
            if (isRandomized) randomize();
        }
    }

    private Tile[][] generateBoard(boolean isRandomized) {
        // Generates and returns a fully unsolved board, where every lamp is on.
        Tile[][] board = new Tile[boardHeight][boardWidth];

        for (int y = 0; y < boardHeight; ++y) {
            for (int x = 0; x < boardWidth; ++x) {
                if (isRandomized) {
                    board[y][x] = new Tile(false);
                } else {
                    board[y][x] = new Tile(true);
                }
            }
        }

        return board;
    }

    private void randomize() {
        // Shuffling the board
        for (int y = 0; y < boardHeight; ++y) {
            for (int x = 0; x < boardWidth; ++x) {
                if (random.nextBoolean()) {
                    click(y, x);
                }
            }
        }
    }

    public void printBoard() {
        int totalWidth = boardWidth + 2 * outlineWidth;

        // Column numbers header (shifted right by outlineWidth)
        System.out.print(" ");
        for (int i = 0; i < outlineWidth + 1; i++) System.out.print(' ');
        for (int x = 0; x < boardWidth; x++) System.out.print(Integer.toString(x, 36));
        System.out.println();

        for (int i = 0; i < outlineWidth; i++) {
            System.out.print("  ");
            for (int j = 0; j < totalWidth; j++) System.out.print(outlineSymbol);
            System.out.println();
        }

        for (int y = 0; y < boardHeight; y++) {
            System.out.print(Integer.toString(y, 36) + " ");
            for (int i = 0; i < outlineWidth; i++) System.out.print(outlineSymbol);
            for (int x = 0; x < boardWidth; x++) {
                System.out.print(board[y][x].getCurrentSymbol());
            }
            for (int i = 0; i < outlineWidth; i++) System.out.print(outlineSymbol);
            System.out.println();
        }

        for (int i = 0; i < outlineWidth; i++) {
            System.out.print("  ");
            for (int j = 0; j < totalWidth; j++) System.out.print(outlineSymbol);
            System.out.println();
        }
    }

    public boolean click(int y, int x) {
        // Implements the main logic of toggling neighbours
        try {
            this.board[y][x].toggle();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("[ERROR] Wrong coordinates provided.");
            return false;
        }

        if (y + 1 < boardHeight) this.board[y + 1][x].toggle();
        if (y - 1 >= 0) this.board[y - 1][x].toggle();

        if (x + 1 < boardWidth) this.board[y][x + 1].toggle();
        if (x - 1 >= 0) this.board[y][x - 1].toggle();

        return true;
    }

    public GameState checkState() {
        // Checks if any workings lamps left. If so, game continues, otherwise - ends
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                if (this.board[y][x].isToggled()) {
                    return GameState.CONTINUED;
                }
            }
        }

        return GameState.WON;
    }
}
