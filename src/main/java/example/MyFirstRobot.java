package example;

import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

import java.util.ArrayList;
import java.util.List;

public class MyFirstRobot {

    public static void main(final String[] args){

        System.out.println("Creating Motor B & C");
        final CustomEV3LargeRegulatedMotor motorLeft = new CustomEV3LargeRegulatedMotor(MotorPort.B);
        final CustomEV3LargeRegulatedMotor motorRight = new CustomEV3LargeRegulatedMotor(MotorPort.C);

        //To Stop the motor in case of pkill java for example
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("Emergency Stop");
                motorLeft.stop();
                motorRight.stop();
            }
        }));

        Brick forward = new Brick(6, 2, BrickColor.DarkGreen);
        Brick loopStart = new Brick(6, 2, BrickColor.White);
        Brick iterations = new Brick(3, 1, BrickColor.DarkBlue);
        Brick loopEnd = new Brick(6, 2, BrickColor.White);
        Brick turnLeft = new Brick(3, 2, BrickColor.Red);
        Brick reverse = new Brick(8, 3, BrickColor.Black);
        Brick turnRight = new Brick(6, 8, BrickColor.Yellow);
        List<Brick> bricks = new ArrayList<>();

        bricks.add(forward);
        bricks.add(loopStart);
        bricks.add(iterations); //Value=6
        bricks.add(forward);
        bricks.add(turnLeft);
        bricks.add(reverse);
        bricks.add(turnRight);
        bricks.add(loopEnd);
        bricks.add(reverse);

        System.out.println("Defining the Stop mode");
        motorLeft.brake();
        motorRight.brake();

        System.out.println(Parser.parse(bricks).toString());
        List<Exp> parse = Parser.parse(bricks);
        System.out.println("parsed");
        Interpreter interpreter = new Interpreter(motorLeft, motorRight);
        System.out.println("interpreter initialized");
        interpreter.eval(parse);

        System.out.println("Stop motors");
        motorLeft.stop();
        motorRight.stop();


        System.exit(0);
    }
}
