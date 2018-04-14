package example;

import lejos.utility.Delay;

/**
 * @author Johannes Ernstsen
 */
public class Utils {
    private boolean isTest = false;
    private CustomEV3LargeRegulatedMotor motorLeft;
    private CustomEV3LargeRegulatedMotor motorRight;

    public Utils(CustomEV3LargeRegulatedMotor left, CustomEV3LargeRegulatedMotor right) {
        this.motorLeft = left;
        this.motorRight = right;
    }

    public void forward() {
        if (isTest) {
            System.out.println("Forward!");
        } else {
            System.out.println("Forward!");
            motorLeft.forward();
            motorRight.forward();
            Delay.msDelay(1000);
            motorLeft.stop();
            motorRight.stop();
        }
    }

    public void turnLeft() {
        if (isTest) {
            System.out.println("Turn left");
        } else {
            System.out.println("Turn left");
            motorLeft.backward();
            motorRight.forward();
            Delay.msDelay(300);
            motorLeft.stop();
            motorRight.stop();
        }
    }

    public void turnRight() {
        if (isTest) {
            System.out.println("Turn right");
        } else {
            System.out.println("Turn right");
            motorLeft.forward();
            motorRight.backward();
            Delay.msDelay(300);
            motorLeft.stop();
            motorRight.stop();
        }
    }

    public void reverse() {
        if (isTest) {
            System.out.println("Reverse");
        } else {
            System.out.println("Reverse");
            motorLeft.backward();
            motorRight.backward();
            Delay.msDelay(1000);
            motorLeft.stop();
            motorRight.stop();
        }
    }
}