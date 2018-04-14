package example;

public class Brick {
    private int width;
    private int height;
    private BrickColor color;

    Brick(int width, int height, BrickColor color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public BrickColor getColor() {
        return this.color;
    }
}