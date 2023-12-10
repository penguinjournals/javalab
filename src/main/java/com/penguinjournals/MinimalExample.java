package com.penguinjournals;

import com.pi4j.Pi4J;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.gpio.digital.PullResistance;
import com.pi4j.util.Console;

public class MinimalExample {

    private static final int PIN_LED = 22; // PIN 15 = BCM 22

    private static int pressCount = 0;

    public static void main(String[] args) throws Exception {
        final var console = new Console();

        console.title("<-- The Pi4J Project -->", "Minimal Example project");

        var pi4j = Pi4J.newAutoContext();

        PrintInfo.printLoadedPlatforms(console, pi4j);
        PrintInfo.printDefaultPlatform(console, pi4j);
        PrintInfo.printProviders(console, pi4j);

        var ledConfig = DigitalOutput.newConfigBuilder(pi4j)
                .id("led")
                .name("LED Flasher")
                .address(PIN_LED)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .provider("pigpio-digital-output");
        var led = pi4j.create(ledConfig);

        int evenodd = -1;
        while (true) {
            if (evenodd<0) {
                console.println("LED low");
                led.low();
            } else {
                console.println("LED high");
                led.high();
            }
            evenodd = evenodd * -1;
            Thread.sleep(1000 );
        }

    }
}
