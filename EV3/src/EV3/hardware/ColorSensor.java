/*
 * Copyright (c) [2018] [Jonathan McIntosh, Martin Staadecker, Ryan Zazo]
 */

package EV3.hardware;

import Common.Config;
import Common.mapping.SurfaceMap;
import EV3.localization.RobotPoseProvider;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.navigation.Pose;
import org.jetbrains.annotations.Nullable;

/**
 * Static class allowing access to color sensors
 */
public final class ColorSensor {
    @Nullable
    private static final EV3ColorSensor surfaceColorSensor;

    static {
        if (Config.useSimulator) {
            surfaceColorSensor = null;
        } else {
            surfaceColorSensor = new EV3ColorSensor(Ports.PORT_SENSOR_COLOR_SURFACE);
        }
    }

    public static int getSurfaceColor() {
        if (surfaceColorSensor == null) {
            Pose currentPose = RobotPoseProvider.get().getPose();
            return SurfaceMap.getColorAtPoint(currentPose.getX(), currentPose.getY());
        } else {
            return surfaceColorSensor.getColorID();
        }
    }
}