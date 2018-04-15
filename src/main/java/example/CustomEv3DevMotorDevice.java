package example;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: This is NOT the correct way to fix compatibility issues. Should be fixed
 */
public abstract class CustomEv3DevMotorDevice extends CustomEV3Device {
    private static final Logger log = LoggerFactory.getLogger(CustomEv3DevMotorDevice.class);
    protected static final String TACHO_MOTOR = "tacho-motor";
    protected static final String DC_MOTOR = "dc-motor";
    protected static final String AUTO_MODE = "auto";
    protected static final String POSITION_SP = "position_sp";
    protected static final String POSITION = "position";
    protected static final String SPEED = "speed_sp";
    protected static final String DUTY_CYCLE = "duty_cycle_sp";
    protected static final String COMMAND = "command";
    protected static final String RUN_FOREVER = "run-forever";
    protected static final String RUN_DIRECT = "run-direct";
    protected static final String RUN_TO_REL_POS = "run-to-rel-pos";
    protected static final String RUN_TO_ABS_POS = "run-to-abs-pos";
    protected static final String STOP_COMMAND = "stop_action";
    protected static final String COAST = "coast";
    protected static final String BRAKE = "brake";
    protected static final String HOLD = "hold";
    protected static final String STOP = "stop";
    protected static final String RESET = "reset";
    protected static final String STATE = "state";
    protected static final String STATE_RUNNING = "running";
    protected static final String STATE_STALLED = "stalled";
    protected static final String POWER = "power";
    protected static final String POLARITY = "polarity";
    protected static final String POLARITY_NORMAL = "normal";
    protected static final String POLARITY_INVERSED = "inversed";

    public CustomEv3DevMotorDevice() {
    }
}
