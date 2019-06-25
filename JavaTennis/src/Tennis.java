import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.Thread.*;

public class Tennis extends Applet implements Runnable, KeyListener {
    //declaring all variables
    final int WIDTH = 700, HEIGHT = 500;
    Thread thread;
    player p1;
    AI p2;
    Ball ball;
    boolean setGame;
    Graphics gfx;
    Image img;
    public void init() {
        //setting applets size
        this.resize(WIDTH,HEIGHT);
       //puasing the game.
        setGame=false;
        //enabling keylistener
        this.addKeyListener(this);
        //adding our players and oval ball.
        p1 = new player(1);
        ball = new Ball();
        p2 = new AI(2,ball);
        //creatign a secondary offscreen image to prevent blinking
        img = createImage(WIDTH,HEIGHT);
        gfx = img.getGraphics();
        //starting our thread
        thread = new Thread(this);
        thread.start();
     }
    public void paint(Graphics graphics) {
        //desplaying the offscreen iamges that where created earlier
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0,0,WIDTH,HEIGHT);
        //if the ball leaves to the left or the right desplay game over *you will always loose the game is unbeatable)
        if(ball.getX() < -10 || ball.getX() > 710 ) {
            gfx.setColor(Color.white);
            gfx.drawString("Game Over",350,250);
        }
        else {
            //as long as the ball did not corss the above  parameters keep on drawing our offscreen images
            p1.draw(gfx);
            ball.draw(gfx);
            p2.draw(gfx);
        }
        //this will be our start screen
        if(!setGame) {
            gfx.setColor(Color.white);
            gfx.drawString("Press Enter to Start..",310,310);
        }
        graphics.drawImage(img,0,0,this);
    }
    public void update(Graphics graphics) {

        paint(graphics);
    }

    public void run() {
        //endless loop to take care of our runtime
        while (true){
            if(setGame) {
                //all of those are parts of different classes
                //you can see their codes in their respected classes
                p1.move();
                ball.move();
                p2.move();
                ball.checkColl(p1, p2);
            }
            repaint();
            try {//sleep for 10ms and repeated process unless you get an error
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
//unused function
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
//when a user pressed a button do the following
    public void keyPressed(KeyEvent keyEvent) {
        //up
     if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
        p1.setUpAccel(true);
    }
     //down
     else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
         p1.setDownAccel(true);
        }
     //pressign enter starts the game by setting setGame to true/ see code above
     else if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
         setGame = true;
     }
    }
    //on releasing the button will stop the movement
    public void keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
            //false move for UP
    p1.setUpAccel(false);
        }
        else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
            //false move for Down
    p1.setDownAccel(false);
        }
    }
}
