import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Parry extends JFrame{
	public static void main(String[] args) {
		DisplayMode dm=new DisplayMode(1366,768, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		Parry p=new Parry();
		p.run(dm);
	}
	
	private Screen screen;
	private Image bg;
	private Image pic1;
	private Image pic2;
	private Animation a;

	public void loadPics(){
		ClassLoader cldr = this.getClass().getClassLoader(); 
		java.net.URL imageURL_bg = cldr.getResource("ABC.jpg"); 
		bg = new ImageIcon(imageURL_bg).getImage();

		java.net.URL imageURL_pic = cldr.getResource("image.gif"); 
		pic1 = new ImageIcon(imageURL_pic).getImage();

		java.net.URL imageURL_pic2 = cldr.getResource("image2.gif"); 
		pic2 = new ImageIcon(imageURL_pic2).getImage();

		a=new Animation();
		a.addScene(pic1, 250);
		a.addScene(pic2, 250);
	}

	public void run(DisplayMode dm){
		screen=new Screen();
		try{
			screen.setFullScreen(dm,new JFrame());
			loadPics();
			movieLoop();	
		}
		finally{
			screen.restoreScreen();
		}
	}

	public void movieLoop(){
		long startingTime=System.currentTimeMillis();
		long cumTime=startingTime;

		while(cumTime - startingTime <5000){
			long timePassed=System.currentTimeMillis()-cumTime;
			cumTime +=timePassed;
			a.update(timePassed);

			Graphics g=screen.getFullScreenWindow().getGraphics();
			draw(g);
			g.dispose();

			try{
				Thread.sleep(20);
			}catch(Exception ex){
			}
		}
	}

	//draw method
	public void draw(Graphics g){
		super.paint(g);
		g.drawImage(bg, 0, 0, null);
		g.drawImage(a.getImage(),0,0,null);
	}
}