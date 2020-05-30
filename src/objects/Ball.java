/*
   the ball, which shows by shooting on cannon.
 */

package objects;


import javax.lang.model.type.ErrorType;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

public class Ball {

    public ImageIcon ballimg = new ImageIcon("src/res/ball2.png");

    public final int ball_width = 50;
    public final int ball_height = 50;

    private final double g = 9.80665; // gravity acceleration on the Earth
    private final double dt = 0.05;

    //public double alpha = Math.toRadians(39);

    public double x0 = 100;     // x coordinate of the ball
    public double y0 = 300;     // y coordinate of the ball

    public double dr0 = 1;

    public double moveX(double t, double alpha) {
        double x0 = getX0(alpha);
        alpha = Math.toRadians(alpha);
        double x = x0 + dr0 * Math.cos(alpha) * t;
        return x;
    }

    public double moveY(double t, double alpha) {
        double y0 = getY0(alpha);
        alpha = Math.toRadians(alpha);
        double y = y0 - dr0 * Math.sin(alpha) * t + g * t * t / 2;
        return y;
    }

    public double getX(int i, double dr0, WallsAndRoad w, Cannon c) {
        double t = 0;
        t = dt * i;
        double x = moveX(t, c.angle);
        if (x >= w.walloy2_x) {
            x = w.walloy2_x + (w.walloy2_x - x);
        }
        if (x < 0) {
            x = -x;
        }
        
        return x;
    }

    public double getY(int i, double dr0, WallsAndRoad w, Cannon c) {
        double t = 0;
        t = dt * i;
        double y = moveY(t, c.angle);
        if (y > w.road_y) {
            y = w.road_y;
        }
        if (y < 0) {
            y = -y;
        }

        return y;
    }

    public double getX0(double alpha) {
        double x0;
        switch ((int) alpha) {
            case 0:
                x0 = 250;
                break;
            case 15:
                x0 = 225;
                break;
            case 30:
                x0 = 210;
                break;
            case 45:
                x0 = 208;
                break;
            default:
                x0 = 0;
                break;
        }
        return x0;
    }

    public double getY0(double alpha) {
        double y0;
        switch ((int) alpha) {
            case 0:
                y0 = 480;
                break;
            case 15:
                y0 = 460;
                break;
            case 30:
                y0 = 430;
                break;
            case 45:
                y0 = 388;
                break;
            default:
                y0 = 0;
                break;
        }
        return y0;
    }


}
