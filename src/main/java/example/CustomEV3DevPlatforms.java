package example;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import ev3dev.hardware.EV3DevFileSystem;
import ev3dev.utils.Sysfs;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: This is NOT the correct way to fix compatibility issues. Should be fixed
 */
public abstract class CustomEV3DevPlatforms extends EV3DevFileSystem {
    private static final Logger log = LoggerFactory.getLogger(CustomEV3DevPlatforms.class);

    public CustomEV3DevPlatforms() {
    }

    protected EV3DevPlatform getPlatform() {
        String BATTERY = "/power_supply";
        String BATTERY_PATH = this.ROOT_PATH + "/power_supply";
        String BATTERY_EV3 = "legoev3-battery";
        String BATTERY_PISTORMS = "pistorms-battery";
        String BATTERY_BRICKPI = "brickpi-battery";
        String BATTERY_BRICKPI3 = "brickpi3-battery";
        String EV3BRICK_DISCOVERY_PATTERN_PATH = BATTERY_PATH + "/" + "lego-ev3-battery";
        String PISTORMS_DISCOVERY_PATTERN_PATH = BATTERY_PATH + "/" + "pistorms-battery";
        String BRICKPI_DISCOVERY_PATTERN_PATH = BATTERY_PATH + "/" + "brickpi-battery";
        String BRICKPI3_DISCOVERY_PATTERN_PATH = BATTERY_PATH + "/" + "brickpi3-battery";
        if (Sysfs.existPath(EV3BRICK_DISCOVERY_PATTERN_PATH)) {
            if (log.isTraceEnabled()) {
                log.trace(EV3BRICK_DISCOVERY_PATTERN_PATH);
            }

            log.trace("Detected platform: " + EV3DevPlatform.EV3BRICK);
            return EV3DevPlatform.EV3BRICK;
        } else if (Sysfs.existPath(PISTORMS_DISCOVERY_PATTERN_PATH)) {
            if (log.isTraceEnabled()) {
                log.trace(PISTORMS_DISCOVERY_PATTERN_PATH);
            }

            log.trace("Detected platform: " + EV3DevPlatform.PISTORMS);
            return EV3DevPlatform.PISTORMS;
        } else if (Sysfs.existPath(BRICKPI_DISCOVERY_PATTERN_PATH)) {
            if (log.isTraceEnabled()) {
                log.trace(BRICKPI_DISCOVERY_PATTERN_PATH);
            }

            log.trace("Detected platform: " + EV3DevPlatform.BRICKPI);
            return EV3DevPlatform.BRICKPI;
        } else if (Sysfs.existPath(BRICKPI3_DISCOVERY_PATTERN_PATH)) {
            if (log.isTraceEnabled()) {
                log.trace(BRICKPI3_DISCOVERY_PATTERN_PATH);
            }

            log.trace("Detected platform: " + EV3DevPlatform.BRICKPI3);
            return EV3DevPlatform.BRICKPI3;
        } else {
            String OS_NAME = System.getProperty("os.name");
            String OS_VERSION = System.getProperty("os.version");
            String message = "Platform not supported: " + OS_NAME + " " + OS_VERSION;
            log.error(message);
            throw new RuntimeException(message);
        }
    }

    protected String getMotorPort(Port port) {
        if (this.getPlatform().equals(EV3DevPlatform.EV3BRICK)) {
            if (port.equals(MotorPort.A)) {
                return "outA";
            }

            if (port.equals(MotorPort.B)) {
                return "outB";
            }

            if (port.equals(MotorPort.C)) {
                return "outC";
            }

            if (port.equals(MotorPort.D)) {
                return "outD";
            }
        } else if (this.getPlatform().equals(EV3DevPlatform.BRICKPI)) {
            if (port.equals(MotorPort.A)) {
                return "ttyAMA0:MA";
            }

            if (port.equals(MotorPort.B)) {
                return "ttyAMA0:MB";
            }

            if (port.equals(MotorPort.C)) {
                return "ttyAMA0:MC";
            }

            if (port.equals(MotorPort.D)) {
                return "ttyAMA0:MD";
            }
        } else if (this.getPlatform().equals(EV3DevPlatform.BRICKPI3)) {
            if (port.equals(MotorPort.A)) {
                return "spi0.1:MA";
            }

            if (port.equals(MotorPort.B)) {
                return "spi0.1:MB";
            }

            if (port.equals(MotorPort.C)) {
                return "spi0.1:MC";
            }

            if (port.equals(MotorPort.D)) {
                return "spi0.1:MD";
            }
        } else {
            if (port.equals(MotorPort.A)) {
                return "pistorms:BBM1";
            }

            if (port.equals(MotorPort.B)) {
                return "pistorms:BBM2";
            }

            if (port.equals(MotorPort.C)) {
                return "pistorms:BAM2";
            }

            if (port.equals(MotorPort.D)) {
                return "pistorms:BAM1";
            }
        }

        return null;
    }

    protected String getSensorPort(Port port) {
        if (this.getPlatform().equals(EV3DevPlatform.EV3BRICK)) {
            if (port.equals(SensorPort.S1)) {
                return "in1";
            }

            if (port.equals(SensorPort.S2)) {
                return "in2";
            }

            if (port.equals(SensorPort.S3)) {
                return "in3";
            }

            if (port.equals(SensorPort.S4)) {
                return "in4";
            }
        } else if (this.getPlatform().equals(EV3DevPlatform.BRICKPI)) {
            if (port.equals(SensorPort.S1)) {
                return "ttyAMA0:S1";
            }

            if (port.equals(SensorPort.S2)) {
                return "ttyAMA0:S2";
            }

            if (port.equals(SensorPort.S3)) {
                return "ttyAMA0:S3";
            }

            if (port.equals(SensorPort.S4)) {
                return "ttyAMA0:S4";
            }
        } else if (this.getPlatform().equals(EV3DevPlatform.BRICKPI3)) {
            if (port.equals(SensorPort.S1)) {
                return "spi0.1:S1";
            }

            if (port.equals(SensorPort.S2)) {
                return "spi0.1:S2";
            }

            if (port.equals(SensorPort.S3)) {
                return "spi0.1:S3";
            }

            if (port.equals(SensorPort.S4)) {
                return "spi0.1:S4";
            }
        } else {
            if (port.equals(SensorPort.S1)) {
                return "pistorms:BBS2";
            }

            if (port.equals(SensorPort.S2)) {
                return "pistorms:BBS1";
            }

            if (port.equals(SensorPort.S3)) {
                return "pistorms:BAS1";
            }

            if (port.equals(SensorPort.S4)) {
                return "pistorms:BAS2";
            }
        }

        return null;
    }

    public enum EV3DevPlatform {
        EV3BRICK("EV3BRICK"),
        PISTORMS("PISTORMS"),
        BRICKPI("BRICKPI"),
        BRICKPI3("BRICKPI3"),
        UNKNOWN("UNKNOWN");

        private String platform;

        private EV3DevPlatform(String stringVal) {
            this.platform = stringVal;
        }

        public String toString() {
            return this.platform;
        }

        public static String getPlatformByString(String code) {
            EV3DevPlatform[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                EV3DevPlatform e = var1[var3];
                if (code == e.platform) {
                    return e.name();
                }
            }

            return UNKNOWN.toString();
        }
    }
}
