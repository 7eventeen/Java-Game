package objects;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Cannon {

    public final int x = 40;
    public final int y = 365;

    public final int width = 250;
    public final int height = 250;

    public final ImageIcon cannonimg0 = new ImageIcon("src/res/cannonforms/cannon0.png");
    public final ImageIcon cannonimg15 = new ImageIcon("src/res/cannonforms/cannon15.png");
    public final ImageIcon cannonimg30 = new ImageIcon("src/res/cannonforms/cannon30.png");
    public final ImageIcon cannonimg45 = new ImageIcon("src/res/cannonforms/cannon45.png");

    public int angle = 0;

    public void updatecannon(JFrame root, JLabel label_cannon, Cannon c) {
        // Update image of the cannon on a screen by pressed key RIGHT or LEFT
        switch (c.angle) {
            case 0:
                label_cannon.setIcon(c.cannonimg0);
                root.repaint();
                break;
            case 15:
                label_cannon.setIcon(c.cannonimg15);
                root.repaint();
                break;
            case 30:
                label_cannon.setIcon(c.cannonimg30);
                root.repaint();
                break;
            case 45:
                label_cannon.setIcon(c.cannonimg45);
                root.repaint();
                break;
        }

    }



}

