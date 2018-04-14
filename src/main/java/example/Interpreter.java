package example;

import ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor;

import java.util.List;

/**
 * @author Johannes Ernstsen
 */
public class Interpreter {

    private Utils utils;

    public Interpreter(CustomEV3LargeRegulatedMotor left, CustomEV3LargeRegulatedMotor right) {
        this.utils = new Utils(left, right);
    }

    public void eval(List<Exp> asts) {
        for (Exp ast : asts) {
            doAction(ast);
        }
    }

    private void doAction(AbstractSyntaxTree abstractSyntaxTree) {
        if (abstractSyntaxTree instanceof ForwardExp) {
            utils.forward();
        } else if (abstractSyntaxTree instanceof TurnLeft) {
            utils.turnLeft();
        } else if (abstractSyntaxTree instanceof TurnRight) {
            utils.turnRight();
        } else if (abstractSyntaxTree instanceof Reverse) {
            utils.reverse();
        } else if (abstractSyntaxTree instanceof ForLoopExp) {
            ForLoopExp forLoop = (ForLoopExp) abstractSyntaxTree;
            for (int i = 0; i < forLoop.getIterations(); i++) {
                eval(forLoop.getBody());
            }
        }
    }
}
