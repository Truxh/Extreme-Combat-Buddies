package graphics;

import java.awt.*;


import javax.swing.JButton;
import javax.swing.JPanel;

import characters.TestSprite;
import game.CombatEntity;

public class MainMenu extends JPanel {
	TestSprite ts;
	MainMenu() {
	JButton start = new JButton("Start Game");
	add(start);
	ts = new TestSprite();
	addKeyListener(ts);
	setFocusable(true);
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(ts.sprite, (int) ts.location.getX(), (int) ts.location.getY(), null);
	}
	
}
