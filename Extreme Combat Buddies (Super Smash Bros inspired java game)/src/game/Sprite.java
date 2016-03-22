package game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import graphics.Display;

public class Sprite implements KeyListener {
	File imageLoc = new File("Resources//soldier.png");
	public BufferedImage img;
	ControlSet cs;
	public String name;
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
	int burstCount = 0;
	public int health = 3;

	double xvelocity = 0;
	double yvelocity = 0;
	double gravity = -32;

	double jumpTime = 0;
	double jumpDelay = 0;

	public Sprite(int i, String name, ControlSet cs) {
		this.player = i;
		this.name = name;
		this.cs = cs;
		readImage();
		height = img.getHeight();
		width = img.getWidth();
		initKeys();
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
			xvelocity = -0.5;
			x = x + xvelocity;
			dir = false;
		} else if (checkWalls() == true)
			x--;

		if (pressedKeys[cs.right] == true && checkWalls() != true) {
			xvelocity = 0.5;
			x = x + xvelocity;
			dir = true;
		} else if (checkWalls() == true)
			x++;

		if (pressedKeys[cs.jump] == true && time - jumpDelay >= 1) {
			yvelocity = 7;
			y++;
			jumpDelay = time;

		}

		if (pressedKeys[cs.crouch] == true) {
			crouch = true;
			xvelocity = 0;
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
		}

		if (checkFloor() != true) {
			// x = (xvelocity / 1000 * Math.cos(Math.toRadians(angle))) * time +
			// x;
			y = (0.5 * gravity * Math.pow(jumpTime, 2)) + yvelocity * jumpTime + y;
			System.out.println(yvelocity);
		}
		
		else {
			jumpTime = 0;
			yvelocity = 0;
		}


	}

	private void fireMissile() {
		double x;
		if (dir == true)
			x = this.x + 0.6 * this.width / 10;
		else
			x = this.x - 0.6 * this.width / 10;
		Arena.missiles.add(new Missile(this, x, y, dir));
	}

	public void update(int gameTime) {
		time = (double) gameTime / 1000;
		jumpTime += 0.01;
		updateMovement();
		if (checkHit() == true && time - hitDelay >= 0.15) {
			health--;
			hitDelay = time;
		}
	}

	private boolean checkFloor() {
		boolean flag = false;
		for (int i = 0; i < Arena.hitboxes.size(); i++) {
			if (Arena.hitboxes.get(i).intersects(
					new Rectangle((int) (x * 10 - 0.5 * width), (int) (1080 - y * 10 - 0.5 * height), width, height))) {
				flag = true;
				break;
			} 
		/*	if (Arena.hitboxes.get(i).y == (int) (1080 - y * 10 - 0.5 * height)) {
				flag = true;
				break;
			} */ 
		}
		return flag;
	}

	private boolean checkWalls() {
		boolean flag = false;
		if (x == 1)
			flag = true;
		else if (x == 191)
			flag = true;
		else if (y == 108)
			flag = true;
		return flag;
	}

	public boolean checkHit() {

		boolean flag = false;
		for (int i = 0; i < Arena.missiles.size(); i++) {
			if (Arena.missiles.get(i).hitbox.intersects(
					new Rectangle((int) (x * 10 - 0.5 * width), (int) (1080 - y * 10 - 0.5 * height), width, height))) {
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
