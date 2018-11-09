import java.awt.*;
import java.applet.*;

public class Tank extends Applet
{
    final int NORTH = 1;
    final int EAST = 2;
    final int SOUTH = 3;
    final int WEST = 4;
    int direction, moveBy;
    Color color;
    Point target;
    public int x, y, w, h;
    public int maxX=600,maxY=600,minX=0,minY=0;
    public int maxFire;	// The maximum amount of shots allowed for the tank to fire at once
    public int maxShells;// The max number of shells allowed to be fired
    public boolean isMoving, isAlive, exploded;
    Graphics g;

    public Tank(int xpos, int ypos, Color c, int speed, int newDirection, int maxFire){
	color = c;	
	this.x = xpos;
	this.y = ypos;
	this.direction = newDirection;
	this.moveBy = speed;
	this.isMoving = true;
	this.maxFire = maxFire;
	this.maxShells = 1000;
	this.isAlive = false;
	this.exploded = false;
    }
	
    public void move(){
	switch (direction) {
            case NORTH: if( (y-moveBy<minY) ){
                            isMoving=false;
                        }else {
                            y -= moveBy;
                        }
                break;
            case EAST:  if( (x+tankWidth()+moveBy)>maxX){
                            isMoving=false;
                        }else { 
                            x += moveBy;
                        }
                break;
            case SOUTH: if( (y+tankHeight()+moveBy)>maxY ){
                            isMoving=false;
                        }else { 
                            y += moveBy;
                        }
                break;
            case WEST:  if( (x-moveBy<minX) ){
                            isMoving=false;
                        }else { 
                            x -= moveBy;
                        }
                break;
        }    
    }
	
    public void turn(int newDirection){
        this.direction = newDirection;
    }
	
    public void turnAround(){
	direction += 2;
	if(direction>4){
            direction -= 4;
        }
    }
	
    void explode(){
	exploded = true;
    }
	
    @Override
    public void paint(Graphics gr){
        if (!isAlive)
            return;
	g = gr;
	if(exploded){
            g.setColor(new Color(255,255,100));
            g.fillOval(x,y,60,60);
            g.setColor(new Color(255,255,170));
            g.fillOval(x+5,y+5,50,50);
            g.setColor(new Color(255,255,225));
            g.fillOval(x+10,y+10,40,40);
            isAlive = false;
            isMoving = false;
            exploded = false;
            return;
	}
	//polygon canon wheels
	int x11[] = {0+x,35+x,35+x,25+x,25+x,10+x,10+x,0+x};
	int y11[] = {5+y,5+y,40+y,40+y,5+y,5+y,40+y,40+y};
	int x12[] = {0+x,35+x,35+x,0+x,0+x,35+x,35+x,0+x};
	int y12[] = {0+y,0+y,35+y,35+y,25+y,25+y,10+y,10+y};
	int x13[] = x11;
	int y13[] = {0+y,0+y,35+y,35+y,0+y,0+y,35+y,35+y};
	int x14[] = {5+x,40+x,40+x,5+x,5+x,40+x,40+x,5+x};
	int y14[] = y12;
	int x1[][] = {x11,x12,x13,x14};
	int y1[][] = {y11,y12,y13,y14};
	//roundrect canon compartment
	int x2[] = {5+x,5+x,5+x,10+x};
	int y2[] = {10+y,5+y,5+y,5+y};
	//rect canon case
	int x3[] = {15+x,20+x,15+x,8+x};
	int y3[] = {8+y,15+y,20+y,15+y};
	int x3w[] = {5,12,5,12};
	int y3h[] = {12,5,12,5};
	//oval canon cap
	int x4[] = {10+x,10+x,10+x,15+x};
	int y4[] = {15+y,10+y,10+y,10+y};
	//canon
	int x5[] = {16+x,30+x,16+x,0+x};
	int y5[] = {0+y,16+y,30+y,16+y};
	int x5w[] = {3,10,3,10};
	int y5h[] = {10,3,10,3};
	int d = direction - 1;
	//draw wheels
	g.setColor(Color.black);
	g.fillPolygon(x1[d], y1[d] ,8);
	//draw compartment
	g.setColor(color);
	g.fillRoundRect(x2[d],y2[d],25,25,5,5);
	//draw canon
	g.setColor(Color.black);
	g.fillRect(x5[d],y5[d],x5w[d],y5h[d]);
	//draw canon case
	g.setColor(new Color(47,79,79));
	g.fillRect(x3[d],y3[d],x3w[d],y3h[d]);
	//draw canon cap		
	g.setColor(Color.black);
	g.fillOval(x4[d],y4[d],15,15);
    }
	
    int tankWidth(){
        if(direction==1||direction==3)
            return 35;
	else 
            return 40;
    }
    
    int tankHeight(){
	if(direction==2||direction==4)
            return 40;
	else 
            return 35;
    }
}
