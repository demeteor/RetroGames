import java.awt.*;

public class Ball {

    double xVel, yVel, x, y;
//a simple creation of our oval object
    public Ball(){
        //setting its poss
        x =350;
        y =250;
        //with xVel you can change the initial direction of x axis ( -1 left +1 right)
        xVel = -2;
        //with yVel you can change the initial direction of y axis  ( -1 up +1 down)
        yVel =1;
    }
    public void draw(Graphics graphics){
        //drawing an oval.
        graphics.setColor(Color.red);
        graphics.fillOval((int)x-10,(int)y-10,20,20);
    }

    public void checkColl(Paddle p1, Paddle p2) {
        //setting collision control
        //checking if the edges of each player actually meet with our oval object
        if(x <= 50) {
            if(y>= p1.getY() && y<=p1.getY()+80)
                xVel = -xVel;
        }
        else if(x>=650) {
            if(y>= p2.getY() && y<=p2.getY()+80)
                xVel = -xVel;
        }
    }
    public void move(){
        x+= xVel;
        y+= yVel;
        //reverses the direction of the oval if it hits a up/down border or a puddle
        if(y<10)
            yVel =-yVel;
        if(y>490)
            yVel =-yVel;

    }
    public int getX(){
        return (int)x;
    }
    public int getY() {
        return (int)y;
    }

}