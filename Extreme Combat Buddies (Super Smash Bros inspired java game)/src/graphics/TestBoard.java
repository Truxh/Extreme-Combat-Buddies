package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;

import characters.TestSprite;

public class TestBoard extends JPanel {
	TestSprite ts;

	TestBoard() {
		ts = new TestSprite();
		addKeyListener(ts);
		setFocusable(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(ts.sprite, (int) ts.location.getX(), (int) ts.location.getY(), null);
		setBackground(new Color(200, 200, 20));
	}
}
