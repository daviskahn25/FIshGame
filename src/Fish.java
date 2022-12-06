import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class Fish{

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
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


    //This is a constructor that takes 3 parameters.
    // This allows us to specify the hero's name and position when we build it.
    public Fish(String pName, int pXpos, int pYpos) { // Astronaut constructor
        name = pName;
        xpos = pXpos;
        //xpos = (int)(Math.random()*400+100);
        ypos = pYpos;
        //ypos = (int)(Math.random()*150+50);
        dx = 8;
        dy = 3;
        width = 60;
        height = 60;
        isAlive = true;
        //rec = new Rectangle(xpos, ypos, width, height);

    } // end Astronaut constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() { // move
        xpos = xpos + dx;
        ypos = ypos + dy;

    } // end move

    public void bounce(){
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos >= 1000-width ||xpos<=0  ){//right or left wall
            dx = -dx;}

        if (ypos<=0 || ypos>=700-height){//top or bottom wall
            dy = -dy;}

        rec = new Rectangle(xpos,ypos, width, height);
    }


    public void wrap (){
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos>=1000-width && dx>0) {//right wall
            xpos = -width;
        }

        if (xpos <= -width && dx<0){//left wall
            xpos = 1000;
        }

        if (ypos <= -height && dy<0){//top wall
            ypos = 700;
        }

        if (ypos>=600 && dy>0){//bottom wall
            ypos = -height;
        }
        rec = new Rectangle(xpos,ypos, width, height);

    }







}







