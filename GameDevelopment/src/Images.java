import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Images extends JFrame{
	public static void main(String[] args) {
		DisplayMode dm=new DisplayMode(1366,768, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		Images i=new Images();
		i.run(dm);

	}

	private Screen s;
	private Image bg;
	private Image pic;
	private boolean loaded;



	private void run(DisplayMode dm) {
		this.getContentPane().setBackground(Color.PINK);
		setForeground(Color.WHITE);
		setFont(new Font("Arial",Font.PLAIN,24));
		loaded=false;

		s=new Screen();

		try{
			s.setFullScreen(dm,this);	
			loadpics();
			try{
				Thread.sleep(10000);
			}catch(Exception e){
				System.out.println("Thread sleep not");
			}
		}finally{
			s.restoreScreen();
		}
		System.out.println("out run");
	}

	private void loadpics() {
		ClassLoader cldr = this.getClass().getClassLoader(); 
		java.net.URL imageURL_bg = cldr.getResource("ABC.jpg"); 
		bg = new ImageIcon(imageURL_bg).getImage();
		
		java.net.URL imageURL_pic = cldr.getResource("image.gif"); 
		pic = new ImageIcon(imageURL_pic).getImage();
		
		loaded=true;
		repaint();
	}

	public void paint(Graphics g){
		super.paint(g);
		if(g instanceof Graphics2D){
			Graphics2D g2=(Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}
		if(loaded){
			System.out.println(g.drawImage(bg,0,0,null));
			g.drawImage(pic,15,15,null);
		}
		
	}
}
