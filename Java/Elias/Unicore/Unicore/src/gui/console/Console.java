package gui.console;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import main.Base;
import console.ConsoleHead;

/**
 *
 */
public class Console extends JFrame {
    
	private JTextArea consoleArea;
    private JScrollPane jScrollPane1;
    
    
	private final Base base;
	private final ConsoleHead consoleHead;
	
	private int lastCommandPosition = 0;
	
	private boolean inputOn = false;
    
    /** Creates new form Console */
    public Console(ConsoleHead con) {
    	initComponents();
    	
    	consoleHead = con;
    	base = con.getBase();
    	base.regGui(this);
    	consoleHead.regGui(this);
    	
    	printAt(consoleHead.getUserPrompt(), false);
    	
    	setVisible(true);
    }
    
    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        consoleArea = new JTextArea();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Console");
        setResizable(false);
        
        consoleArea.setLineWrap(true);
        //consoleArea.setWrapStyleWord(true); Maybe wrap on whitespaces
        
        consoleArea.setBackground(Color.BLACK);
        consoleArea.setForeground(Color.WHITE);
        consoleArea.setSelectedTextColor(Color.BLACK);
        consoleArea.setSelectionColor(Color.WHITE);
        consoleArea.setCaretColor(Color.WHITE);
        
        
        jScrollPane1.setName("jScrollPane1");
        
        consoleArea.setName("consoleArea");
        jScrollPane1.setViewportView(consoleArea);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
        
        consoleArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
					consoleKeyTyped(evt);
            }
        });
    }
    
    /** The handler for keys typed.
     * @param evt The KeyEvent occurred.
     */
	private void consoleKeyTyped(KeyEvent evt){
		if(evt.getKeyCode()==KeyEvent.VK_CONTROL)
			return;
		if(consoleArea.getCaretPosition()<lastCommandPosition)
			consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
		if(evt.getKeyCode()==KeyEvent.VK_ENTER) {
    		String input = inputHandler();
    		if(inputOn) {
    			PrintWriter w = new PrintWriter(new OutputStreamWriter(base.getConnector().getConsoleOutput()));
    			w.println(input);
    			w.flush();
    		}
    		else
    			execute(input);
    		return;
    	}
		if(evt.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
			if(consoleArea.getDocument().getLength()-lastCommandPosition<1)
				evt.consume();
		}
    }

	/** Reads a line from the Console.
	 * @return The line read.
	 */
	private String inputHandler() {
		String line = null;
		
		try {
			line = consoleArea.getText(lastCommandPosition, consoleArea.getDocument().getLength()-lastCommandPosition);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
		
		lastCommandPosition = consoleArea.getDocument().getLength();
		
		System.out.println(line);	//@REMOVE
		
		return line;
	}
	
	private void execute(String command) {
		consoleHead.exe(command.split("\\s+"));
	}
	
	/** Reads one line from the Console, in real time.
	 * Blocks and waits for the user to press enter.
	 * @return The line typed.
	 * @throws IOException If an io error occurs.
	 */
	public synchronized String read() throws IOException {
		inputOn = true;
		String in = new BufferedReader(new InputStreamReader(base.getConnector().getConsoleInput())).readLine();
		inputOn = false;
		return in;
	}
	
	/** Prints a String on the Console.
	 * Set to print linefeed or not.
	 * @param output The String to print.
	 * @param linefeed True to append linefeed, false to not.
	 */
	public synchronized void printAt(String output, boolean linefeed) {
		if(linefeed)
			consoleArea.append(output +"\n");
		else
			consoleArea.append(output);
		lastCommandPosition = consoleArea.getDocument().getLength();
		consoleArea.setCaretPosition(lastCommandPosition);
	}
	
	/** Not working yet. */
	public synchronized void printPrompt() {
		
		consoleArea.setForeground(Color.GREEN);
		consoleArea.append(consoleHead.getUserPrompt());
		consoleArea.setForeground(Color.WHITE);
		
		lastCommandPosition = consoleArea.getDocument().getLength();
		consoleArea.setCaretPosition(lastCommandPosition);
	}
}
