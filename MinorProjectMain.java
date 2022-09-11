// EECS 1021 Minor Project W22
// Mohammad Mahfooz
// 218621045

package eecs1021;

import org.firmata4j.I2CDevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.ssd1306.SSD1306;
import java.io.IOException;
import java.util.Timer;

public class MinorProjectMain {

    public static void main(String[] args) throws IOException, InterruptedException {

        var arduinoObject = new FirmataDevice("COM4");
        try
        {
            arduinoObject.start();
            System.out.println("Board started.");
            arduinoObject.ensureInitializationIsDone();
        }

        catch (Exception ex)
        {
            System.out.println("Couldn't connect to board.");
        }

        finally
        {
            I2CDevice i2cObject = arduinoObject.getI2CDevice((byte) 0x3C);
            SSD1306 display = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64);
            display.init();

            var Moisture = arduinoObject.getPin(14);
            var Pump = arduinoObject.getPin(2);

            Moisture.setMode(Pin.Mode.ANALOG);
            Pump.setMode(Pin.Mode.OUTPUT);

            moistureListener theMoistureListener = new moistureListener(display, Pump, Moisture);
            new Timer().schedule(theMoistureListener, 0, 1000);
        }
    }
}