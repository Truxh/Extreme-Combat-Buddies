package game;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class CombatEntity implements KeyListener, Runnable{
	String name; //Character name
	Rectangle hitbox; //Hitbox, based on image size, must be over 30 x 80 and under 150 * 200
	BufferedImage sprite; //Character image 
	BufferedImage abilities[]; //Unique abilities images
	int damage; //Damage multiplier from 1-10, all skill damages will be multiplied by this amount
	int health; //Health multiplier from 1-10, character maximum health will be multiplied by this amount
	int[] pressedKeys = new int[223]; //array containg pressed keys, if key code element is stored as 1 the key is pressed
	Point location; //centralized location 
	
	public CombatEntity(String name, Rectangle hitbox, BufferedImage sprite, BufferedImage[] abilities, int damage, int health) {
		this.name = name;
		this.hitbox = hitbox;
		this.sprite = sprite;
		this.abilities = abilities;
		this.damage = damage;
		this.health = health;	
		
				
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		e.getKeyCode();
		System.out.println(e);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void move() {
		
	}
	public void jump() {
		
	}
	public void crouch() {
		
	}
	public void attackQ() {
		
	}
	public void attackW() {
		
	}
	public void attackE() {
		
	}
	public void attackR() {
		
	}
	public void hit() {
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
