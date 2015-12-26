package graphics;
import java.awt.*;
import javax.swing.*;

public class Window extends JFrame {
	public Window() {
		//Display display = new Display(); 
		setTitle("Extreme Combat Buddies (Dev Build: 1)");
		setSize(1920, 1080);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//add(display);
		setVisible(true);
		
		MainMenu mm = new MainMenu();
		add(mm);
	}	
}