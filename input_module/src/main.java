import java.util.ArrayList;
import java.util.List;

/**
 * @author Johannes Ernstsen
 */
public class main {

    public static void main(String[] args) {
        Brick loopStart = new Brick(6, 2, BrickColor.White);
        Brick iterations = new Brick(3, 1, BrickColor.DarkBlue);
        Brick forward = new Brick(6, 2, BrickColor.DarkGreen);
        Brick loopEnd = new Brick(6, 2, BrickColor.White);
        Brick turnLeft = new Brick(3, 2, BrickColor.Red);
        Brick reverse = new Brick(8, 3, BrickColor.Black);
        Brick turnRight = new Brick(6, 8, BrickColor.Yellow);
        List<Brick> bricks = new ArrayList<>();

        bricks.add(forward);
        bricks.add(loopStart);
        bricks.add(iterations); //Value=6
        bricks.add(forward);
        bricks.add(turnLeft);
        bricks.add(reverse);
        bricks.add(turnRight);
        bricks.add(loopEnd);
        bricks.add(reverse);
        System.out.println(Parser.parse(bricks).toString());
        List<Exp> parse = Parser.parse(bricks);
        Interpreter.eval(parse);

    }
}
