
package snakegame;
import javax.swing.*;
public class SnakeGame extends JFrame{

    /**
     * @param args the command line arguments
     */
    
    SnakeGame(){
         super("Snake Game");
         add(new Board());
         pack(); //refresh the frame
        setSize(300,300);
        setResizable(false);
        setLocationRelativeTo(null);
        
        
       
    }
    public static void main(String[] args) {
        // TODO code application logic here
        new SnakeGame().setVisible(true);
    }
    
}
