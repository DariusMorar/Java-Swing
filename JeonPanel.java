import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class creates a panel object that shows randomly moving metaballs. 
 * @author Yeongbae Jeon
 * @version 2022.06.01
 */
public class JeonPanel extends JPanel implements ActionListener{

    private static final int UPDATE_TIME = 1000 / 60; // unit: ms
    private static final int BALL_NUM = 7;
    private static final int BALL_MAX_VELOCITY = 3;   // unit: pixel;
    private static final int BALL_MAX_RADIUS = 25;    // unit: pixel

    private Timer timer;

    private BufferedImage canvas = null;
    private CollisionManager manager = null;
    private ArrayList<Blob> balls = null;

    public JeonPanel() {
        this.canvas = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);      // framebuffer
        // Manage interaction between world borders and balls
        this.manager = new CollisionManager(this.getWidth(), this.getHeight());  
        
        /* Generate metaballs in random locations at random speeds */
        
        this.balls = new ArrayList<Blob>();
        for (int i = 0; i < BALL_NUM; i++) {
            int x = ThreadLocalRandom.current().nextInt(0, 200);
            int y = ThreadLocalRandom.current().nextInt(0, 200);
            int radius = ThreadLocalRandom.current().nextInt(1, BALL_MAX_RADIUS);
            int xv = ThreadLocalRandom.current().nextInt(1, BALL_MAX_VELOCITY);
            int yv = ThreadLocalRandom.current().nextInt(1, BALL_MAX_VELOCITY);
            Blob b = new Blob(x, y, radius, xv, yv);
            this.balls.add(b);
        }
    
       
        /* Start rendering */
        
        this.timer = new Timer(UPDATE_TIME, this);
        this.timer.start();
    }

    /**
     * Paint elements in the panel. 
     * I used this method like a game engine rendering function.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // When the user resizes the window, allocate a new framebuffer with the changed size.
        // note: check if there is event listener for window resize
        if (this.canvas.getWidth() != this.getWidth() || this.canvas.getHeight() != this.getHeight()) {
            this.canvas = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
            this.manager.update(this.getWidth(), this.getHeight());
        }
        else
            this.canvas.getGraphics().clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());


        for (Blob b: this.balls)
            b.draw(this.canvas);

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }

    /**
     * This function is called whenever a certain amount of time has passed.
     * The data of all objects in the world are updated.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Blob b : this.balls) {
            b.update();
            // Bounce the ball if there is a collision between a ball
            // and the world boundary.
            this.manager.collision_update(b);
        }
        this.repaint();
    }
}


/**
 * This class manages collisions between the world boundaries and balls.
 * but it does not care about the collsiions between the balls.
 */
class CollisionManager {
    private int worldWidth;
    private int worldHeight;

    /**
     * @param worldWidth width of the world
     * @param worldHeight height of the world
     */
    CollisionManager(int worldWidth, int worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    /**
     * Update the width and height of the world.
     * This method is used when the user resizes the window.
     *
     * @param worldWith width of the world
     * @param worldHeight width of the world
     */
    public void update(int worldWidth, int worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
    }

    /**
     * Bounce the ball (change velocity) when the center of the ball touches the border of the world. 
     *
     * @param ball Blob object to check collision
     */
    public void collision_update(Blob ball) {
        if (ball.y < 0)
            ball.yVelocity = Math.abs(ball.yVelocity);
        else if (ball.y > this.worldHeight)
            ball.yVelocity = -1 * Math.abs(ball.yVelocity);
        
        if (ball.x < 0)
            ball.xVelocity = Math.abs(ball.xVelocity);
        else if (ball.x > this.worldWidth)
            ball.xVelocity = -1 * Math.abs(ball.xVelocity);
    }
}


/**
 * Ball class
 */
class Blob {
     // solar corona intensity 
    private static final int INTENSITY = 1500;

    // location in the world
    public int x;
    public int y;
    
    public int radius;
    public int xVelocity;
    public int yVelocity;


    /**
     * @param x x-axis position
     * @param y y-axis position
     * @param radius radius
     * @param xVelocity horizontal velocity component
     * @param yVelocity vertical velocity component
     */
    Blob(int x, int y, int radius, int xVelocity, int yVelocity) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    /**
     * Update the current position of the ball.
     */
    public void update() {
        this.x += this.xVelocity;
        this.y += this.yVelocity;
    }

    /**
     * Draw the ball and white corona around the ball on the framebuffer.
     * This method should only be called in render method.
     *
     * @param canvas framebuffer to draw the ball
     */
    public void draw(BufferedImage canvas) {
        
        // Every pixel
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
             
                // The minimum must be 1, otherwise "1/d" in the line below will cause a problem.
                double d = Math.max(dist(x, y, this.x, this.y) - radius, 1);
                
                // Clamp intensity to the range [0, 0xFF].
                // Java Color class allows values above 0xFF and it
                // causes unexpected bug.
                int intensity = (int) Math.min(1/d * INTENSITY, 0xFF);
                
                Color c1 = new Color(0xAA, intensity, 0xAA);
                Color c2 = new Color(canvas.getRGB(x, y));

                Color mixed = mixColor(c1, c2);
                canvas.setRGB(x, y, mixed.getRGB());
            }
        }
    }

    /**
     * Get distance between two points
     *
     * @param x1 x position of point one
     * @param y1 y posiiton of point one
     * @param x2 x position of point two
     * @param y2 y position of point two
     */
    public static double dist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    }

    /**
     * Mix two RGB colors, and the maximum color is always white.  
     * @param c1: Color object one
     * @param c2: Color object two
     */
    public static Color mixColor(Color c1, Color c2) {
        int red = (int) Math.min(c1.getRed() + c2.getRed(), 0xFF);
        int green = (int) Math.min(c1.getGreen() + c2.getGreen(), 0xFF);
        int blue = (int) Math.min(c1.getBlue() + c2.getBlue(), 0xFF);
        return new Color(red, green, blue);
    }
}