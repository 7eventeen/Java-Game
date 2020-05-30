package menu;

import gameloop.GameLoop;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;


public class Ui {

    private GameLoop newgame;

    private JFrame root;
    private JPanel startpanel = new JPanel();


    private JButton buttontostart = new JButton("START");
    private JButton buttontoexit = new JButton("EXIT");

    private int width;
    private int height;

    private Font mainfont = new Font("Dialog", Font.BOLD, 70);


    public Ui(int width, int height, JFrame root) {

        this.width = width;
        this.height = height;

            // Init GameLoop by clicking this
        buttontostart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newgame = new GameLoop(width, height, root);

            }
        });


            // Exit from the program by clicking this
        buttontoexit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                root.dispose();

            }
        });

    }

    public void startpanel(JFrame root) {

        startpanel.setBackground(Color.DARK_GRAY);
        startpanel.setLayout(null);
        startpanel.setBounds(0, 0, this.width, this.height);
        startpanel.add(buttontostartoptions());
        startpanel.add(buttontoexitoptions());


        root.add(startpanel);

    }


    private JButton buttontostartoptions() {

        buttontostart.setFont(mainfont);
        buttontostart.setForeground(Color.DARK_GRAY);
        buttontostart.setBackground(Color.lightGray);
        buttontostart.setFocusPainted(false);
        buttontostart.setBounds((int) (this.width / 2.7), (int) (this.height / 3.2), 350, 100);

        return buttontostart;

    }

    private JButton buttontoexitoptions() {

        buttontoexit.setFont(mainfont);
        buttontoexit.setForeground(Color.DARK_GRAY);
        buttontoexit.setBackground(Color.lightGray);
        buttontoexit.setFocusPainted(false);
        buttontoexit.setBounds((int) (this.width / 2.7), (int) (this.height / 2), 350, 100);

        return buttontoexit;

    }


}
