package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Playground extends Canvas {

	private int x = 5;
	private int y = 5;
	
	private int speed = 5;
	
	public void paint(Graphics graphics) {
		graphics.setColor(Color.black);
		graphics.fillOval(x, y, 50, 50);
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void moveVertikal(int direction) {
		if(direction<0) {
			y -= speed;
			if(y<5)
				y = 5;			
		}
		else {
			y += speed;
			if(y>310)
				y = 310;				
		}
	}
}
