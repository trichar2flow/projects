import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

//*****************************************
//   Name: Troy Richardson
//   CSI 162-002
//   Lab 9
//*****************************************

public class HouseWatch extends JApplet
{
   private int currentX = 0; // Mouse cursor's X position
   private int currentY = 0; // Mouse cursor's Y position
   private int width = 0;    // The rectangle's width
   private int height = 0;   // The rectangle's height
   private boolean leftWin = false; // sets left window closed
   private boolean rightWin = false; // sets right window closed
   private boolean door = false;    // sets door closed
   
   int[] roofX = {100, 200, 300}; // x coordinates for roof
   int[] roofY = {100, 40, 100}; // y coordinates for roof 
   
   final int EYE_WIDTH = 30; // eye width
   final int EYE_HEIGHT = 40; // eye height
   final int PUPIL_SIZE = 10; // pupil size
   final int EYE_X1 = 140;    // left eye x coord
   final int EYE_Y1 = 20;     // left eye y coord
   final int EYE_X2 = 230;    // right eye x coord
   final int EYE_Y2 = 20;     // right eye y coord
   
   enum eyes{STRAIGHT, UP, DOWN, RIGHT, LEFT}; // enum types for the state of the eyes
   eyes eyeMotion = eyes.STRAIGHT;        // default state of the eyes 
   
   public boolean mouse = false;
   
   public void init()
   {
      // Add a mouse listener and a mouse motion listener.
      addMouseListener(new MyMouseListener());
      addMouseMotionListener(new MyMouseMotionListener());
   }
      /**
      paint method
      @param g The applet's Graphics object.
      */
   
   public void paint(Graphics g)
   {
      // Call the superclass's paint method.
      super.paint(g);
      
      //face
      g.setColor(Color.blue);
      g.fillOval(100, 0, 200,200);
      
      g.setColor(Color.white);
      //left eye
      g.fillOval(EYE_X1, EYE_Y1, EYE_WIDTH, EYE_HEIGHT);
      // right eye
      g.fillOval(EYE_X2, EYE_Y2, EYE_WIDTH, EYE_HEIGHT);
      
      //draw pupil straight
      if(eyeMotion == eyes.STRAIGHT)
      {
         lookStraight(g);
      }
      
      // draw pupil up
      if(eyeMotion == eyes.UP)
      {
         lookUp(g);
      }
      
      // draw pupil down
      if(eyeMotion == eyes.DOWN)
      {
         lookDown(g);
      }
      
      //draw pupil left
      if(eyeMotion == eyes.LEFT)
      {
         lookLeft(g);
      }
      
      //draw pupil right
      if(eyeMotion == eyes.RIGHT)
      {
         lookRight(g);
      }
      
      
      
      //house
     g.setColor(Color.red);
     g.fillRect(100, 100, 200, 100);
     
     //roof
     g.setColor(Color.black);
     g.fillPolygon(roofX, roofY, 3);
     
     // left window
     g.setColor(Color.yellow);
     g.fillRect(120, 130, 40, 40);
     
     // right window
     g.fillRect(240, 130, 40, 40);
     
     //door
     g.fillRect(180, 130, 40, 70); 
     
     if (leftWin) 
     {
        // Draw  left window closed.
        g.setColor(Color.yellow);
        g.fillRect(120, 130, 40, 40);
        g.setColor(Color.black);
        g.drawRect(120, 130, 40, 40);
        g.drawLine(140, 130, 140, 170);
        g.drawLine(120, 150, 160, 150);

     }
     
     if (rightWin) 
     {
        // Draw right window closed.
        g.setColor(Color.yellow);
        g.fillRect(240, 130, 40, 40);
        g.setColor(Color.black);
        g.drawRect(240, 130, 40, 40);
        g.drawLine(260, 130, 260, 170);
        g.drawLine(240, 150, 280, 150);

     }
     
     if (door) 
     {
        // Draw door closed.
        g.setColor(Color.red);
        g.fillRect(180, 130, 40, 70);
        g.setColor(Color.black);
        g.drawRect(180, 130, 40, 70);
        g.fillOval(210, 165, 07, 07);

     }  

   }
   
   
   /**
      paint method draws pupils looking straight
      @param g The applet's Graphics object.
   */
   public void lookStraight(Graphics g)
   {  
      g.setColor(Color.black);
      // left pupil
      g.fillOval(EYE_X1 + (EYE_WIDTH/2)-(PUPIL_SIZE/2), EYE_Y1+(EYE_HEIGHT/2)-(PUPIL_SIZE/2), PUPIL_SIZE, PUPIL_SIZE);
      // right pupil
      g.fillOval(EYE_X2 + (EYE_WIDTH/2)-(PUPIL_SIZE/2), EYE_Y2+(EYE_HEIGHT/2)-(PUPIL_SIZE/2),PUPIL_SIZE, PUPIL_SIZE);
   }
   
   /**
      paint method draws pupils looking up
      @param g The applet's Graphics object.
   */
   public void lookUp(Graphics g)
   {  
      g.setColor(Color.black);
      //left pupil
      g.fillOval(EYE_X1 + (EYE_WIDTH/2)-(PUPIL_SIZE/2), EYE_Y1, PUPIL_SIZE, PUPIL_SIZE);
      // right pupil
      g.fillOval(EYE_X2 + (EYE_WIDTH/2)-(PUPIL_SIZE/2), EYE_Y2, PUPIL_SIZE, PUPIL_SIZE);
   }
   
   /**
      paint method draws pupils looking down
      @param g The applet's Graphics object.
   */
       public void lookDown(Graphics g)
   {
      g.setColor(Color.black);
      // left pupil
      g.fillOval(EYE_X1 + (EYE_WIDTH/2)-(PUPIL_SIZE/2), EYE_Y1 + EYE_HEIGHT-PUPIL_SIZE, PUPIL_SIZE, PUPIL_SIZE);
      // right pupil
      g.fillOval(EYE_X2 + (EYE_WIDTH/2)-(PUPIL_SIZE/2), EYE_Y2 + EYE_HEIGHT-10, PUPIL_SIZE, PUPIL_SIZE);
   }
   
   /**
      paint method draws pupils looking left
      @param g The applet's Graphics object.
   */
       public void lookLeft(Graphics g)
   {  
      g.setColor(Color.black);
      //left pupil
      g.fillOval(EYE_X1, EYE_Y1+(EYE_HEIGHT/2)-(PUPIL_SIZE/2), PUPIL_SIZE,PUPIL_SIZE);
      // right pupil
      g.fillOval(EYE_X2, EYE_Y1+(EYE_HEIGHT/2)-(PUPIL_SIZE/2), PUPIL_SIZE,PUPIL_SIZE);
   }
   
   /**
      paint method draws pupils looking right
      @param g The applet's Graphics object.
   */
    public void lookRight(Graphics g)
   {
      g.setColor(Color.black);
      //left pupil
      g.fillOval(EYE_X1+EYE_WIDTH-PUPIL_SIZE, EYE_Y1+(EYE_HEIGHT/2)-(PUPIL_SIZE/2), PUPIL_SIZE,PUPIL_SIZE);
      //right pupil
      g.fillOval(EYE_X2+EYE_WIDTH-PUPIL_SIZE, EYE_Y1+(EYE_HEIGHT/2)-(PUPIL_SIZE/2), PUPIL_SIZE,PUPIL_SIZE);
   }

    

   
private class MyMouseListener implements MouseListener
   {
      public void mousePressed(MouseEvent e)
      {
         // Get the mouse cursor coordinates.
         currentX = e.getX();
         currentY = e.getY();
         
         System.out.print("\n"+ currentX + " " + currentY);
      }
      public void mouseClicked(MouseEvent e)
      {
      
         // Get the mouse cursor coordinates.
         currentX = e.getX();
         currentY = e.getY();
      
      //if mouse clicks inside left window
      boolean leftWindow = (currentX >= 120 && currentX < 160 && currentY >= 130 && currentY <= 170);
        if (leftWindow)
        {
           leftWin = !leftWin;
           repaint();               
        }
        //if mouse clicks inside right window
        boolean rightWindow = (currentX >= 240 && currentX < 280 && currentY >= 130 && currentY <= 170);
        if (rightWindow)
        {
           rightWin = !rightWin;
           repaint(); 
        }
        
        //if mouse clicks inside door
        boolean Door = (currentX >= 180 && currentX <= 220 && currentY >= 130 && currentY <= 200);
        if (Door)
        {            
           door = !door;
           repaint();     
        }  
      }

      public void mouseReleased(MouseEvent e)
      {
      }

      public void mouseEntered(MouseEvent e)
      {
         // mouse is in the applet
         mouse = true; 
      }

      public void mouseExited(MouseEvent e)
      {
         //mouse is out of the applet
         mouse = false;
         eyeMotion = eyes.STRAIGHT;
         repaint();
         
      }
   }
   
   private class MyMouseMotionListener implements MouseMotionListener
   {
      public void mouseDragged(MouseEvent e)
      {  
      }

      public void mouseMoved(MouseEvent e)
      {
         eyes state = eyeMotion;
      
         if(mouse=true)
         {
            // moves eye up
            if(e.getY() < 40)
            {
               eyeMotion = eyes.UP;
            }
            // moves eye down
            if(e.getY() > EYE_Y1+EYE_HEIGHT)
            {
               eyeMotion = eyes.DOWN;
            }
            // moves eye left
            if(e.getX() < EYE_X1)
            {
               eyeMotion = eyes.LEFT;
            }
            //moves eye right
            if(e.getX() > EYE_X2 + EYE_WIDTH)
            {
               eyeMotion = eyes.RIGHT;
            }
            // repaints
            if(state!=eyeMotion)
            {
            repaint();
            }
         }
      }
   }
   
}
