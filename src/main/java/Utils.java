import ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * @author Johannes Ernstsen
 */
class Utils {
    private static boolean isTest = false;
    private static EV3LargeRegulatedMotor motorLeft;
    private static EV3LargeRegulatedMotor motorRight;

    static {
        try {
            motorLeft = new EV3LargeRegulatedMotor(MotorPort.A);
            motorRight = new EV3LargeRegulatedMotor(MotorPort.B);
        } catch (Exception e) {
            isTest = true;
        }

    }

    static void forward() {
        if (isTest) {
            System.out.println("Forward!");
        } else {
            motorLeft.rotate(180);
            motorRight.rotate(180);
        }
    }

    static void turnLeft() {
        if (isTest) {
            System.out.println("TurnLeft");
        } else {
            motorLeft.rotate(180);
            motorRight.rotate(-180);
        }
    }

    static void turnRight() {
        if (isTest) {
            System.out.println("TurnRight");
        } else {
            motorLeft.rotate(-180);
            motorRight.rotate(180);
        }
    }

    static void reverse() {
        if (isTest) {
            System.out.println("Reverse");
        } else {
            motorLeft.rotate(-180);
            motorRight.rotate(-180);
        }
    }
}
