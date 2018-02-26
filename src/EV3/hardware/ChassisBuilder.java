/*
 * Copyright (c) [2018] [Jonathan McIntosh, Martin Staadecker, Ryan Zazo]
 */

package EV3.hardware;

import Common.Config;
import EV3.sim.AbstractMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import org.jetbrains.annotations.NotNull;

/**
 * Static class to build the chassis object
 */
public final class ChassisBuilder {

    private static final double WHEEL_DIAMETER = 55.9;
    private static final double WHEEL_OFFSET = 82.4;

    @NotNull
    public static Chassis getChassis() {
        RegulatedMotor leftMotor;
        RegulatedMotor rightMotor;

        if (Config.useSimulator) {
            leftMotor = new AbstractMotor("Left motor");
            rightMotor = new AbstractMotor("Right motor");
        } else {
            leftMotor = new EV3LargeRegulatedMotor(Ports.PORT_MOTOR_LEFT);
            rightMotor = new EV3LargeRegulatedMotor(Ports.PORT_MOTOR_RIGHT);
        }

        Wheel[] wheels = new Wheel[]{
                WheeledChassis.modelWheel(leftMotor, WHEEL_DIAMETER).offset(WHEEL_OFFSET),
                WheeledChassis.modelWheel(rightMotor, WHEEL_DIAMETER).offset(-WHEEL_OFFSET)
        };

        return new WheeledChassis(wheels, WheeledChassis.TYPE_DIFFERENTIAL);
    }
}
