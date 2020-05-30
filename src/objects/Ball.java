/*
   the ball, which shows by shooting on cannon.
 */

package objects;


import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

public class Ball {

    public ImageIcon ballimg = new ImageIcon("src/res/ball2.png");

    public final int ball_width = 50;
    public final int ball_height = 50;

    private final double g = 9.80665; // gravity acceleration on the Earth
    private final double dt = 0.005;

    public double alpha = Math.toRadians(90);

    public double x0 = 100;     // x coordinate of the ball
    public double y0 = 300;     // y coordinate of the ball

    public double dr0 = 1;

    public double moveX(double t) {
        double x = x0 + dr0 * Math.cos(alpha) * t;
        return x;
    }

    public double moveY(double t) {
        double y = y0 + dr0 * Math.sin(alpha) * t - g * t * t / 2;
        return y;
    }

    public List<ArrayList> getCoordinates(double dr0) {

        ArrayList<Double> X = new ArrayList<>();
        ArrayList<Double> Y = new ArrayList<>();
        X.add(x0);
        Y.add(y0);

        double t = 0;

        while (true) {
            t += dt;
            double newY = moveY(t);
            if (newY < 0) {
                break;
            } else {
                double newX = moveX(t);
                X.add(newX);
                Y.add(newY);
            }
        }

            List<ArrayList> result_coordinates = new ArrayList<>();
            result_coordinates.add(X);
            result_coordinates.add(Y);
            return result_coordinates;

        }

}
