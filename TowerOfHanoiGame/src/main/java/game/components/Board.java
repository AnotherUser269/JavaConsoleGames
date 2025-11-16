package game.components;

import game.configs.GameConfig;
import game.exceptions.IllegalMoveException;
import game.exceptions.PossibilityException;
import game.game_enums.GameState;

public class Board {
    private final Tower[] board;
    private final int diskAmount;
    private final int goalIndex;
    private final int startIndex;
    private final int towerAmount;
    private final StringBuilder stringBuilder;

    public Board(int diskAmount, int towerAmount, int startIndex, int goalIndex) {
        if (diskAmount <= 0) {
            throw new IllegalArgumentException("Disk amount must be >= 1.");
        } else if (towerAmount <= 0) {
            throw new IllegalArgumentException("Tower amount must be >= 1.");
        } else if (!checkIfPossible(diskAmount, towerAmount)) {
            throw new PossibilityException("This configuration is impossible");
        } else if(goalIndex >= towerAmount || goalIndex < 0) {
            throw new IllegalArgumentException("Goal index must be in 0..[towerAmount-1] range.");
        } else if(startIndex >= towerAmount || startIndex < 0) {
            throw new IllegalArgumentException("Start index must be in 0..[towerAmount-1] range.");
        } else if(towerAmount > 36) {
            throw new IllegalArgumentException("Tower amount must be <= 36.");
        }

        this.diskAmount = diskAmount;
        this.goalIndex = goalIndex;
        this.towerAmount = towerAmount;
        this.startIndex = startIndex;
        this.board = generateBoard(towerAmount);
        this.stringBuilder = new StringBuilder();

        fillFirst();
    }

    public boolean changePosition(int fromInd, int toInd) {
        // Moves top disk on board[fromInd] to the top of the board[toInd]
        // Returns true, if this was possible. Otherwise - false
        try {
            Tower startTower = board[fromInd];
            Tower destinationTower = board[toInd];

            if (startTower.peekHeadRadius() <= destinationTower.peekHeadRadius() && !startTower.isEmpty()) {
                Disk oldDisk = startTower.pop();

                destinationTower.put(oldDisk);
            } else {
                throw new IllegalMoveException("Cannot place larger disk on smaller disk");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("[ERROR] Wrong index.");
            return false;
        } catch (IllegalMoveException e) {
            System.err.println("[ERROR] " + e.getLocalizedMessage());
            return false;
        }

        return true;
    }

    public GameState checkState() {
        if (board[goalIndex].isFull()) {
            return GameState.WON;
        } else {
            return GameState.CONTINUE;
        }
    }

    public void printBoard() {
        stringBuilder.setLength(0);

        int slotWidth = diskAmount * 2 - 1;

        for (int y = 0; y < diskAmount; ++y) {
            for (Tower tower : board) {
                int r = tower.getRadii()[y];
                int diskWidth = Math.max(0, r * 2 - 1);

                int leftPadding = (slotWidth - diskWidth) / 2;
                int rightPadding = slotWidth - diskWidth - leftPadding;

                stringBuilder.append(" ".repeat(Math.max(0, leftPadding)));
                stringBuilder.append(String.valueOf(GameConfig.DISK_CHAR).repeat(diskWidth));

                if(diskWidth == 0) {
                    stringBuilder.append(GameConfig.STICK_CHAR);
                    stringBuilder.append(" ".repeat(Math.max(0, rightPadding - 1)));
                } else stringBuilder.append(" ".repeat(Math.max(0, rightPadding)));

                stringBuilder.append(" ".repeat(Math.max(0, GameConfig.DISTANCE)));
            }

            System.out.println(stringBuilder);
            stringBuilder.setLength(0);
        }

        int deskLen = GameConfig.DISTANCE * (towerAmount - 1) + (diskAmount * 2 - 1) * towerAmount;
        stringBuilder.append(String.valueOf(GameConfig.DESK_CHAR).repeat(Math.max(0, deskLen))).append('\n');

        // Indexes
        int leftPadding = (slotWidth - 1) / 2;
        int rightPadding = slotWidth - 1 - leftPadding;

        for(int j = 0; j < towerAmount; ++j) {
            if(j == GameConfig.GOAL_INDEX) {
                stringBuilder.append(" ".repeat(Math.max(0, leftPadding - 1)));
                stringBuilder.append("[");
            } else {
                stringBuilder.append(" ".repeat(Math.max(0, leftPadding)));
            }

            stringBuilder.append(Integer.toString(j, 36));

            if(j == GameConfig.GOAL_INDEX) {
                stringBuilder.append("]");
                stringBuilder.append(" ".repeat(Math.max(0, rightPadding - 1)));
            } else {
                stringBuilder.append(" ".repeat(Math.max(0, rightPadding)));
            }

            stringBuilder.append(" ".repeat(Math.max(0, GameConfig.DISTANCE)));
        }

        System.out.println(stringBuilder);
        System.out.println("\n");
    }


    private boolean checkIfPossible(int diskAmount, int towerAmount) {
        // Checks if start position can be solved.
        // If 1 >= towerAmount - the game doesn't make any sense, we cant change a position
        // If towerAmount == 2 - the only possible configuration is 1 disk.
        // Else the game is possible (and always possible with only three towers)
        if (towerAmount <= 1) {
            return false;
        }

        return towerAmount != 2 || diskAmount == 1;
    }

    private Tower[] generateBoard(int towerAmount) {
        // Creates towers without disks
        Tower[] board = new Tower[towerAmount];

        for (int i = 0; i < towerAmount; ++i) {
            board[i] = new Tower();
        }

        return board;
    }

    private void fillFirst() {
        // Initial state
        for (int i = diskAmount; i > 0; --i) {
            board[startIndex].put(new Disk(i));
        }
    }
}
