package example;

import lejos.hardware.port.MotorPort;
import socket.Listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFirstRobot {

    public static void main(final String[] args){

        List<Brick> instructions;

        // Use port 8192, since it's free and it's 2 to the power of 13.
        Listener socketListener = new Listener(8192);

        System.out.println("Creating Motor B & C");
        final CustomEV3LargeRegulatedMotor motorLeft = new CustomEV3LargeRegulatedMotor(MotorPort.B);
        final CustomEV3LargeRegulatedMotor motorRight = new CustomEV3LargeRegulatedMotor(MotorPort.C);

        //To Stop the motor in case of pkill java for example
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Emergency Stop");
            motorLeft.stop();
            motorRight.stop();
        }));

        try {
            instructions = socketListener.listenForInstructions();
        } catch (IOException e) {
            e.printStackTrace();
            instructions = easterEgg();
        }

        // Create the interpreter and interpret the bricks.
        Interpreter interpreter = new Interpreter(motorLeft, motorRight);
        interpreter.eval(Parser.parse(instructions));

        System.out.println("Defining the Stop mode");
        motorLeft.brake();
        motorRight.brake();

        System.out.println("Stop motors");
        motorLeft.stop();
        motorRight.stop();

        System.exit(0);
    }

    private static List<Brick> easterEgg() {
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

        return bricks;
    }
}
