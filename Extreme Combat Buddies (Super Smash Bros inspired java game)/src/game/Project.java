package game;
import javax.swing.SwingUtilities;

import graphics.Window;

public class Project {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				Window window = new Window();
			}
			
		});
	}

}


/*
 ***EXTREME COMBAT BUDDIES- (Super Smash Bros inspired Java game)***
 * GUIS, Character select, attack types, heal, coins, movement mechanics, etc. 
 * add documentation such as version etc.
 * 
 */


