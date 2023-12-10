import javax.swing.JFrame;

import Brickbeaker.GamePlay;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        GamePlay gamePlay=new GamePlay();
        JFrame obj=new JFrame();
        obj.setBounds(10,10,700,600);
        obj.setTitle("Brick beakers");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
        
    }
}