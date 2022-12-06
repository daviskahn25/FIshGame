import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

    //Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.io.FileReader;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

    public class BasicGameApp implements Runnable {

        //Variable Definition Section
        //Declare the variables used in the program
        //You can set their initial values too

        //Sets the width and height of the program window
        final int WIDTH = 1000;
        final int HEIGHT = 700;

        //Declare the variables needed for the graphics
        public JFrame frame;
        public Canvas canvas;
        public JPanel panel;

        public BufferStrategy bufferStrategy;

        public Image Fish1Pic;
        public Image Fish2Pic;
        public Image backgroundPic;
        public Image fishfoodPic;

        //Declare the objects used in the program
        //These are things that are made up of more than one variable type
        private Fish Fish1;
        public Fish Fish2;
        public Fish fishFood;

        // Main method definition
        // This is the code that runs first and automatically
        public static void main(String[] args) {
            BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
            new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
        }


        // This section is the setup portion of the program
        // Initialize your variables and construct your program objects here.
        public BasicGameApp() { // BasicGameApp constructor

            setUpGraphics();
            backgroundPic = Toolkit.getDefaultToolkit().getImage("water1.png");
            fishfoodPic = Toolkit.getDefaultToolkit().getImage("fishfood.png");
            //fishFood = new Fish("fish food", Math.random()100-400, Math.random()100-400);
                //fishfoodPic = Math.random(int)(Math.random()*400+100);
            //variable and objects
            //create (construct) the objects needed for the game and load up
            Fish1Pic = Toolkit.getDefaultToolkit().getImage("fish1.png"); //load the picture
            Fish1 = new Fish("Fish1",10,100); //construct the astronaut

            Fish2Pic = Toolkit.getDefaultToolkit().getImage("fish2.png");
            Fish2 = new Fish("Fish2", 800, 400);
            Fish2.dx= Fish2.dx*-1;
            Fish2.dy= Fish2.dy*-1;



//            backgroundPic = Toolkit.getDefaultToolkit().getImage("water.png");

        } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

        // main thread
        // this is the code that plays the game after you set things up
        public void run() {

            //for the moment we will loop things forever.
            while (true) {
                moveThings();  //move all the game objects
                crash();
                render();  // paint the graphics
                pause(20); // sleep for 10 ms
            }
        }

        public void moveThings() {
            //calls the move( ) code in the objects
            Fish1.bounce();
            Fish2.bounce();

        }

        public void crash(){
            //if astro and alien interset they bounce

            if (Fish1.rec.intersects(Fish2.rec)&& Fish1.isCrashing == false){
                System.out.println("CRASH");
                Fish1.isCrashing = true;
                Fish1.dx = -Fish1.dx;
                Fish2.dx = -Fish2.dx;
                Fish1.dy = -Fish1.dy;
                Fish2.dy = -Fish2.dy;
                Fish1.width=Fish1.width+80;
                Fish1.height=Fish1.height+80;
                Fish2.width=Fish2.width-10;
                Fish2.height=Fish2.height-10;
//			astro.isAlive = false;
            }
            if (!Fish1.rec.intersects(Fish2.rec)){
                Fish1.isCrashing = false;

            }

        }

        //Pauses or sleeps the computer for the amount specified in milliseconds
        public void pause(int time ) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
            }
        }

        //Graphics setup method
        private void setUpGraphics() {
            frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

            panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
            panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
            panel.setLayout(null);   //set the layout

            // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
            // and trap input events (Mouse and Keyboard events)
            canvas = new Canvas();
            canvas.setBounds(0, 0, WIDTH, HEIGHT);
            canvas.setIgnoreRepaint(true);

            panel.add(canvas);  // adds the canvas to the panel.

            // frame operations
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
            frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
            frame.setResizable(false);   //makes it so the frame cannot be resized
            frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

            // sets up things so the screen displays images nicely.
            canvas.createBufferStrategy(2);
            bufferStrategy = canvas.getBufferStrategy();
            canvas.requestFocus();
            System.out.println("DONE graphic setup");
        }

        //Paints things on the screen using bufferStrategy
        private void render() {
            Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
            g.clearRect(0, 0, WIDTH, HEIGHT);
            g.drawImage(backgroundPic, 0,0, WIDTH, HEIGHT, null);

            g.drawRect(Fish1.rec.x, Fish1.rec.y, Fish1.rec.width, Fish1.rec.height);
            //draw the image of the astronaut
            if (Fish1.isAlive==true) {
                if(Fish1.dx>=0) {
                    g.drawImage(Fish1Pic, Fish1.xpos, Fish1.ypos, Fish1.width, Fish1.height, null);
                }else {
                    g.drawImage(Fish1Pic, Fish1.xpos, Fish1.ypos, Fish1.width, Fish1.height, null);

                }
               // g.drawRect(Fish1.rec.x, Fish1.rec.y, Fish1.rec.width, Fish1.rec.height);
            }

            g.setColor(Color.YELLOW);
            g.drawRect(Fish2.rec.x, Fish2.rec.y, Fish2.rec.width, Fish2.rec.height);

            g.drawImage(Fish2Pic, Fish2.xpos, Fish2.ypos, Fish2.width, Fish2.height, null);

          //  g.drawRect(Fish2.rec.x, Fish2.rec.y, Fish2.rec.width, Fish2.rec.height);





            g.dispose();
            bufferStrategy.show();
        }
    }




