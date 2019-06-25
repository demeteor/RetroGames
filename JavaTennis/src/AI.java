import java.awt.*;

//This will be the AI for the tenis game. in this case the AI will aways win , since as you can see it will always
//Makes sure that our Ball will hit in the midle of its Paddle.
public class AI implements Paddle {
    //the entire code is a copy of player.class. just simplified.
    double y,yVel;
    boolean upAccel,downAccel;
    int  x;

    Ball b1;
    public AI(int player,Ball b){
        //Up and down access are set to false
        upAccel=false;
        //declairing our ballfrom the object for later use
        b1 =b;
        downAccel=false;
        //speed of baddle and centering its possition
        y=210;yVel=0;
        //adding possitions
        if(player==1){
            x=20;
        }
        else {
            x=660;
        }
    }

    public void draw(Graphics graphics) {
        //drawing AI paddle
        graphics.setColor(Color.cyan);
        graphics.fillRect(x,(int)y,20,80);
    }
    public void move() {
//sets the movement  to aways makes sure that it will hit the ball
// this can be modified to make it "Random" to if its going to actually let you win or not
    y=b1.getY() - 40;
        if(y<0)
            y=0;
        else if(y>420) {
            y=420;
        }

    }


    public int getY()
    {
        return (int)y;
    }
}
