import java.util.List;

/**
 * @author Johannes Ernstsen
 */
public class Interpreter {

    public static void eval(List<Exp> asts) {
        for (Exp ast : asts) {
            doAction(ast);
        }
    }

    private static void doAction(AbstractSyntaxTree abstractSyntaxTree) {
        if (abstractSyntaxTree instanceof ForwardExp) {
            Utils.forward();
        } else if (abstractSyntaxTree instanceof TurnLeft) {
            Utils.turnLeft();
        } else if (abstractSyntaxTree instanceof TurnRight) {
            Utils.turnRight();
        } else if (abstractSyntaxTree instanceof Reverse) {
            Utils.reverse();
        } else if (abstractSyntaxTree instanceof ForLoopExp) {
            ForLoopExp forLoop = (ForLoopExp) abstractSyntaxTree;
            for (int i = 0; i < forLoop.getIterations(); i++) {
                eval(forLoop.getBody());
            }
        }


    }
}
