package game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Missile {
	private Sprite player;
	public double x;
	public double y;
	public double width;
	public double height;
	public boolean dir;
	public static BufferedImage img;
	public final double velocity = 0.6;
	public Rectangle hitbox = new Rectangle((int) x,(int) y,(int) width,(int) height);;
	
	public Missile(Sprite sprite, double x, double y, boolean dir) {
		player = sprite;
		this.x = x;
		this.y = y;
		this.dir = dir;
		width = img.getWidth();
		height = img.getHeight();
	}
	
	
	public static void readMissile() {
		try {
			img = ImageIO.read(new File("Resources//Missile.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Point translateCoordinates(double x, double y) {
		y = 1080 - (y * 10 + 0.5*height);
		x = (x * 10) - (0.5 * width);
		return new Point((int) x, (int) y);
	}

	public void update() {
		if(dir == true) x += velocity;
		else x -= velocity;
		Point p = translateCoordinates(x, y);
		hitbox = new Rectangle((int) p.x,(int) p.y,(int) width,(int) height);
	}

}
