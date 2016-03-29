package graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;

import game.Arena;
import game.Missile;
import game.Sprite;

public class Display extends JPanel {
	Timer timer;
	Sprite[] players;
	ArrayList<Point> trail = new ArrayList<Point>();
	private int width;
	private int height;
	public static int scale = 10;

	public Display(Sprite[] players) {
		this.players = players;
		this.width = Window.width;
		this.height = Window.height;
		for (int i = 0; i < players.length; i++)
			addKeyListener(players[i]);
		setFocusable(true);
	}

	public Point translateCoordinates(double x, double y, Sprite p) {
		y = (height - (y * scale)) - (0.5 * p.height);
		x = (x * scale) - (0.5 * p.width);
		return new Point((int) x, (int) y);
	}

	public Point translateCoordinates(double x, double y, Missile m) {
		y = (height - (y * scale)) - (0.5 * m.height);
		x = (x * scale) - (0.5 * m.width);
		return new Point((int) x, (int) y);
	}

	public void paintBackground(Graphics2D g2d) {
		g2d.drawImage(game.Arena.arenaImg, 0, 0, null);
		for (int i = 0; i < game.Arena.hitboxes.size(); i++) {
			g2d.setColor(Color.BLACK);
			g2d.fill(game.Arena.hitboxes.get(i));
		}
	}

	public void paintPlayers(Graphics2D g2d) {
		for (int i = 0; i < players.length; i++) {
			Sprite c = players[i];
			Point p = translateCoordinates(c.x, c.y, players[i]);
			int x = p.x;
			int y = p.y;
			int w = c.width;
			int h = c.height;
			if (players[i].dir != true) { // root must be here...
				x = p.x + c.width;
				w = -c.width;
			}

			if (players[i].crouch == true) {
				BufferedImage t = c.img.getSubimage(0, 0, c.width, c.height * 3 / 4);
				g2d.drawImage(t, x, y, t.getWidth(), t.getHeight(), null);
			}

			else
				g2d.drawImage(c.img, x, y, w, h, null);

			x = p.x;
			w = c.width;

			if (players[i].checkHit() == true) {
				g2d.setColor(new Color(255, 0, 0, 128));
				g2d.fillRect(x, y, w, h); // <--- Problem is this statement
											// (fixed)
				System.out.println(x + ", " + y + "-----" + w + "*" + h);
			}
		//	g2d.fill(players[i].hitbox);
		}
	}

	void paintMissiles(Graphics2D g2d) {
		for (int i = 0; i < Arena.missiles.size(); i++) {
			Missile m = Arena.missiles.get(i);
			Point p = translateCoordinates(m.x, m.y, m);
			int x = p.x;
			int y = p.y;
			int w = (int) m.width;
			int h = (int) m.height;
			if (m.dir == false) {
				x = x + w;
				w = -w;
			}
			g2d.drawImage(m.img, x, y, w, h, null);
			// g2d.fill(m.hitbox);
		}
	}

	void paintScores(Graphics2D g2d) {
		g2d.setColor(Color.RED);
		g2d.setFont(new Font("Arial", Font.BOLD, 20));
		g2d.drawString("Health:", 10, 20);
		for (int i = 0; i < players.length; i++) {
			g2d.drawString(players[i].name + ": " + players[i].health, 10, 40 + i * 20);
		}
	}

	void paintTrail(Graphics2D g2d) {
		for (int z = 0; z < players.length; z++) {
			trail.add(translateCoordinates(players[z].x, players[z].y, players[z]));	
			for (int i = 0; i < trail.size(); i++) {
				g2d.setColor(Color.RED);
				g2d.fillRect(trail.get(i).x, trail.get(i).y, 2, 2);
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		Graphics2D g2d = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);

		paintBackground(g2d);
		paintPlayers(g2d);
		paintMissiles(g2d);
		paintScores(g2d);
		paintTrail(g2d);

	}
}
