/*
    Main game loop, which shows by clicking to button "start"
 */

package gameloop;

import com.sun.jdi.IntegerValue;

import objects.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



public class GameLoop {

    private final long timeInterval = 1;

    private Thread calculating = new Thread();

    private final JLabel background = new JLabel();
    private final JLabel label_ball = new JLabel();
    private final JLabel label_cannon = new JLabel();
    private final JLabel label_wallox = new JLabel();
    private final JLabel label_walloy1 = new JLabel();
    private final JLabel label_walloy2 = new JLabel();
    private final JLabel label_road = new JLabel();

    private final Basket basket = new Basket();

    ProgressBarOfStartVelocity progressBar = new ProgressBarOfStartVelocity();

    final int width;
    final int height;

    private int i = 0;

    public GameLoop(int width, int height, JFrame root) {
        this.width = width;
        this.height = height;


        root.getContentPane().removeAll();

        JPanel gamePanel = new JPanel();
        gamePanel.setBounds(0, 0, width, height);
        gamePanel.setBackground(Color.WHITE);

        background.setBounds(0, 0, width, height);

        background.add(label_ball);

        gamePanel.add(background);
        root.add(gamePanel);
        root.repaint();


        Background bgrd = new Background();
        Ball b = new Ball();
        Cannon c = new Cannon();
        WallsAndRoad w = new WallsAndRoad();
        loadGame(bgrd, b, c, w, root);

        addKeyListenerCannon(root, label_cannon, c);
        addKeyListenerStartVelocity(root, background, b);
        addKeyListenerRun(root, b, w, c);
    }

    private void loadGame(Background bgrd, Ball b, Cannon c, WallsAndRoad w, JFrame root) {

        background.setIcon(bgrd.backgroundimg);

        label_ball.setBounds(0, 0, b.ball_width, b.ball_height);
        label_ball.setIcon(b.ballimg);

        label_cannon.setBounds(c.x, c.y, c.width, c.height);
        label_cannon.setIcon(c.cannonimg0);

        label_wallox.setBounds(w.wallox_x, w.wallox_y, w.wallox_width, w.wallox_height);
        label_wallox.setIcon(w.wallox);

        label_walloy1.setBounds(w.walloy1_x, w.walloy1_y, w.walloy1_width, w.walloy1_height);
        label_walloy1.setIcon(w.walloy);

        label_walloy2.setBounds(w.walloy2_x, w.walloy2_y, w.walloy2_width, w.walloy2_height);
        label_walloy2.setIcon(w.walloy);

        label_road.setBounds(w.road_x, w.road_y, w.road_width, w.road_height);
        label_road.setIcon(w.wallox);

        background.add(label_cannon);
        background.add(label_ball);
        background.add(label_wallox);
        background.add(label_walloy1);
        background.add(label_walloy2);

        background.add(basket.initBasket(background));
        //background.remove(label_road);

        root.repaint();

    }

    private void tick(Ball b, JFrame root, WallsAndRoad w, Cannon c) {

        double x = b.getX(i, b.dr0, w, c);
        double y = b.getY(i, b.dr0, w, c);

        label_ball.setBounds((int) (x), (int) (y), b.ball_width, b.ball_height);
        root.repaint();

        i++;
    }

    private Runnable lambdaRun(Ball b, JFrame root, WallsAndRoad w, Cannon c) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    tick(b, root, w, c);
                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        return runnable;
    }

    /*private void run(Ball b, JFrame root) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tick(b.getCoordinates(b.dr0), b, root);
            }
        }, 0, 1000);
    } */



    private void addKeyListenerCannon(JFrame root, JLabel label_cannon, Cannon c) {
        // Change angle of the cannon image by pressed keys LEFT or RIGHT
        root.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        if (c.angle == 45) {
                            break;
                        }
                        else {
                            c.angle += 15;
                            c.updatecannon(root, label_cannon, c);
                            break;
                        }
                    case KeyEvent.VK_RIGHT:
                        if (c.angle == 0) {
                            break;
                        }
                        else {
                            c.angle -= 15;
                            c.updatecannon(root, label_cannon, c);
                            break;
                        }
                }
            }
        });

    }

    private void addKeyListenerStartVelocity(JFrame root, JLabel background, Ball b) {
        // Change the start velocity of a ball by pressed keys W or S
        root.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch(keyCode) {
                    case KeyEvent.VK_W:
                        if (b.dr0 >= progressBar.MAX_SPEED) {
                            break;
                        }
                        b.dr0 += progressBar.VEL_STEP;
                        progressBar.showOnTheBackground(root, background, b);
                        break;
                    case KeyEvent.VK_S:
                        if (b.dr0 <= progressBar.VEL_STEP) {
                            break;
                        }
                        b.dr0 -= progressBar.VEL_STEP;
                        progressBar.showOnTheBackground(root, background, b);
                        break;
                }

            }
        });

    }

    private void addKeyListenerRun(JFrame root, Ball b, WallsAndRoad w, Cannon c) {
        // Init ball flying by pressed SPACE
        root.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_SPACE) {
                    Thread calculating = new Thread(lambdaRun(b, root, w, c));
                    calculating.start();
                }
            }
        });
    }

}



