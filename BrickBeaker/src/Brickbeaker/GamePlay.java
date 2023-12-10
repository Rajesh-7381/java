package Brickbeaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false; // Flag to indicate if the game is active
    private int score = 0; // Player's score
    private int totalBricks = 21; // Total number of bricks in the game (initially 7*3)
    private Timer timer; // Timer for game events
    private int delay = 8; // Delay for timer (controls game speed)

    private int playerX = 310; // Initial x-coordinate of the player's paddle
    private int ballPositionX = 120; // Initial x-coordinate of the ball
    private int ballPositionY = 350; // Initial y-coordinate of the ball

    private int ballXdir = -1; // Ball's horizontal movement direction
    private int ballYdir = -2; // Ball's vertical movement direction

    private MapGenerator map; // Instance of MapGenerator to create and draw the game map
    
    public GamePlay(){
        // Initializing the game
        map = new MapGenerator(3, 7); // Create a map with 3 rows and 7 columns of bricks
        addKeyListener(this); // Registering the KeyListener for user input
        setFocusable(true); // Setting the panel focusable to receive key events
        setFocusTraversalKeysEnabled(false); // Disabling focus traversal keys
        timer = new Timer(delay, this); // Creating a timer for game actions
        timer.start(); // Starting the timer
    }

    // Method responsible for painting components on the panel
    public void paint(Graphics g){
        super.paint(g); // Calling the superclass's paint method
        // Drawing the game components

        // Drawing the background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        // Drawing the game map using the MapGenerator's draw method
        map.draw((Graphics2D)g);

        // Drawing the borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(0, 0, 3, 3);
        g.fillRect(691, 0, 3, 592);

        // score
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score,590,30);

        // Drawing the player's paddle
        g.setColor(Color.green);
        g.fillRect(playerX, 550, 100, 8);

        // Drawing the ball
        g.setColor(Color.yellow);
        g.fillOval(ballPositionX, ballPositionY, 20, 20);
        // detect finish our game
        if(totalBricks <= 0){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You Won:",260,300);

            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("press enter to restart:",230,350);
        }

        if(ballPositionY > 570){
            play=false;
            ballXdir=0;
            ballYdir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Gameover,Your score:"+score,230,300);

            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("press enter to restart:",230,350);
        }
        g.dispose(); // Disposing the graphics context to free resources
    }

    // Method called by the timer for game actions
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start(); // Start the timer

        if (play) { // If the game is active
            // Collision detection for the ball and the paddle
            if (new Rectangle(ballPositionX, ballPositionY, 20, 20)
                    .intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir; // Change the ball's vertical direction
            }
            // here map.map we acess map generator file
            // here 1st map is object and 2nd map is 2d map object
            for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j] > 0){
                        int brickX=j*map.brickWidth+80;
                        int brickY=i*map.brickHeight+50;
                        int brickWidth=map.brickWidth;
                        int brickHeight=map.brickHeight;
                        Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballRect=new Rectangle(ballPositionX,ballPositionY,20,20);
                        Rectangle brickRect=rect;
                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score+=5;
                            if(ballPositionX + 19 <=brickRect.x || ballPositionX +1 >=brickRect.x +brickRect.width){
                                ballXdir=-ballXdir;
                            }else{
                                ballYdir=-ballYdir;
                            }
                        }
                    }
                }
            }
            // Ball's movement
            ballPositionX += ballXdir;
            ballPositionY += ballYdir;

            // Boundary checks for the ball's position
            if (ballPositionX < 0 || ballPositionX > 670) {
                ballXdir = -ballXdir; // Reverse the ball's horizontal direction
            }
            if (ballPositionY < 0) {
                ballYdir = -ballYdir; // Reverse the ball's vertical direction
            }
            if (ballPositionY > 570) {
                ballYdir = -ballYdir; // Reverse the ball's vertical direction
            }
        }
        repaint(); // Repaint the panel to update the game components
    }

    // Methods to handle keyboard input
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                MoveRight(); // Move the player's paddle to the right
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                MoveLeft(); // Move the player's paddle to the left
            }
        }
         // for restarting game
    if(e.getKeyCode()==KeyEvent.VK_ENTER){
        if(!play){
            play=true;
            ballPositionX=120;
            ballPositionY=350;
            ballXdir=-1;
            ballYdir=-2;
            playerX=310;
            score=0;
            totalBricks=21;
            map=new MapGenerator(3,7 );
            repaint();
        }
    }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    // Methods to handle the player's paddle movement
    public void MoveRight() {
        play = true; // Start the game
        playerX += 20; // Move the player's paddle to the right by incrementing x-coordinate
    }

    public void MoveLeft() {
        play = true; // Start the game
        playerX -= 20; // Move the player's paddle to the left by decrementing x-coordinate
    }
   
}
