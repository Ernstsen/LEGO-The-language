package example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kristian Cordes-Andersen, Johannes Ernstsen
 * <p>
 * Parses to a list of expressions.
 */
public class Parser {

    public static List<Exp> parse(List<Brick> bricks) {
        List<Exp> expressions = new ArrayList<>();

        for (Brick brick : bricks) {
            switch (brick.getColor()) {
                case Red:
                    expressions.add(new TurnRight());
                    break;
                case Green:
                    expressions.add(new ForwardExp());
                    break;
                case Blue:
                    expressions.add(new TurnLeft());
                    break;
                default:
                    expressions.add(new ForwardExp());
                    break;
            }
        }
        return expressions;
    }
}
