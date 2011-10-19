package main;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Main extends JFrame implements KeyListener {

	private Playground canvas;
	
	private boolean move = true;
	
	public Main() {

		super("game");

		canvas = new Playground();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 400);
		
		Container container = getContentPane();
		
		container.add(canvas);
		
		setVisible(true);
		
		addKeyListener(this);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
		//canvas.paint(frame.getGraphics());
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP)
			canvas.moveVertikal(-1);
		else if(e.getKeyCode()==KeyEvent.VK_DOWN)
			canvas.moveVertikal(1);
		canvas.repaint();
	}

	public void keyReleased(KeyEvent e) {
		
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	

}
