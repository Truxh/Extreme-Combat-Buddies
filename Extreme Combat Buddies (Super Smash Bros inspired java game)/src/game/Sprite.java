package game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import graphics.Display;

public class Sprite implements KeyListener {
	/*
	 * TODO: 1) Check if line between current/last point intersects floor, if so
	 * teleport to that floor/x
	 */

	File imageLoc = new File("Resources//soldier.png");
	public BufferedImage img;
	ControlSet cs;
	public String name;
	public double startX;
	public double x = new Random().nextInt(1920 / Display.scale); // in feet
	public double y = new Random().nextInt(1080 / Display.scale); // in feet
	public int height = 70; // in pixels
	public int width = 55; // in pixels
	public boolean dir = new Random().nextBoolean(); // true is direction =
														// right
	int player;
	public Rectangle r = new Rectangle();
	boolean[] pressedKeys = new boolean[222];
	boolean jump = false;
	public boolean crouch = false;
	double jumpHeight = 0;

	double angle = 80;
	double time;
	double missileDelay = 0;
	double hitDelay = 0;
	double teleportDelay = 0;
	double burstDelay = 0;
	double tburstDelay = 0;
	double floorDelay = 0;
	double directionDelay = 0;
	int burstCount = 0;
	public int health = 3;

	double xvelocity = 0;
	double yvelocity = 0;
	final double gravity = -26; // -26

	double jumpTime = 0;
	double xTime = 0;
	double jumpDelay = 0;
	public Rectangle hitbox = new Rectangle(0, 0, 1, 1);

	Point lastPoint = new Point();
	double prevX = x;
	boolean wallFlag = false;
	boolean prevDir = dir;

	public Line2D intersector = new Line2D.Float();

	public Sprite(int i, String name, ControlSet cs) {
		this.player = i;
		this.name = name;
		this.cs = cs;
		readImage();
		height = img.getHeight();
		width = img.getWidth();
		initKeys();
		startX = x;
	}

	void readImage() {
		try {
			img = ImageIO.read(imageLoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateMovement() {
		crouch = false;
		if (pressedKeys[cs.left] == true && checkWalls() != true) {
			xvelocity = -50;
			dir = false;
			if (dir != prevDir)
				switchDir();
			prevDir = false;
			wallFlag = false;
		}
		if (pressedKeys[cs.right] == true && checkWalls() != true) {
			xvelocity = 50;
			dir = true;
			if (dir != prevDir)
				switchDir();
			prevDir = true;
			wallFlag = false;
		}
		if (checkWalls() == true) {
			xTime = 0;
			startX = x;
			x = prevX;
		}
		if (pressedKeys[cs.jump] == true && time - jumpDelay >= 0.4) {
			yvelocity = 7;
			y++;
			jumpDelay = time;
			jump = true;

		}

		if (pressedKeys[cs.crouch] == true) {
			crouch = true;

		} else
			crouch = false;

		if (pressedKeys[cs.upTele] == true && time - teleportDelay >= 2.5) {
			y = y + 100;
			teleportDelay = time;
		}

		if (pressedKeys[cs.downTele] == true && time - teleportDelay >= 2.5 && y >= 42) {
			y = y - 40;
			teleportDelay = time;
		}

		if (pressedKeys[cs.fire] == true && time - missileDelay >= 0.6) {
			fireMissile();
			missileDelay = time;
		}

		if (pressedKeys[cs.burst] == true && time - burstDelay >= 1.5 && time - tburstDelay >= 0.05) {
			burstCount++;
			fireMissile();
			tburstDelay = time;
			if (burstCount >= 8) {
				burstDelay = time;
				burstCount = 0;
			}
		}

		if (pressedKeys[cs.left] != true && pressedKeys[cs.right] != true) {

			xvelocity = 0;
			xTime = 0;
			startX = x;

		}
		if ((pressedKeys[cs.left] == true || pressedKeys[cs.right] == true) && wallFlag != true) {
			x = (xvelocity * xTime) + startX;
			prevX = x;
		}
		if (checkFloor() != true) {
			y = (0.5 * gravity * Math.pow(jumpTime, 2)) + yvelocity * jumpTime + y;
			// x equation of movement
			jump = false;
		}

		else {
			jumpTime = 0;
			yvelocity = 0;
		}

	}

	private void fireMissile() {
		double x;
		if (dir == true)
			x = this.x + 0.6 * this.width / graphics.Display.scale;
		else
			x = this.x - 0.6 * this.width / graphics.Display.scale;
		Arena.missiles.add(new Missile(this, x, y, dir));
	}

	public void update(int gameTime, int increment) {
		// lastPoint = new Point((int) (x*10- 0.5 * width),(int)(1080 - y * 10 -0.5 * height));
		time = (double) gameTime / 1000;
		jumpTime += (double) increment / 1000; // 0.01
		xTime += (double) increment / 1000;
		updateMovement();
		if (checkHit() == true && time - hitDelay >= 0.15) {
			health--;
			hitDelay = time;
		}
	}

	private boolean lineIntersect(Point c1, Point c2, Rectangle floor) {
		Line2D floorLine = new Line2D.Float((float) floor.x, (float) floor.y, (float) (floor.x + floor.getWidth()),
				(float) floor.y);

		intersector = new Line2D.Float(c1, c2);

		if (intersector.intersectsLine(floorLine))
			return true;
		else
			return false;

	}

	private boolean checkFloor() {
		//hitbox = new Rectangle((int) (x * 10 - 0.5 * width), (int) (1080 - y * 10 - 0.5 * height) + 50, width, 5); // 20
		boolean flag = false;
		Point currentPoint = new Point((int)((x * 10 - 0.5 * width)+0.5 * width), (int) ((1080 - y * 10 - 0.5 * height)+height));
		for (int i = 0; i < Arena.hitboxes.size(); i++) {
			Rectangle floor = Arena.hitboxes.get(i);
			 //if (floor.intersects(hitbox)) { flag = true;g break; }
			if (lineIntersect(currentPoint, lastPoint, floor)) {
				y = (1080 - floor.y + height / 2) / 10 + 1;
				flag = true;
			}
		}
		lastPoint = currentPoint;
		return flag;
	}

	private boolean checkWalls() {
		boolean flag = false;
		if (x <= 10/graphics.Display.scale) {
			flag = true;
			x+=0.1;
		}
		else if (x >= 1918 / graphics.Display.scale) {
			flag = true;
			x-=0.1;
		}
		
		
		return flag;
	
	}

	public boolean checkHit() {

		boolean flag = false;
		for (int i = 0; i < Arena.missiles.size(); i++) {
			if (Arena.missiles.get(i).hitbox.intersects(new Rectangle((int) (x * graphics.Display.scale - 0.5 * width),
					(int) (1080 - y * graphics.Display.scale - 0.5 * height), width, height))) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private void initKeys() {
		for (int i = 0; i < pressedKeys.length; i++)
			pressedKeys[i] = false;
	}

	private void switchDir() {
		xTime = 0;
		startX = x;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		pressedKeys[e.getKeyCode()] = true;
		// System.out.print(e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		pressedKeys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
