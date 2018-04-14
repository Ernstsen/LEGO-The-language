package example;

public class Brick {
    private BrickColor color;

    Brick(BrickColor color) {
        this.color = color;
    }

    public BrickColor getColor() {
        return this.color;
    }
}