package example;

import lejos.hardware.port.Port;

/**
 * TODO: This is NOT the correct way to fix compatibility issues. Should be fixed
 */
public class CustomEV3LargeRegulatedMotor extends CustomBaseRegulatedMotor {
    private static final float MOVE_P = 4.0F;
    private static final float MOVE_I = 0.04F;
    private static final float MOVE_D = 10.0F;
    private static final float HOLD_P = 2.0F;
    private static final float HOLD_I = 0.02F;
    private static final float HOLD_D = 8.0F;
    private static final int OFFSET = 0;
    private static final int MAX_SPEED = 1050;

    public CustomEV3LargeRegulatedMotor(Port motorPort) {
        super(motorPort, 4.0F, 0.04F, 10.0F, 2.0F, 0.02F, 8.0F, 0, 1050);
    }
}
