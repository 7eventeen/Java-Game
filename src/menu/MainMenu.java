package menu;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.io.File;


public class MainMenu {

    private JFrame root;
    private Canvas canvas;

    private String title;

    private int width;
    private int height;


    private File is = new File("src/res/palamecia titling.ttf");

    public MainMenu(String title, int width, int height)  {

        this.title = title;
        this.width = width;
        this.height = height;


    }

    public void initUi() {

        root = new JFrame(this.title);
        root.setSize(this.width, this.height);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setLocationRelativeTo(null);
        root.setResizable(false);
        root.setVisible(true);
        root.setFocusable(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(this.width, this.height));
        canvas.setMaximumSize(new Dimension(this.width, this.height));
        canvas.setMinimumSize(new Dimension(this.width, this.height));

        Ui ui = new Ui(this.width, this.height, root);
        ui.startpanel(root);
        // root.getContentPane().add(panel);

        root.setVisible(true);

        root.add(canvas);
        root.pack();

    }






}
