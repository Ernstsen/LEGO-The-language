package example;

import ev3dev.hardware.EV3DevDevice;
import ev3dev.utils.Sysfs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * TODO: This is NOT the correct way to fix compatibility issues. Should be fixed
 */
public class CustomEV3Device extends CustomEV3DevPlatforms {

    private static final Logger log = LoggerFactory.getLogger(EV3DevDevice.class);
    protected static final String LEGO_PORT = "lego-port";
    protected static final String ADDRESS = "address";
    protected static final String LEGO_SENSOR = "lego-sensor";
    protected static final String MODE = "mode";
    protected static final String DEVICE = "set_device";
    protected File PATH_DEVICE = null;

    public CustomEV3Device() {
    }

    protected void detect(String type, String portName) {
        if (log.isDebugEnabled()) {
            log.debug("Detecting device on port: {}", portName);
        }

        String devicePath = this.ROOT_PATH + "/" + type;
        if (log.isTraceEnabled()) {
            log.trace(devicePath);
        }

        List<File> deviceAvailables = Sysfs.getElements(devicePath);
        boolean connected = false;
        Iterator var7 = deviceAvailables.iterator();

        while(var7.hasNext()) {
            File deviceAvailable = (File)var7.next();
            this.PATH_DEVICE = deviceAvailable;
            String pathDeviceName = this.PATH_DEVICE + "/" + "address";
            String result = Sysfs.readString(pathDeviceName);
            if (log.isTraceEnabled()) {
                log.trace("Port expected: {}, actual: {}.", portName, result);
            }

            if (result.contains(portName)) {
                if (log.isDebugEnabled()) {
                    log.debug("Detected port on path: {}", pathDeviceName);
                }

                connected = true;
                break;
            }
        }

        if (!connected) {
            throw new RuntimeException("The device was not detected in: " + portName);
        }
    }

    protected String getStringAttribute(String attribute) {
        return Sysfs.readString(this.PATH_DEVICE + "/" + attribute);
    }

    protected int getIntegerAttribute(String attribute) {
        return Sysfs.readInteger(this.PATH_DEVICE + "/" + attribute);
    }

    protected void setStringAttribute(String attribute, String value) {
        boolean result = Sysfs.writeString(this.PATH_DEVICE + "/" + attribute, value);
        if (!result) {
            throw new RuntimeException("Operation not executed: " + this.PATH_DEVICE + "/" + attribute + " with value " + value);
        }
    }

    protected void setIntegerAttribute(String attribute, int value) {
        boolean result = Sysfs.writeInteger(this.PATH_DEVICE + "/" + attribute, value);
        if (!result) {
            throw new RuntimeException("Operation not executed: " + this.PATH_DEVICE + "/" + attribute + " with value " + value);
        }
    }
}
