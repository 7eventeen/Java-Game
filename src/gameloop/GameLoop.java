/*
    Main game loop, which shows by clicking to button "start"
 */

package gameloop;

import com.sun.jdi.IntegerValue;

import objects.Background;
import objects.Ball;
import objects.Cannon;
import objects.ProgressBarOfStartVelocity;
import objects.Walls;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class GameLoop {

    private final long timeInterval = 5;

    private Thread calculating = new Thread();

    private final JLabel background = new JLabel();
    private final JLabel label_ball = new JLabel();
    private final JLabel label_cannon = new JLabel();
    private final JLabel label_wallox = new JLabel();
    private final JLabel label_walloy1 = new JLabel();
    private final JLabel label_walloy2 = new JLabel();

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
        Walls w = new Walls();
        loadGame(bgrd, b, c, w, root);

        addKeyListenerCannon(root, label_cannon, c);
        addKeyListenerStartVelocity(root, background, b);
        addKeyListenerRun(root, b);
    }

    private void loadGame(Background bgrd, Ball b, Cannon c, Walls w, JFrame root) {

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

        background.add(label_cannon);
        background.add(label_ball);
        background.add(label_wallox);
        background.add(label_walloy1);
        background.add(label_walloy2);

        root.repaint();

    }

    private void tick(List<ArrayList> coords, Ball b, JFrame root) {
        ArrayList<Double> X = coords.get(0);
        ArrayList<Double> Y = coords.get(1);
        if (i >= Y.size()) {
            return;
        }
        label_ball.setBounds( X.get(i).intValue(), Y.get(i).intValue(), b.ball_width, b.ball_height);
        root.repaint();
        i++;
    }

    private Runnable lambdaRun(Ball b, JFrame root) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    tick(b.getCoordinates(b.dr0), b, root);
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
                        b.dr0 += 1;
                        progressBar.showOnTheBackground(root, background, b);
                        break;
                    case KeyEvent.VK_S:
                        if (b.dr0 <= 1) {
                            break;
                        }
                        b.dr0 -= 1;
                        progressBar.showOnTheBackground(root, background, b);
                        break;
                }

            }
        });

    }

    private void addKeyListenerRun(JFrame root, Ball b) {
        // Init ball flying by pressed SPACE
        root.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_SPACE) {
                    Thread calculating = new Thread(lambdaRun(b, root));
                    calculating.start();
                }
            }
        });
    }

}



