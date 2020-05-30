package objects;

import javax.swing.*;
import java.awt.*;

public class Basket {

    public JLabel initBasket(JLabel background) {
        JLabel basket = new JLabel() {
            Graphics2D g2;

            public void paint(Graphics g) {
                super.paint(g);
                g2 = (Graphics2D)g;
                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(10.0f));
                g2.drawRect(0, 0, 135, 100);
            }
        };
        basket.setBounds(800, 454, 136, 100);
        return basket;
    }

}
