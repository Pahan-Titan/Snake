package ua.pt.snake;

import java.awt.HeadlessException;

/************************************************************
 ** Title:  ���� ������ 
 ** Class:  SnakeApp
 ** Notes: ����� ���� ���� ������
 ************************************************************/


import java.awt.event.*;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class SnakeApp {

	static String lvl;
	static int SPEED;
	
    public static void main(String[] args) {
        new SnakeApp().startGraphicInterface();
    }

    public void startGraphicInterface() {
        JFrame myWindow = new JFrame("������ ����");
        myWindow.setLayout(null);
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myWindow.setSize(270, 290);
        myWindow.setVisible(true);

        final JButton GO = new JButton("����� ����");
        GO.setLocation(30, 30);
        GO.setSize(200, 40);
        myWindow.add(GO);

        GO.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
            	SPEED = 410;
                startGame();
            }
        });

        final JButton highscore = new JButton("�������");
        highscore.setLocation(30, 80);
        highscore.setSize(200, 40);
        myWindow.add(highscore);
        myWindow.setResizable(false);

        highscore.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
					JOptionPane.showMessageDialog(highscore, HighscoreManager.read(), "�������", JOptionPane.WARNING_MESSAGE);
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
            }
        });

        final JButton Speed = new JButton("���������");
        Speed.setLocation(30, 130);
        Speed.setSize(200, 40);
        myWindow.add(Speed);

        Speed.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                JOptionPane s = new JOptionPane();
                JDialog dialog = s.createDialog(null, "���������");
                lvl = s.showInputDialog(null, "������� ��. �� 1 �� 5", "���������", 1);
                Lvl();
                startGame();
            }
        });
       
        final JButton exit = new JButton("�����");
        exit.setLocation(30, 180);
        exit.setSize(200, 40);
        myWindow.add(exit);

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	System.exit(0);
            }
        });
    }
    
    public static void Lvl() {
    	int l = Integer.parseInt(lvl);
    	if (l == 1) SPEED = 340;
    	if (l == 2) SPEED = 370;
    	if (l == 3) SPEED = 410;
    	if (l == 4) SPEED = 450;
    	if (l == 5) SPEED = 500;
	}
    
    public void startGame() {
        JDialog dlg = new JDialog((JFrame) null, "������");
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
                Object[] options = {"��", "���!"};
                int n = JOptionPane.showOptionDialog(event.getWindow(), "��������� ���������?",
                        "������", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, options,
                        options[0]);
                if (n == 0) {
                    Object[] options1 = {"��", "���!"};
                    String name = "������";
                    JOptionPane k = new JOptionPane();
                    JDialog dialog = k.createDialog(null, "�������");
                    name = k.showInputDialog(null, "���� ���", "�������", 1);
                    if (name != null) {
                        name = name + "  -  ";
                        try {
							HighscoreManager.update(name + sn.getPoints());
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
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