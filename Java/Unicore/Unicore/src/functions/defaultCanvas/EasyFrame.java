/* This Frame was created with Netbeans v7.0.1 */
package functions.defaultCanvas;

import java.awt.Canvas;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/** A simple frame with a Canvas.
 * Supports different resolutions plus fullscreen.
 * Use "Esc" to exit fullscreen.
 */
public class EasyFrame extends JFrame {
	
	/** The frame canvas. */
	private final Canvas mainCanvas;
	
	/** Boolean that indicates if fullscreen is enable. */
	private boolean isFullscreen = false;
	
	/** The current frame width. */
	private int frameWidth;
	/** The current frame height. */
	private int frameHeigth;
	
    /** Creates new form MainFrame with the default size [1024x768].
     * @param myCanvas A custom Canvas to paint.
     * @param fullscreen Choose if to display in fullscreen.
     */
    public EasyFrame(Canvas myCanvas, boolean fullscreen) {
        mainCanvas = myCanvas;
        frameWidth = 1024;
        frameHeigth = 768;
        
    	initComponents();   
        
    	setFullScreen(fullscreen);
    }

    /** Creates new form MainFrame with custom size.
     * @param myCanvas A custom Canvas to paint.
     * @param fullscreen Choose if to display in fullscreen.
     * @param width The frame width.
     * @param height The frame height.
     */
    public EasyFrame(Canvas myCanvas, boolean fullscreen, int width, int heigth) {
        mainCanvas = myCanvas;
        frameWidth = width;
        frameHeigth = heigth;
        
        initComponents();        
        setFullScreen(fullscreen);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     */
    private void initComponents() {
    	
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        mainCanvas.setBackground(new java.awt.Color(0, 0, 0));
        mainCanvas.setForeground(new java.awt.Color(255, 255, 255));
        mainCanvas.setName("mainCanvas");
        
        mainCanvas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mainCanvasKeyPressed(evt);
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainCanvas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, frameWidth, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainCanvas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, frameHeigth, Short.MAX_VALUE)
        );

        pack();
        
        setVisible(true);
    }

    /** Set or exit fullscreen.
     * Has no effect if the old and new state are the same.
     * @param bool True to set, false to exit fullscreen.
     */
    public void setFullScreen(boolean bool) {
    	
    	if(bool==isFullscreen)
    		return;
		
		final GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		final DisplayMode dm = graphicsDevice.getDisplayMode();
		
		if(bool) {
			if(graphicsDevice.isFullScreenSupported())
				mainCanvas.setSize(dm.getWidth(), dm.getHeight());
			else
				JOptionPane.showMessageDialog(null, "FullScreen is not supported.");
			dispose();
			setUndecorated(true);
			graphicsDevice.setFullScreenWindow(this);
			setResizable(false);
			setVisible(true);
		}
		else {
			dispose();
			mainCanvas.setSize(frameWidth, frameHeigth);
			setUndecorated(false);
			graphicsDevice.setFullScreenWindow(null);
			setResizable(true);
			setVisible(true);
		}
		mainCanvas.paint(mainCanvas.getGraphics());
		isFullscreen = bool;
    }
    
    /** The Keylistener.
     * Only the escape key is implemented.
     * Exits fullscreen.
     * @param evt The KeyEvent.
     */
    private void mainCanvasKeyPressed(java.awt.event.KeyEvent evt) {
    	if(isFullscreen && evt.getKeyCode()==KeyEvent.VK_ESCAPE) {
    		setFullScreen(false);
    	}
    }
}
