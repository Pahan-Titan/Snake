package ua.pt.snake;

/************************************************************
 ** Title:  Игра змейка 
 ** Class:  SnakeApp
 ** Notes: Класс окна игры змейка
 ************************************************************/

//import game.highscores.HighscoreManager;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class SnakeApp {

    public static void main(String[] args) {
        new SnakeApp().startGraphicInterface();
    }

    public void startGraphicInterface() {
        JFrame myWindow = new JFrame("Змейка меню");
        myWindow.setLayout(null);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setSize(270, 240);
        myWindow.setVisible(true);

        final JButton GO = new JButton("Старт игры");
        GO.setLocation(30, 30);
        GO.setSize(200, 40);
        myWindow.add(GO);

        GO.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                startGame();
            }
        });

        final JButton highscore = new JButton("История");
        highscore.setLocation(30, 80);
        highscore.setSize(200, 40);
        myWindow.add(highscore);
        myWindow.setResizable(false);

        highscore.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
//                game.highscores.HighscoreManager hm = new game.highscores.HighscoreManager();
//                JOptionPane.showMessageDialog(highscore, hm.getHighscoreString(), "История", JOptionPane.WARNING_MESSAGE);
            }
        });

        final JButton exit = new JButton("Выход");
        exit.setLocation(30, 130);
        exit.setSize(200, 40);
        myWindow.add(exit);

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	System.exit(0);
            }
        });
    }

    public void startGame() {
        JDialog dlg = new JDialog((JFrame) null, "Змейка");
        dlg.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        final SnakeGame sn = new SnakeGame();
        dlg.getContentPane().add(sn);
        sn.newGame();
        dlg.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent ev) {
                sn.processKey(ev);
            }
        });
        dlg.setVisible(true);
        dlg.pack();
        dlg.setResizable(true);
        dlg.setLocation(300, 200);
        dlg.addWindowListener(new WindowListener() {

            public void windowActivated(WindowEvent event) {
            }

            public void windowClosed(WindowEvent event) {
            }

            public void windowClosing(WindowEvent event) {
                Object[] options = {"Да", "Нет!"};
                int n = JOptionPane.showOptionDialog(event.getWindow(), "Сохранить результат?",
                        "Змейка", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);
                if (n == 0) {
                    Object[] options1 = {"Да", "Нет!"};
                    String name = "Аноним";
                    JOptionPane k = new JOptionPane();
                    JDialog dialog = k.createDialog(null, "История");

                   name = k.showInputDialog(null, "Ваше имя", "История", 1);
//                   HighscoreManager hm = new HighscoreManager();
                    if (name != null) {
                        name = name + " ";
//                        hm.addScore(name, sn.getPoints()); 
                    }
                    event.getWindow().dispose();

                } else {
                    event.getWindow().dispose();
                }
            }

            public void windowDeactivated(WindowEvent event) {
            }

            public void windowDeiconified(WindowEvent event) {
            }

            public void windowIconified(WindowEvent event) {
            }

            public void windowOpened(WindowEvent event) {
            }
        });
    }
}