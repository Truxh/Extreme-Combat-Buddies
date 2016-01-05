package characters;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import game.CombatEntity;
public class TestSprite extends CombatEntity{
	static String name = "Test Sprite"; 
	static Rectangle hitbox = new Rectangle(0, 0, 60, 100); 
	static File file = new File("SpriteTest.png");
	public static BufferedImage sprite;// = ImageIO.read(file); 
	static BufferedImage abilities[]=null; 
	static int damage;
	static int health; 
	static int[] pressedKeys = new int[223]; 
	public static Point location; 
	
	public TestSprite() {
		super(name, hitbox, sprite, abilities, damage, health);
		location = new Point(200, 900);
		hitbox = new Rectangle((int)location.getX(),(int) location.getY(), hitbox.width, hitbox.height);
		initalizeSpriteImage(/*parameter*/);

	}
	public void initalizeSpriteImage() {
		try {
			sprite = ImageIO.read(file); //HOW DO I GIVE THIS GUY TO SUPER CLASS :( 
		} catch(Exception e) {
			//super(/*i CaNt FrIcKiN iNiTaLiZe ThIs GoD dArNnIt JaVa Is aNoYiNg aF*/); 
		}
	}
}
