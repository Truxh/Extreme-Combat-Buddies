package game;

import graphics.ArenaReader;
import graphics.Display;
import graphics.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Executer {
	
	private static void setupGraphics(Display d) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Window wndw = new Window(d);
			}
		});
	}
	
	private static Sprite[] setupPlayers(int amount) {
		Sprite[] players = new Sprite[amount];
		for(int i = 0; i < amount; i++) {
			if(i == 0) {
				players[i] = new Sprite(i, ("Muffin Head" + " " + i), new ControlSet(37, 39, 32, 16, 38, 40, 86, 66));
			}
			else if(i == 1) {
				players[i] = new Sprite(i, ("Muffin Head" + " " + i), new ControlSet(65, 68, 67, 17, 87, 83, 90, 88));
			}
			else players[i] = new Sprite(i, ("Muffin Head" + " " + i), new ControlSet(null, true));
		}
		return players;
	}
	
	private static void startGame() {
		ArenaReader ar = new ArenaReader();
		Arena arena = new Arena();
		game.Missile.readMissile();
	}
	
	public static void main(String args[]) {
		Sprite[] players = setupPlayers(4);
		Display display = new Display(players);
		startGame();
		setupGraphics(display);
		GameTimer gt = new GameTimer(players, display);
		
	}


}


/*
TODO: 
-Change the timer interval, fix hardcoded time increases
-(Quantum Tunneling) http://gamedev.stackexchange.com/questions/18604/how-do-i-handle-collision-detection-so-fast-objects-are-not-allowed-to-pass-thro
-Fix glitch/bug where player can run past wall/jump through wall due to updates not being frequent enough. Use last pt/current pt to draw line and check for floor intersect
-current point is not being updated correctly, last pt = current. SHIT!
-draw image/things to scale, change scale factor
-check player hit with another player
-crouch implementation
-user customizable control schemes
-death animation/effect
-better walking animation
-sounds
-borders (hit detect)
-menu 
-character select
-ECB
-Sprite can only walk on TOP of the floors
-AI for players who aren't 1 || 2
-fix score counter for burst 
*/