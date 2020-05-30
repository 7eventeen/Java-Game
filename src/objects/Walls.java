/*
    3 walls and 1 invisible road, if the ball will collision with a wall, his velocity will equals by momentum conservation
                                                    slaw
 */

package objects;

import javax.swing.ImageIcon;

public class Walls {

    public final ImageIcon wallox = new ImageIcon("src/res/wallx.png");
    public final ImageIcon walloy = new ImageIcon("src/res/wally.png");

    public final int wallox_x = 0;
    public final int wallox_y = 0;
    public final int wallox_width = 1280;
    public final int wallox_height = 20;

    public final int walloy1_x = 0;
    public final int walloy1_y = 0;
    public final int walloy1_width = 13;
    public final int walloy1_height = 575;

    public final int walloy2_x = 1265;
    public final int walloy2_y = 0;
    public final int walloy2_width = 13;
    public final int walloy2_height = 570;


}
