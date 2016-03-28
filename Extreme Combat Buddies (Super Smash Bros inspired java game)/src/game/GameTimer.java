package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import graphics.Display;

public class GameTimer implements ActionListener{
	private Timer gameTimer;
	public static int gameTime = 0;
	public static int increment = 10;
	Display display;
	Sprite[] sprites;
	GameTimer(Sprite[] sprites, Display d) {
		initTimer();
		this.display = d;
		this.sprites = sprites;
	}
	
	private void initTimer() {
		gameTimer = new Timer(increment, this); //10
		gameTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameTime += increment; //10 
		display.repaint();
		if(gameTime % 1000 == 0) {
			System.out.println("Game time: " + gameTime/1000 + " seconds");
		}
		for(int i = 0; i < sprites.length; i++) {
			sprites[i].update(gameTime, increment);
		}
		for(int i = 0; i < Arena.missiles.size(); i++) {
			Arena.missiles.get(i).update();
		}
		
	}
}
