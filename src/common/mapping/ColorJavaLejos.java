/*
 * Copyright (c) [2018] [Jonathan McIntosh, Martin Staadecker, Ryan Zazo]
 */

package common.mapping;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;

/**
 * Mapping of Java swing colors to lejos colors (int).
 * For example new Color(255,0,0) (Swing) might map to Color.RED (lejos)
 * Used by the SurfaceReadings algorithm to convert a pixel on the picture (Swing) to a sensor reading (Lejos)
 */
public class ColorJavaLejos {

    @NotNull
    private static final HashMap<Color, Integer> javaToLejosMap = new HashMap<>();

    public static final Color MAP_RED = new Color(237, 28, 36);
    public static final Color MAP_GREEN = new Color(0, 172, 70);
    public static final Color MAP_BLUE = new Color(0, 117, 191);
    public static final Color MAP_YELLOW = new Color(255, 205, 3);

    static {
        javaToLejosMap.put(MAP_RED, lejos.robotics.Color.RED);
        javaToLejosMap.put(MAP_GREEN, lejos.robotics.Color.GREEN);
        javaToLejosMap.put(MAP_BLUE, lejos.robotics.Color.BLUE);
        javaToLejosMap.put(MAP_YELLOW, lejos.robotics.Color.YELLOW);
        javaToLejosMap.put(Color.WHITE, lejos.robotics.Color.WHITE);
        javaToLejosMap.put(Color.BLACK, lejos.robotics.Color.BLACK);
        javaToLejosMap.put(Color.LIGHT_GRAY, lejos.robotics.Color.WHITE);
    }

    static int getLejosColor(Color color) {
        return javaToLejosMap.get(color);
    }
}
