package example;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import ev3dev.sensors.Battery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.RegulatedMotorListener;
import lejos.utility.Delay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CustomBaseRegulatedMotor extends CustomEv3DevMotorDevice implements RegulatedMotor {
    private static final Logger log = LoggerFactory.getLogger(CustomBaseRegulatedMotor.class);
    protected final int MAX_SPEED_AT_9V;
    private int speed = 360;
    protected int acceleration = 6000;
    private boolean regulationFlag = true;
    private final List<RegulatedMotorListener> listenerList = Collections.synchronizedList(new ArrayList());

    public CustomBaseRegulatedMotor(Port motorPort, float moveP, float moveI, float moveD, float holdP, float holdI, float holdD, int offset, int maxSpeed) {
        this.MAX_SPEED_AT_9V = maxSpeed;
        String port = this.getMotorPort(motorPort);
        log.debug("Detecting motor on port: {}", port);
        this.detect("lego-port", port);
        log.debug("Setting port in mode: {}", "tacho-motor");
        this.setStringAttribute("mode", "tacho-motor");
        Delay.msDelay(500L);
        this.detect("tacho-motor", port);
        this.setStringAttribute("command", "reset");
    }

    public boolean suspendRegulation() {
        this.regulationFlag = false;
        return this.regulationFlag;
    }

    public int getTachoCount() {
        return this.getIntegerAttribute("position");
    }

    public float getPosition() {
        return 0.0F;
    }

    public void forward() {
        this.setStringAttribute("polarity", "normal");
        this.setSpeed(this.speed);
        if (!this.regulationFlag) {
            this.setStringAttribute("command", "run-direct");
        } else {
            this.setStringAttribute("command", "run-forever");
        }

        Iterator var1 = this.listenerList.iterator();

        while(var1.hasNext()) {
            RegulatedMotorListener listener = (RegulatedMotorListener)var1.next();
            listener.rotationStarted(this, this.getTachoCount(), this.isStalled(), System.currentTimeMillis());
        }

    }

    public void backward() {
        this.setStringAttribute("polarity", "inversed");
        this.setSpeed(this.speed);
        if (!this.regulationFlag) {
            this.setStringAttribute("command", "run-direct");
        } else {
            this.setStringAttribute("command", "run-forever");
        }

        Iterator var1 = this.listenerList.iterator();

        while(var1.hasNext()) {
            RegulatedMotorListener listener = (RegulatedMotorListener)var1.next();
            listener.rotationStarted(this, this.getTachoCount(), this.isStalled(), System.currentTimeMillis());
        }

    }

    public void flt(boolean b) {
        this.flt();
    }

    public void flt() {
        log.debug("Not implemented");
        throw new RuntimeException("Not implemented");
    }

    public void coast() {
        this.setStringAttribute("stop_action", "coast");
    }

    public void brake() {
        this.setStringAttribute("stop_action", "brake");
    }

    public void hold() {
        this.setStringAttribute("stop_action", "hold");
    }

    public void stop() {
        this.setStringAttribute("command", "stop");
        Iterator var1 = this.listenerList.iterator();

        while(var1.hasNext()) {
            RegulatedMotorListener listener = (RegulatedMotorListener)var1.next();
            listener.rotationStopped(this, this.getTachoCount(), this.isStalled(), System.currentTimeMillis());
        }

    }

    public void stop(boolean b) {
        this.setStringAttribute("command", "stop");
    }

    public boolean isMoving() {
        return this.getStringAttribute("state").contains("running");
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        if (!this.regulationFlag) {
            this.setIntegerAttribute("duty_cycle_sp", speed);
        } else {
            this.setIntegerAttribute("speed_sp", speed);
        }

    }

    public void resetTachoCount() {
        this.setStringAttribute("command", "reset");
        this.regulationFlag = true;
    }

    public void rotate(int angle, boolean immediateReturn) {
        this.setIntegerAttribute("position_sp", angle);
        this.setStringAttribute("command", "run-to-rel-pos");
        if (!immediateReturn) {
            while(true) {
                if (this.isMoving()) {
                    continue;
                }
            }
        }

        Iterator var3 = this.listenerList.iterator();

        while(var3.hasNext()) {
            RegulatedMotorListener listener = (RegulatedMotorListener)var3.next();
            listener.rotationStarted(this, this.getTachoCount(), this.isStalled(), System.currentTimeMillis());
        }

    }

    public void rotate(int angle) {
        this.rotate(angle, false);
    }

    public void rotateTo(int limitAngle, boolean immediateReturn) {
        this.setIntegerAttribute("position_sp", limitAngle);
        this.setStringAttribute("command", "run-to-abs-pos");
        if (!immediateReturn) {
            while(true) {
                if (this.isMoving()) {
                    continue;
                }
            }
        }

        Iterator var3 = this.listenerList.iterator();

        while(var3.hasNext()) {
            RegulatedMotorListener listener = (RegulatedMotorListener)var3.next();
            listener.rotationStarted(this, this.getTachoCount(), this.isStalled(), System.currentTimeMillis());
        }

    }

    public void rotateTo(int limitAngle) {
        this.rotateTo(limitAngle, false);
    }

    public int getSpeed() {
        return !this.regulationFlag ? this.getIntegerAttribute("duty_cycle_sp") : this.getIntegerAttribute("speed_sp");
    }

    public boolean isStalled() {
        return this.getStringAttribute("state").contains("stalled");
    }

    public int getRotationSpeed() {
        return 0;
    }

    public void addListener(RegulatedMotorListener regulatedMotorListener) {
        this.listenerList.add(regulatedMotorListener);
    }

    public RegulatedMotorListener removeListener() {
        if (this.listenerList.size() > 0) {
            this.listenerList.remove(this.listenerList.size() - 1);
        }

        return null;
    }

    public void waitComplete() {
        while(this.isMoving()) {
            ;
        }

    }

    public float getMaxSpeed() {
        return Battery.getInstance().getVoltage() * (float)this.MAX_SPEED_AT_9V / 9.0F * 0.9F;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = Math.abs(acceleration);
        log.warn("Not executed internally the method: setAcceleration");
    }

    public void synchronizeWith(RegulatedMotor[] regulatedMotors) {
        log.warn("Not implemented the method: synchronizeWith");
    }

    public void startSynchronization() {
        log.warn("Not implemented the method: startSynchronization");
    }

    public void endSynchronization() {
        log.warn("Not implemented the method: endSynchronization");
    }
}
