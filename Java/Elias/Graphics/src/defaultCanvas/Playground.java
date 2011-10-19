package defaultCanvas;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/** A custom canvas. */
public class Playground extends Canvas {
	
	private int xPos;
	private int yPos;
	
	private BufferedImage img = null;
	
	public Playground(){
		super();
		//loadImage();
		loadScreen();
	}
	
	public void paint(Graphics graphics) {
		if(img!=null) {
			final int imgW = img.getWidth();
			final int imgH = img.getHeight();
			
			xPos = ((int)getSize().getWidth()-imgW)/2;
			yPos = ((int)getSize().getHeight()-imgH)/2;
			graphics.drawImage(img, xPos, yPos, null);
		}
	}
	
	/** Load an image to diplay.
	 * Opens a filechooser and displays the selected image in the screen center.
	 */
	public void loadImage() {
		final JFileChooser chooser = new JFileChooser("I:\\workspace\\2dGame\\resources");
		chooser.showOpenDialog(this);
		final File fileImg = chooser.getSelectedFile();
		try {
			if(fileImg!=null)
				img = ImageIO.read(fileImg);
		} catch (IOException e) {}
	}
	
	/** Capture the screen and display it. */
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