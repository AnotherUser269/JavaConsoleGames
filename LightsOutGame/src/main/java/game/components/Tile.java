package game.components;

import game.configs.GameConfig;

public class Tile {
    private enum TileStatus {
        TOGGLED, NOT_TOGGLED
    }

    private TileStatus status = TileStatus.TOGGLED;

    Tile(boolean isToggled) {
        if (!isToggled) status = TileStatus.NOT_TOGGLED;
    }

    public char getCurrentSymbol() {
        if (status == TileStatus.TOGGLED) {
            return GameConfig.TOGGLED_SYMBOL;
        } else {
            return GameConfig.NOT_TOGGLED_SYMBOL;
        }
    }

    public boolean isToggled() {
        return getCurrentSymbol() == GameConfig.TOGGLED_SYMBOL;
    }

    public void toggle() {
        // Changes state
        if (status == TileStatus.TOGGLED) {
            status = TileStatus.NOT_TOGGLED;
        } else {
            status = TileStatus.TOGGLED;
        }
    }
}