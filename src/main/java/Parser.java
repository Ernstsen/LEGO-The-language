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
                case "8Black":
                    expressions.add(new Reverse());
                    break;
                case "6White":
                    brick = brickIter.next();
                    int iterations = brick.getHeight() * brick.getWidth();
                    List<Brick> loopBricks = new ArrayList<>();
                    while (brick.getColor() != BrickColor.White) {
                        brick = brickIter.next();
                        loopBricks.add(brick);
                    }
                    expressions.add(new ForLoopExp(iterations, parse(loopBricks)));
                default:
                    expressions.add(new IntLit(brick.getWidth() * brick.getHeight()));
                    break;
            }
        }
        return expressions;
    }
}
