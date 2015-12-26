package graphics;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Display extends JPanel implements ActionListener{
	Timer timer;
	Display() {
	initTimer();
	
	
	}
	
	
	public void initTimer() {
		timer = new Timer(10, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		setBackground(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;
	
		}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
	
}
