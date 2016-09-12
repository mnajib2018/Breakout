import javax.swing.Timer;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.lang.*;
import java.util.Random;

public class Ball extends JPanel {
  private int delay;
  private Timer timer = new Timer(delay, new TimerListener());
  //bool variable for timer
  private boolean first_click = true;
  //List of Balls
  private ArrayList<SimpleBall> Balls = new ArrayList<SimpleBall> (0);
  private SimpleBall ball1 = new SimpleBall();
  //constructor
  public Ball() {
     timer.start();
     create_first_ball();
     for(int x = 0; x < 5; x++ ){
       create_new_ball();
     }
   }
  //create first ball seperately for its original
  //properties
  public void create_first_ball(){
      //ball1 = new SimpleBall();
      ball1.set_color(Color.red);
      ball1.set_x(0);
      ball1.set_radius(5);
      //Balls.add(ball1);
  }

  //create new ball whenever +1 clicked
  public void create_new_ball(){
      SimpleBall ball2 = new SimpleBall();
      Balls.add(ball2);
  }
  //destroy most recent ball if -1 clicked
  public void destroy_new_ball(){
      Balls.remove(Balls.size()-1);
  }
  //change x-direction in opposite
  public void negate_dx(SimpleBall item){
	int dummy_variable = item.get_dx();
	dummy_variable *= -1;
	item.set_dx(dummy_variable);
  }
  //change y-direction in opposite
  public void negate_dy(SimpleBall item){
	int dummy_variable = item.get_dy();
	dummy_variable *= -1;
	item.set_dy(dummy_variable);
  }
  //add dx to x
  public void change_x(SimpleBall item){
	int dummy_x = item.get_x();
	dummy_x += item.get_dx();
	item.set_x(dummy_x);
  }
  //add dy to y
  public void change_y(SimpleBall item){
	int dummy_y = item.get_y();
	dummy_y += item.get_dy();
	item.set_y(dummy_y);
  }

  //change all the properties as needed to reflect
  public void reflect(Graphics g,SimpleBall item,
		      SimpleBall other_item){
      //change color, dx, dy of both balls
      //change x and y of both balls
      //fill pixels at specified Ovals
      g.setColor(item.get_color());
      negate_dx(item);negate_dx(other_item);
      negate_dy(item);negate_dy(other_item);
      change_x(item);change_x(other_item);
      change_y(other_item);change_y(other_item);
      g.fillRect(item.get_x() - item.get_radius(),
		 item.get_y() - item.get_radius(),
		 item.get_radius() * 2,item.get_radius() * 2);
      g.fillRect(other_item.get_x() - other_item.get_radius(),
		 other_item.get_y() - other_item.get_radius(),
		 other_item.get_radius() * 2,
		 other_item.get_radius() * 2);
    }

  //check if (center+radius) of one ball fall
  //inside length AND width both of the other ball.
  //If so, bounce off the two balls.if condition
  //for converse(center-radius) not needed
  //it will get checked for the other ball in the for loop
  public void check_collision(Graphics g){
	for(SimpleBall item : Balls){
	  for(SimpleBall other_item : Balls){
	    //check for (x+radius) for within specified range
	    if((item.get_x()+item.get_radius())
	       <= (other_item.get_x()+other_item.get_radius())
	    && (item.get_x()+item.get_radius())
	       >= (other_item.get_x()-other_item.get_radius()))
	       //if true check for (y+radius) similarly
	       if((item.get_y()+item.get_radius())
		  <= (other_item.get_y()+other_item.get_radius())
	       && (item.get_y()+item.get_radius())
		  >= (other_item.get_y()-other_item.get_radius()))
		 //if different balls
		 if(other_item != item){
		     reflect(g, item, other_item);
    	      }
    	  }
       }
    }

  //Timer class to repaint for every tick
  private class TimerListener implements ActionListener {
    @Override /** Handle the action event */
    public void actionPerformed(ActionEvent e) {
      repaint();
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // change position and fill color for each ball
    for(SimpleBall item :Balls){
	g.setColor(item.get_color());
	// Check boundaries
	if (item.get_x() < 0 || item.get_x() > getWidth())
	    negate_dx(item);
	if (item.get_y() < 0 || item.get_y() > getHeight())
	    negate_dy(item);
	//make changes to x and y coordinates
	//change_x(item);
	//change_y(item);
	//color the needed oval
	g.fillRect(item.get_x() - item.get_radius(),
		   item.get_y() - item.get_radius(),
		   item.get_radius() * 2, item.get_radius() * 2);
    }

  SimpleBall item = ball1;
  g.setColor(item.get_color());
  // Check boundaries
  if (item.get_x() < 0 || item.get_x() > getWidth())
      negate_dx(item);
  if (item.get_y() < 0 || item.get_y() > getHeight())
      negate_dy(item);
  //make changes to x and y coordinates
  change_x(item);
  change_y(item);
  //color the needed oval
  g.fillRect(item.get_x() - item.get_radius(),
       item.get_y() - item.get_radius(),
       item.get_radius() * 2, item.get_radius() * 2);
    //check for collision and bounce the balls
    check_collision(g);
  }





  //suspend used to switch timer on and off
  public void suspend() {
      if(first_click){
	  //Suspend Timer
	  timer.stop();
      }else{
	  //Start Timer
	  timer.start();
      }
      first_click = !first_click;
  }

  public void resume() {
  timer.start(); // Resume timer
}

  //set delay time for the timer
  //depending on the scroll bar position
  public void setDelay(int delay) {
    this.delay = delay;
    timer.setDelay(delay);
  }
}





/***
import javax.swing.Timer;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Ball extends JPanel {
  private int delay = 10;

  // Create a timer with delay 1000 ms
  private Timer timer = new Timer(delay, new TimerListener());

  private int x = 0; private int y = 0; // Current ball position
  private int radius = 5; // Ball radius
  private int dx = 2; // Increment on ball's x-coordinate
  private int dy = 2; // Increment on ball's y-coordinate

  public Ball() {
    timer.start();
  }

  private class TimerListener implements ActionListener {
    @Override // /* Handle the action event */
    /*
    public void actionPerformed(ActionEvent e) {
      repaint();
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.red);

    // Check boundaries
    if (x < 0 || x > getWidth())
      dx *= -1;
    if (y < 0 || y > getHeight())
      dy *= -1;

    // Adjust ball position
    x += dx;
    y += dy;
    g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
  }

  public void suspend() {
    timer.stop(); // Suspend timer
  }

  public void resume() {
    timer.start(); // Resume timer
  }

  public void setDelay(int delay) {
    this.delay = delay;
    timer.setDelay(delay);
  }
}
***/
