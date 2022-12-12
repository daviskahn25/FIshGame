import java.awt.*;

public class FishFood {


    public String name;               //name of the hero
    public int xpos;                  //the x position
    public int ypos;                  //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;                 //the width of the hero image
    public int height;                //the height of the hero image
    public boolean isAlive;//a boolean to denote if the hero is alive or dead
    public Rectangle rec;
    public boolean isCrashing = false;
    public boolean isEaten = false;


    public FishFood(String pName, int pXpos, int pYpos) { // Astronaut constructor
        name = pName;
        xpos = pXpos;
        //xpos = 100;
        xpos = (int)(Math.random()*800);
        //ypos = pYpos;
        //ypos = 100;
        ypos = (int)(Math.random()*600);
        dx = 2;
        dy = 2;
        width = 60;
        height = 60;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);

    } // end Astronaut constructor
    public void move() { // move
        xpos = xpos + dx;
        ypos = ypos + dy;

    } // end move


}
