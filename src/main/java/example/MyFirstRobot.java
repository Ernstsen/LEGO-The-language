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

        int iteration = 0;

        while(iteration < 50) {
            try {
                System.out.println("Listening for connections");
                instructions = socketListener.listenForInstructions();
            } catch (IOException e) {
                e.printStackTrace();
                instructions = easterEgg();
            }
            iteration += 1;

            // Create the interpreter and interpret the bricks.
            Interpreter interpreter = new Interpreter(motorLeft, motorRight);
            interpreter.eval(Parser.parse(instructions));
        }

        System.exit(0);
    }

    private static List<Brick> easterEgg() {
        System.out.println("Easter egg! Wuhuu!");
        Brick forward = new Brick(BrickColor.Green);
        Brick turnLeft = new Brick(BrickColor.Blue);
        Brick turnRight = new Brick(BrickColor.Red);
        List<Brick> bricks = new ArrayList<>();

        bricks.add(forward);
        bricks.add(forward);
        bricks.add(turnLeft);
        bricks.add(turnRight);
        bricks.add(turnRight);
        bricks.add(turnLeft);
        bricks.add(forward);

        return bricks;
    }
}
