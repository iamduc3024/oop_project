package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.setSize(gamePanel.screenWidth, gamePanel.screenHeight);
        window.add(gamePanel);
        //window.pack();//causes this window to be sized to fit the preferred size and layout of its subcomponents
        System.out.print(window.getWidth() + " " + window.getHeight());
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}