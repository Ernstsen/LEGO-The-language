import ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * @author Johannes Ernstsen
 */
class Utils {

    private static final EV3LargeRegulatedMotor motorLeft = new EV3LargeRegulatedMotor(MotorPort.A);
    private static final EV3LargeRegulatedMotor motorRight = new EV3LargeRegulatedMotor(MotorPort.B);


    static void forward() {
        motorLeft.rotate(180);
        motorRight.rotate(180);
    }

    static void turnLeft() {
        motorLeft.rotate(180);
        motorRight.rotate(-180);
    }

    static void turnRight() {
        motorLeft.rotate(-180);
        motorRight.rotate(180);
    }

    static void reverse() {
        motorLeft.rotate(-180);
        motorRight.rotate(-180);
    }
}
