package objects;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class ProgressBarOfStartVelocity {

    public final int MAX_SPEED = 200;
    public final int VEL_STEP = 20;
    JProgressBar progressBar = new JProgressBar();

    public void showOnTheBackground(JFrame root, JLabel background, Ball b) {
        progressBar.setMaximum(MAX_SPEED);
        progressBar.setMinimum(0);
        progressBar.setValue((int) b.dr0);
        progressBar.setBounds(150, 600, 300, 25);
        progressBar.setStringPainted(true);
        progressBar.setString("Start Velocity");
        background.add(progressBar);
        root.repaint();
    }

}
