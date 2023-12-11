
package snakegame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author hp
 */
public class Board extends JPanel implements ActionListener{
    private Image apple;
    private Image dot;
    private Image head;
    private int dots;
    
    private Timer timer;
    
    private boolean leftdirection=false;
    private boolean rightdirection=true;
    private boolean updirection=false;
    private boolean downdirection=false;
    
    private final int All_Dots=900;
    private final int  Dot_Size=10;
    private final int Random_Position=29;
    private int apple_x;
    private int apple_y;
    
    private final int x[]=new int[All_Dots];
    private final int y[]=new int[All_Dots];
    
    private boolean InGame=true;
    
    Board(){
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(300,300));
        loadImage();
        
        initGame();
    }
    public void loadImage(){
        ImageIcon I1=new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/apple.png"));
        apple=I1.getImage();
        
        ImageIcon I2=new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/dot.png"));
        dot=I2.getImage();
        
        ImageIcon I3=new ImageIcon(ClassLoader.getSystemResource("snakegame/icons/head.png"));
        head=I3.getImage();
    }
    public  void initGame(){
        dots=3;
        for(int i=0;i<dots;i++){
            y[i]=50;
            x[i]=50-i*Dot_Size;
            
        }
        locateApple();
        timer=new Timer(140,this);
        timer.start();
           
    }
    
    public void locateApple(){
             int r=(int)(Math.random() * Random_Position);
            apple_x=r * Dot_Size;
            r=(int)(Math.random()*Random_Position);
            apple_y=r * Dot_Size;
            
        }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
       if(InGame){
            g.drawImage(apple, apple_x, apple_y, this);
        for (int i=0;i <dots;i++){
            if(i==0){
                g.drawImage(head,x[i],y[i], this);
            }else{
                g.drawImage(dot,x[i],y[i], this);
            }
        }
        Toolkit.getDefaultToolkit().sync();
       }else{
           gameOver(g);
       }
    }
    public void gameOver(Graphics g){
    String msg = "Game Over";
    Font font = new Font("SansSerif", Font.BOLD, 14); // Fixed font name typo
    FontMetrics metrics = getFontMetrics(font); // Fixed getFontMetrics typo
    g.setFont(font); // Set the font for drawing the string
    g.setColor(Color.white);
    g.setFont(font);
    g.drawString(msg, (300 - metrics.stringWidth(msg)) / 2, 300 / 2);
}

    public void move(){
        for(int i=dots;i > 0;i--){
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        if(leftdirection){
            x[0]=x[0]-Dot_Size;
        }
        if(rightdirection){
            x[0]=x[0]+Dot_Size;
        }
        if(updirection){
            y[0]=y[0]-Dot_Size;
        }
        if(downdirection){
            y[0]=y[0]+Dot_Size;
        }
        
    }
    public void checkapple(){
        if((x[0]==apple_x) && y[0] ==apple_y){
            dots++;
            locateApple();
            
        }
    }
    public void checkCollison(){
        for(int i=dots; i>0;i--){
            if((i > 4) && (x[0] == x[i]) && (y[0]== y[i])){
                InGame=false;
            }
        }
        if(x[0] >=300){
            InGame=false;
        }
        if(y[0] >=300){
            InGame=false;
        }
        if(y[0] < 0){
            InGame=false;
        }
        if(y[0] < 0){
            InGame=false;
        }
        if(!InGame){
            timer.stop();
        }
    }
    public void actionPerformed(ActionEvent e){
        if(InGame){
            
        
            move();
            checkapple();
            checkCollison();
            
        }
        repaint();
    }
    public class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int key=e.getKeyCode();
            if(key==KeyEvent.VK_LEFT && (!rightdirection)){
                leftdirection=true;
                updirection=false;
                downdirection=false;
            }
            if(key==KeyEvent.VK_RIGHT && (!leftdirection)){
                rightdirection=true;
                updirection=false;
                downdirection=false;
            }
            if(key==KeyEvent.VK_DOWN && (!updirection)){
                downdirection=true;
                leftdirection=false;
                rightdirection=false;
            }
            if(key==KeyEvent.VK_UP && (!downdirection)){
                updirection=true;
                leftdirection=false;
                rightdirection=false;
            }
        }
        
    }
}
