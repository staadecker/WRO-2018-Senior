/*
 * Copyright (c) [2018] [Jonathan McIntosh, Martin Staadecker, Ryan Zazo]
 */

package ev3.robot.hardware;

import ev3.robot.Robot;
import lejos.hardware.sensor.EV3ColorSensor;

/**
 * class allowing access to ev3's color sensors
 * TODO Create method that creates all the color sensors when called to save time while running (and uses threads)
 */
public final class EV3ColorSensors implements Robot.ColorSensors {
    private EV3ColorSensor sensorSurfaceLeft;
    private EV3ColorSensor sensorSurfaceRight;
    private EV3ColorSensor sensorContainer;
    private EV3ColorSensor sensorBoat;

    @Override
    public int getColorSurfaceLeft() {
        if (sensorSurfaceLeft == null) {
            sensorSurfaceLeft = new EV3ColorSensor(Ports.PORT_SENSOR_COLOR_SURFACE_LEFT);
        }

        return sensorSurfaceLeft.getColorID();
    }

    @Override
    public int getColorSurfaceRight() {
        if (sensorSurfaceRight == null) {
            sensorSurfaceRight = new EV3ColorSensor(Ports.PORT_SENSOR_COLOR_SURFACE_RIGHT);
        }

        return sensorSurfaceRight.getColorID();
    }

    @Override
    public int getColorContainer() {
        if (sensorContainer == null) {
            sensorContainer = new EV3ColorSensor(Ports.PORT_SENSOR_COLOR_BLOCKS);
        }

        return sensorContainer.getColorID();
    }

    @Override
    public int getColorBoat() {
        if (sensorBoat == null) {
            sensorBoat = new EV3ColorSensor(Ports.PORT_SENSOR_COLOR_BLOCKS);
        }

        return sensorBoat.getColorID();
    }
}