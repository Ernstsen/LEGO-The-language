package example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Kristian Cordes-Andersen, Johannes Ernstsen
 * <p>
 * Parses to a list of expressions.
 */
public class Parser {

    public static List<Exp> parse(List<Brick> bricks) {
        List<Exp> expressions = new ArrayList<>();

        Iterator<Brick> brickIter = bricks.listIterator();
        while (brickIter.hasNext()) {
            Brick brick = brickIter.next();
            switch (brick.getWidth() + brick.getColor().toString()) {
                case "6DarkGreen":
                    expressions.add(new ForwardExp());
                    break;
                case "3Red":
                    expressions.add(new TurnLeft());
                    break;
                case "6Yellow":
                    expressions.add(new TurnRight());
                    break;
                case "8Black":
                    expressions.add(new Reverse());
                    break;
                case "6White":
                    if (!brickIter.hasNext()) {
                        throw new RuntimeException("Invalid syntax: Forloop followed by end of input");
                    }
                    brick = brickIter.next();
                    int iterations = brick.getHeight() * brick.getWidth();
                    List<Brick> loopBricks = new ArrayList<>();
                    while (true) {
                        brick = brickIter.next();
                        if (brick.getColor() != BrickColor.White) {
                            loopBricks.add(brick);
                        } else {
                            break;
                        }
                    }
                    expressions.add(new ForLoopExp(iterations, parse(loopBricks)));
                    break;
                default:
                    expressions.add(new IntLit(brick.getWidth() * brick.getHeight()));
                    break;
            }
        }
        return expressions;
    }
}
