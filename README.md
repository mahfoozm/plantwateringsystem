# Plant Watering System
Automatic Plant Watering System utilizing Java and an Arduino (Firmata)

### YouTube Video Link: https://youtu.be/iJcEGaXv8hg

## Photo of Setup and Flowchart

![Photo of Setup](https://i.imgur.com/eifqx7Q.png)

![Flowchart](https://i.imgur.com/0AXui7l.png)

## Context
An automatic plant watering system can be very useful in certain contexts. For example, if you leave on vacation, a device like this would be very useful as it would keep your plant alive without any human intervention, as the system would pump water to keep the plant alive autonomously.

## Requirements
The objectives of this project were to read values from a moisture sensor, to activate a pump to water a potted plant if the moisture sensor values crossed a certain threshold, and to display the status of the system on an OLED. In effect, this would create a plant that would automatically be watered as long as the reservoir has water (flowchart on next page). 

## Components List
To monitor and maintain soil moisture, a moisture sensor, relay board, pump, 9v battery, OLED, and grove board is required.

## Procedure
To automatically monitor and maintain soil moisture, a state machine is required. The Grove Board is connected to the Moisture Sensor which provides values that indicate if the soil is wet/dry, and if the soil is reported to be above the dryness threshold, the Grove Board will send a digital signal to the MOSFET which connects the Pump to the 9V battery, which will cause water to flow from the reservoir to the plant. The pump will continue to run until the Moisture Sensor reports that the soil is below the threshold. The status of the system will be shown on the OLED.

## Test
The soil moisture remained at a constant level of “wetness” when tested. This was confirmed as water was pumped when the soil was dry, and stopped when the soil water levels were adequate. The status of the system was updated on the OLED. 

## Conclusions
The state machine was programmed correctly, as the pump automatically activates when the soil moisture level drops below the threshold, which severely reduces the need for the user to water the plant themselves. The only intervention required by the user is to fill the reservoir when the water level is low. An issue was encountered when developing this state machine, as the moisture sensor values were being updated very slowly when the pin was placed in an analog mode. This was solved by using a TimerTask implementation instead of an event driven implementation, which allowed the pump to be turned on instantly if the moisture levels were above/below the threshold. In the future, a helpful implementation would be to sound the buzzer or turn the LED on when pumping water to alert the user, although the OLED does currently serve this purpose.
