import java.awt.*;
import javax.swing.*;


public class BreakoutApp extends JApplet{
  public BreakoutApp(){
    add(new BallControl());
  }

  public static void main(String[] args) {
      BreakoutApp applet = new BreakoutApp();
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("BreakoutApp");
      frame.add(applet, BorderLayout.CENTER);
      frame.setSize(400, 320);
      frame.setVisible(true);
  }
}
