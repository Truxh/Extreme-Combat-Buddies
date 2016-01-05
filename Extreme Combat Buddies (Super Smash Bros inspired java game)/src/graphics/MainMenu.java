package graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import characters.TestSprite;
import game.CombatEntity;

public class MainMenu extends JPanel implements ActionListener{
	TestBoard tb;
	MainMenu() {
	JButton start = new JButton("Start Game");
	add(start);
	start.addActionListener(this);
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//whatever menu here
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Start Game")) {
			System.out.println("triggered");
			remove(this);
			tb = new TestBoard();
			add(tb);
			validate();
		    repaint();

		}
	}
	

	
}
