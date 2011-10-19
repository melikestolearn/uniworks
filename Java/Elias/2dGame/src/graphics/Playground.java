package graphics;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Playground extends Canvas {
	
	private int width;
	private int heigth;
	
	private int xPos;
	private int yPos;
	
	private BufferedImage img = null;
	
	boolean defaultEnabled = true;
	boolean fullscreen;
	
	public Playground(int canvasWidth, int canvasHeigth){
		super();
		width = canvasWidth;
		heigth = canvasHeigth;
		//loadImage();
		loadScreen();
	}
	
	public void loadImage() {
		JFileChooser chooser = new JFileChooser("I:\\workspace\\2dGame\\resources");
		int returnVal = chooser.showOpenDialog(this);
		File fileImg = chooser.getSelectedFile();
		try {
			if(fileImg!=null)
				img = ImageIO.read(fileImg);
		} catch (IOException e) {}
	}

	public void paint(Graphics graphics) {
		if(defaultEnabled && img!=null) {
			int imgW = img.getWidth();
			int imgH = img.getHeight();
			
			Rectangle rec = new Rectangle(img.getWidth(), img.getHeight());

			xPos = (width-imgW)/2;
			yPos = (heigth-imgH)/2;
		}
		//this.getGraphics().drawImage(img, xPos, yPos, null);
		graphics.drawImage(img, xPos, yPos, null);
	}
	
	public void setSize(int width, int height) {
		super.setSize(width, height);
		this.width = width;
		this.heigth = height;
	}
	private void loadScreen() {
		try {
			img = (new Robot()).createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}