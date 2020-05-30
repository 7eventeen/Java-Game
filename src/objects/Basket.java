package objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.QuadCurve2D;

public class Basket {

    public final int basketx1 = 800;
    public final int basketx2 = 925;

    public final int baskety1 = 438;
    public final int baskety2 = 563;

    public JLabel initBasket(JLabel background) {
        JLabel basket = new JLabel() {
            Graphics2D g2;

            public void paint(Graphics g) {
                super.paint(g);
                g2 = (Graphics2D)g;
                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(7.0f));
                g2.drawLine(0, 0, 0, 125);
                g2.drawLine(0, 125, 125 ,125);
                g2.drawLine(125, 125, 125, 0);
            }
        };
        basket.setBounds(800, 438, 400, 200);
        return basket;
    }

}
