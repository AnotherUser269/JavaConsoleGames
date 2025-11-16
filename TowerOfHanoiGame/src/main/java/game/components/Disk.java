package game.components;

public class Disk {
    private final int radius;

    Disk(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be greater than 0.");
        }

        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }
}
