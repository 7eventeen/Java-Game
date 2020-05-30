package objects;

import javax.swing.JLabel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Basket {

    public final int x1 = 1105;
    public final int x2 = 1280;

    public final int x1k = 1140;
    public final int x2k = 1260;

    public final int y1 = 438;
    public final int y2 = 550;

    public JLabel initBasket(JLabel background) {
        JLabel basket = new JLabel() {
            Graphics2D g2;

            public void paint(Graphics g) {
                super.paint(g);
                g2 = (Graphics2D)g;
                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(15.0f));
                g2.drawLine(0, 0, 0, 125);
                g2.drawLine(0, 125, 174 ,125);
                g2.drawLine(175, 125, 174, 0);
            }
        };
        basket.setBounds(1105, 425, 400, 200);
        return basket;
    }

}
