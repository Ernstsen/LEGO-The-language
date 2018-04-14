package example;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import ev3dev.utils.Sysfs;
import lejos.hardware.Power;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomBattery extends CustomEV3Device implements Power {
	private static final Logger log = LoggerFactory.getLogger(CustomBattery.class);
	private static final String BATTERY = "power_supply";
	private static final String BATTERY_EV3 = "legoev3-battery";
	private static final String BATTERY_PISTORMS = "pistorms-battery";
	private static final String BATTERY_BRICKPI = "brickpi-battery";
	private static final String BATTERY_BRICKPI3 = "brickpi3-battery";
	private static String BATTERY_PATH;
	private static final String VOLTAGE = "voltage_now";
	private static final String CURRENT = "current_now";
	private String BATTERY_PATH_LOCAL = "";
	private static CustomBattery Instance;

	public static CustomBattery getInstance() {
		if(Instance == null) {
			Instance = new CustomBattery();
		}

		return Instance;
	}

	private CustomBattery() {
		BATTERY_PATH = this.ROOT_PATH + "/" + "power_supply";
		EV3DevPlatform platform = this.getPlatform();
		if(platform.equals(EV3DevPlatform.EV3BRICK)) {
			this.BATTERY_PATH_LOCAL = this.BATTERY_PATH_LOCAL + BATTERY_PATH + "/" + "legoev3-battery";
		} else if(platform.equals(EV3DevPlatform.PISTORMS)) {
			this.BATTERY_PATH_LOCAL = this.BATTERY_PATH_LOCAL + BATTERY_PATH + "/" + "pistorms-battery";
		} else if(platform.equals(EV3DevPlatform.BRICKPI)) {
			this.BATTERY_PATH_LOCAL = this.BATTERY_PATH_LOCAL + BATTERY_PATH + "/" + "brickpi-battery";
		} else if(platform.equals(EV3DevPlatform.BRICKPI3)) {
			this.BATTERY_PATH_LOCAL = this.BATTERY_PATH_LOCAL + BATTERY_PATH + "/" + "brickpi3-battery";
		} else if(platform.equals(EV3DevPlatform.UNKNOWN)) {
			throw new RuntimeException("Platform Unknown");
		}

	}

	public int getVoltageMilliVolt() {
		return (int)Sysfs.readFloat(this.BATTERY_PATH_LOCAL + "/" + "voltage_now") / 1000;
	}

	public float getVoltage() {
		return Sysfs.readFloat(this.BATTERY_PATH_LOCAL + "/" + "voltage_now") / 1000000.0F;
	}

	public float getBatteryCurrent() {
		if(this.getPlatform().equals(EV3DevPlatform.EV3BRICK)) {
			return Sysfs.readFloat(BATTERY_PATH + "/" + "legoev3-battery" + "/" + "current_now");
		} else {
			log.warn("This method is not available for {} & {}", EV3DevPlatform.PISTORMS, EV3DevPlatform.BRICKPI);
			return -1.0F;
		}
	}

	public float getMotorCurrent() {
		throw new UnsupportedOperationException("This feature is not implemented");
	}
}
