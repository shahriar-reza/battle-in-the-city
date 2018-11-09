import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class Intro extends java.applet.Applet
{
    Image img1, img2;
    int x1, x2, y1, speed;
    boolean opening, opened;
	
    public Intro()
    {
	this.x1 = 0;
	this.x2 = 0;
	this.opening = false;
	this.opened = false;
	this.speed = 15;
    }

    @Override
    public void paint(Graphics g)
    {		
	g.setColor(Color.BLACK);
	g.fillRect(0+x1,0,302,600);
	g.fillRect(302+x2,0,298,600);
	g.drawImage(img1,0+x1,0,this);
	g.drawImage(img2,302+x2,1,this);
	g.setColor(Color.CYAN);
	g.setFont(new Font("AR BONNIE", Font.BOLD,  30));
	g.drawString("Spring 2016 OOP Project",150,570+y1);		
    }

    public void move(){
	x1 -= speed;
	x2 += speed+1;
	y1 += (int)speed;
	if ( (x1<-175) && (x2>175) ) {
            opening=false; 
            opened=true;
	}
    }	
}