package graphics;
import java.awt.*;
import javax.swing.*;

import game.Sprite;


public class Window extends JFrame {
	public static int width = 1920;
	public static int height = 1080;
	public Window(Display display) {
		setTitle("Jumping Game");
		setSize(1920, 1120);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(display);
		setVisible(true);
	}	
}