/*
 * Copyright (c) [2018] [Jonathan McIntosh, Martin Staadecker, Ryan Zazo]
 */

package ev3.robot;

import ev3.navigation.Controller;
import lejos.robotics.chassis.Chassis;

public interface Robot {
    Arm getArm();

    Chassis getChassis();

    Paddle getPaddle();

    ColorSensors getColorSensors();

    Brick getBrick();

    Controller getController();
}
