package graphics;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ArenaReader {
	File img = new File("Resources/Arena1.png");
	static BufferedImage arena;
	Color floorColor = new Color(78, 0, 255);
	static ArrayList<Rectangle> floors; 
	
	public ArenaReader() {
		readImage();
		floors = locateHitboxes();
	}
	private void readImage() {
		try {
			arena = ImageIO.read(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ArrayList<Rectangle> locateHitboxes() {
		ArrayList<Rectangle> list = new ArrayList<Rectangle>();
		for (int x = 0; x < arena.getWidth(); x++) {
			for (int y = 0; y < arena.getHeight(); y++) { 
				int cW = 0;
				int cH = 0;
				int cX = x;
				int cY = y;
			
				if(arena.getRGB(x, y) == floorColor.getRGB()) {
				
					
					while(arena.getRGB(x, y + cH) == floorColor.getRGB()) {
						cH++;
					
					}
					y = y + cH;
					
					while(arena.getRGB(x + cW, y-cH) == floorColor.getRGB()) {
						cW++;
					}

					
				
					list.add(new Rectangle(cX, cY, cW, cH));
					
				}
			}
		
		}
		return list;
	}
	
	
	public static BufferedImage getImg() {
		// TODO Auto-generated method stub
		return arena;
	}
	public static ArrayList<Rectangle> getHitboxes() {
		// TODO Auto-generated method stub
		return floors;
	}
}
