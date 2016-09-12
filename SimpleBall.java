import javax.swing.Timer;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SimpleBall{
    //variables for different properties
    //of the ball
    private int x;
    private int y;
    private int radius;
    private int dx;
    private int dy;
    private int Red;
    private int Green;
    private int Blue;
    private Color mycolor;

    //constructor
    public SimpleBall(){
	     x = 25;
       y = 10;
       radius = 5+1;
       dx = 2;
       dy = 2;
	     Red = (int)(Math.random()*256);
	     Green = (int)(Math.random()*256);
	     Blue = (int)(Math.random()*256);
       mycolor = new Color (Red,Green,Blue);
    }

    //return value of x
    public int get_x(){
	       return x;
    }
    //return value of y
    public int get_y(){
	return y;
    }
    //return value of the radius
    public int get_radius(){
	return radius;
    }
    //return the value of dx
    public int get_dx(){
	return dx;
    }
    //return the value of dy
    public int get_dy(){
	return dy;
    }
    //set dx to value
    public void set_dx(int value){
	dx = value;
    }
    //set dy to value
    public void set_dy(int value){
	dy = value;
    }
    //set x to value
    public void set_x(int value){
	x = value;
    }
    //set y to value
    public void set_y(int value){
	y = value;
    }
    //set radius to value
    public void set_radius(int value){
	radius = value;
    }
    //set color to value
    public void set_color(Color value){
	mycolor = value;
    }
    //return the color of the ball
    public Color get_color(){
	return mycolor;
    }
}
