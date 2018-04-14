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

    public void eval(List<Exp> exps) {
        for (Exp exp : exps) {
            doAction(exp);
        }
    }

    private void doAction(AbstractSyntaxTree abstractSyntaxTree) {
        if (abstractSyntaxTree instanceof ForwardExp) {
            utils.forward();
        } else if (abstractSyntaxTree instanceof TurnLeft) {
            utils.turnLeft();
        } else if (abstractSyntaxTree instanceof TurnRight) {
            utils.turnRight();
        }
    }
}
