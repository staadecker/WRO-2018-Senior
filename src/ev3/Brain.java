/*
 * Copyright (c) [2018] [Jonathan McIntosh, Martin Staadecker, Ryan Zazo]
 */

package ev3;

import ev3.robot.EV3Robot;

/**
 * Specifies the sequence of actions the robot should do to win the competition !
 */
class Brain {
    private static final String LOG_TAG = Brain.class.getSimpleName();

    private final EV3Robot robot;
    private final Controller controller;

    Brain(EV3Robot robot, Controller controller) {
        this.robot = robot;
        this.controller = controller;
    }

    void start() {
        controller.goToStartIntersection(); //Leave starting area
        robot.getClaw().drop(true); //Lower claw
        controller.goToTempRegGreen();
        robot.getClaw().raise();
        controller.goBackTempReg(true, false); //Go back to line
        /*
         *
         * controller.goToMiddleBoat
         * controller.turn(90)
         * controller.doHalfBoatRun(immediateReturn = true)
         *
         * controller.turn(180)
         * controller.goToLastBoat
         *
         */

        /*
        * start
        * go to first tempreg
        * scan the temp reg while in motion
        * pick up tempreg if scanned
        * if not go to next temp reg
        *
        * go to boats
        * scan boat colors
        * if boat = current color
        * drop tempreg on boat
        * return to center
        * Loop twice {
        *   go to next temp reg
        *   pickup temp reg
        *   go to boats
        *   scan boats for current color
        *   drop off temp reg
        *   return to center
        * }
        * go to start
        */

    }
}
