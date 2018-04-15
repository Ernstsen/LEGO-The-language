package example;

import ev3dev.actuators.LCD;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.robotics.Color;

/**
 * @author Marina Alenskaja
 * TODO: Fix version incompatibility issues, and re-enable
 */
public class LCDUtils {

	//public static GraphicsLCD lcd = LCD.getInstance();

	static void writeMessage(final String message) {
		//clear();
		//lcd.setColor(Color.BLACK);
		//lcd.drawString(message, 50, 50, 0);
	//	lcd.refresh();
	}

	// clears the display
	static void clear() {
		//lcd.setColor(Color.WHITE);
		//lcd.fillRect(0,0,lcd.getWidth(), lcd.getHeight());
	}
}