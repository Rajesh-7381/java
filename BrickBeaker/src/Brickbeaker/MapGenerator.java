package Brickbeaker;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
    public int[][] map; // 2D array to represent the game map
    public int brickWidth; // Width of each brick
    public int brickHeight; // Height of each brick
    
    // Constructor to initialize the map and brick dimensions
    public MapGenerator(int row, int col) {
        map = new int[row][col]; // Initializing the map with specified rows and columns
        
        // Filling the map with bricks (initially set to 1)
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
        
        // Calculating brick width and height based on map dimensions
        brickWidth = 540 / col;
        brickHeight = 150 / row;
    }
    
    // Method to draw the map with bricks
    public void draw(Graphics2D g) {
        // Iterating through the map and drawing bricks where value is greater than 0
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    // Setting the brick color and filling a rectangle for each brick
                    g.setColor(Color.white);
                    g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    
                    // Adding a border around each brick
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }
    
    // Method to set the value of a brick at a specific position
    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}
