package navigation;

import PC.Displayable;
import com.sun.istack.internal.NotNull;
import lejos.robotics.Transmittable;
import lejos.robotics.geometry.Point;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.Pose;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Inspired by Lawrie Griffiths' MCLParticle class in LEJOS EV3 Source code
 */
public class Particle implements Transmittable, Displayable {
    private static final String LOG_TAG = Particle.class.getSimpleName();

    private static final float DISPLAY_TAIL_LENGTH = 2;
    private static final float DISPLAY_ANGLE_WIDTH = 10;

    private static final Random rand = new Random();

    @NotNull
    private Pose pose;
    private float weight = 1;

    Particle(@NotNull DataInputStream dis) throws IOException {
        this.pose = new Pose(0, 0, 0);
        loadObject(dis);
    }

    Particle(@NotNull Pose pose) {
        this.pose = pose;
    }

    @NotNull
    public float getWeight() {
        return weight;
    }

    public void setWeight(@NotNull float weight) {
        this.weight = weight;
    }

    @NotNull
    public Pose getPose() {
        return pose;
    }

    @NotNull
    public Point getLocation() {
        return pose.getLocation();
    }

    public void calculateWeight(@NotNull Reading readings) {
        weight = readings.calculateWeight(this);
    }

    public void applyMove(@NotNull Move move, @NotNull float distanceNoiseFactor, @NotNull float angleNoiseFactor) {
        float hypotenuse = move.getDistanceTraveled();
        double theta = Math.toRadians(pose.getHeading());

        float ym = hypotenuse * ((float) Math.sin(theta));
        float xm = hypotenuse * ((float) Math.cos(theta));

        float x = (float) (pose.getX() + xm + (distanceNoiseFactor * xm * rand.nextGaussian()));
        float y = (float) (pose.getY() + ym + (distanceNoiseFactor * ym * rand.nextGaussian()));

        float heading = ((pose.getHeading() + move.getAngleTurned() + (float) (move.getAngleTurned() * angleNoiseFactor * rand.nextGaussian())) + 0.5F) % 360;

        pose = new Pose(x, y, heading);
    }

    public void displayOnGUI(@NotNull Graphics g) {
        g.setColor(Color.BLUE);

        Point leftEnd = pose.pointAt((int) DISPLAY_TAIL_LENGTH, pose.getHeading() + 180 - DISPLAY_ANGLE_WIDTH);
        Point rightEnd = pose.pointAt((int) DISPLAY_TAIL_LENGTH, pose.getHeading() + 180 + DISPLAY_ANGLE_WIDTH);

        int[] xValues = new int[]{
                (int) pose.getX(),
                (int) leftEnd.x,
                (int) rightEnd.x
        };

        int[] yValues = new int[]{
                (int) pose.getY(),
                (int) leftEnd.y,
                (int) rightEnd.y
        };

        g.fillPolygon(xValues, yValues, xValues.length);
    }

    @Override
    public void loadObject(@NotNull DataInputStream dis) throws IOException {
        weight = dis.readFloat();
        pose.loadObject(dis);
    }

    @Override
    public void dumpObject(@NotNull DataOutputStream dos) throws IOException {
        dos.writeFloat(weight);
        pose.dumpObject(dos);
    }
}