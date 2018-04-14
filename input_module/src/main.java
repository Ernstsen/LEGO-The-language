import java.util.ArrayList;

/**
 * @author Johannes Ernstsen
 */
public class main {

    public static void main(String[] args) {
        ArrayList<Exp> exps = new ArrayList<>();
        exps.add(new ForwardExp());
        ArrayList<Exp> forLoop = new ArrayList<>();
        forLoop.add(new TurnLeft());
        forLoop.add(new ForwardExp());
        forLoop.add(new Reverse());
        forLoop.add(new TurnRight());
        exps.add(new ForLoopExp(3, forLoop));
        exps.add(new Reverse());
        Interpreter.eval(exps);
    }
}
