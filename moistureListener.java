// EECS 1021 Minor Project W22
// Mohammad Mahfooz
// 218621045// for minor project

package eecs1021;

import org.firmata4j.Pin;
import org.firmata4j.ssd1306.MonochromeCanvas;
import org.firmata4j.ssd1306.SSD1306;
import java.io.IOException;
import java.util.TimerTask;

public class moistureListener extends TimerTask {

    private final SSD1306 display;
    private final Pin pumpPin;
    private final Pin moisturePin;

    int dryValue = 716;
    int moistureThreshold = 614;
    int saturatedValue = 552;

    moistureListener(SSD1306 display, Pin pumpPin, Pin moisturePin)
    {
        this.display = display;
        this.pumpPin = pumpPin;
        this.moisturePin = moisturePin;
    }

    @Override
    public void run() {

        int moistureValue = (int) this.moisturePin.getValue();
        int potBar = (int) this.moisturePin.getValue() / 8;

        if (moistureValue >= dryValue) {
            try {
                pumpPin.setValue(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            display.getCanvas().clear();
            display.getCanvas().drawString(0, 0, "Soil Moisture: Dry!");
            display.getCanvas().drawString(0, 10, "Pump status: pumping!");
            display.getCanvas().drawString(0, 40, "Moisture Value: " + String.valueOf(moistureValue));
            display.getCanvas().drawHorizontalLine(0,55,64, MonochromeCanvas.Color.DARK);
            display.getCanvas().drawHorizontalLine(0,55,potBar,MonochromeCanvas.Color.BRIGHT);
            display.display();
        }

        else if (moistureValue > moistureThreshold) {
            try {
                pumpPin.setValue(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            display.getCanvas().clear();
            display.getCanvas().drawString(0, 0, "Soil Moisture: Dry!");
            display.getCanvas().drawString(0, 10, "Pump status: pumping!");
            display.getCanvas().drawString(0, 40, "Moisture Value: " + String.valueOf(moistureValue));
            display.getCanvas().drawHorizontalLine(0,55,64, MonochromeCanvas.Color.DARK);
            display.getCanvas().drawHorizontalLine(0,55,potBar,MonochromeCanvas.Color.BRIGHT);
            display.display();
        }

        else if (moistureValue <= saturatedValue) {
            try {
                pumpPin.setValue(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            display.getCanvas().clear();
            display.getCanvas().drawString(0, 0, "Soil Moisture: Wet!");
            display.getCanvas().drawString(0, 10, "Pump status: inactive");
            display.getCanvas().drawString(0, 40, "Moisture Value: " + String.valueOf(moistureValue));
            display.getCanvas().drawHorizontalLine(0,55,64, MonochromeCanvas.Color.DARK);
            display.getCanvas().drawHorizontalLine(0,55,potBar,MonochromeCanvas.Color.BRIGHT);
            display.display();

        }
    }
}