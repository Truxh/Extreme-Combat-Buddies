package game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import graphics.ArenaReader;

public class Arena {
	public static Dimension border;
	public static BufferedImage arenaImg;
	public static ArrayList<Rectangle> hitboxes;
	public static ArrayList<Missile> missiles;
	public static int length = 160; //in feet 
	public int width = 90; //in feet (1 ft = 10 pixels)
	

	public Arena() {
		arenaImg = ArenaReader.getImg();
		hitboxes = ArenaReader.getHitboxes();	
		missiles = new ArrayList<Missile>();
	}
}
