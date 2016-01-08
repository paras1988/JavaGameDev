import java.applet.*; 
import java.awt.*; 

import javax.swing.ImageIcon;
             
public class MainAc extends Applet{
   Image img;
   public void paint(Graphics g) {
      img = new ImageIcon("image.gif").getImage();
      g.drawImage(img, 0, 0, null);
   } 
}