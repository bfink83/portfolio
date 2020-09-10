// Lab04bvst.java
// The AWT Graphics Program
// This is the student, starting version of Lab04b.


import java.awt.*;
import java.applet.*;


public class Lab04 extends Applet
{

	public void paint(Graphics g)
	{
		// DRAW CUBE
      g.drawRect(25,25,100,100);
      g.drawRect(75,75,100,100);
      g.drawLine(25,25,75,75);
      g.drawLine(125,25,175,75);
      g.drawLine(25,125,75,175);
      g.drawLine(125,125,175,175);


		// DRAW SPHERE
      g.drawOval(200,25,200,200);
      g.drawArc(200,100,200,50,0,180);
      g.drawArc(200,100,200,50,0,-180);
      g.drawArc(200,75,200,100,0,180);
      g.drawArc(200,75,200,100,0,-180);
      g.drawArc(200,50,200,150,0,180);
      g.drawArc(200,50,200,150,0,-180);
      g.drawArc(275,25,50,200,0,90);
      g.drawArc(275,25,50,200,0,-90);
      g.drawArc(275,25,50,200,180,270);
      g.drawArc(275,25,50,200,90,180);
      g.drawArc(250,25,100,200,0,90);
      g.drawArc(250,25,100,200,0,-90);
      g.drawArc(250,25,100,200,180,270);
      g.drawArc(250,25,100,200,90,180);
      g.drawArc(225,25,150,200,0,90);
      g.drawArc(225,25,150,200,0,-90);
      g.drawArc(225,25,150,200,180,270);
      g.drawArc(225,25,150,200,90,180);



		// DRAW INSCRIBED/CIRCUMSCRIBED TRIANGLE
      g.drawOval(25,300,300,300);
      g.drawLine(85,569,175,300);
      g.drawLine(85,569,265,569);
      g.drawLine(175,300,265,569);
      g.drawOval(110,439,130,130);



		// DRAW APCS
      g.fillRect(250,25,25,100);



		// DRAW PACMEN FLOWER



	}

}


