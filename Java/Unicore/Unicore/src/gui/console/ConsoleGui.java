package gui.console;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import main.Base;
import console.Console;

/**
 *
 */
public class ConsoleGui extends JFrame {
    
	private int lastCommandPosition = 0;
	
	private final Base base;
	
	private final Console console;
	
	private JTextArea consoleArea;
    private JScrollPane jScrollPane1;
    
    /** Creates new form Console */
    public ConsoleGui(Console con) {
    	initComponents();
    	console = con;
    	base = con.getBase();
    	base.regGui(this);
    	console.regGui(this);
    	printAt(console.getPrompt(), true);
    	
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
	private void consoleKeyTyped(KeyEvent evt){
		if(consoleArea.getCaretPosition()<lastCommandPosition)
			consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
		if(evt.getKeyCode()==KeyEvent.VK_ENTER) {
    		inputHandler();
    	}
		else if(evt.getKeyCode()==KeyEvent.VK_BACK_SPACE)
			if(consoleArea.getDocument().getLength()-lastCommandPosition<1)
				evt.consume();
    }

	private void inputHandler() {
		String line = null;
		
		try {
			line = consoleArea.getText(lastCommandPosition, consoleArea.getDocument().getLength()-lastCommandPosition);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
		
		lastCommandPosition = consoleArea.getDocument().getLength();
		
		System.out.println(line);	//@REMOVE
		
		console.exe(line.split("\\s+"));
	}
	public synchronized void printAt(String output, boolean isKey) {
		if(isKey)
			consoleArea.append(output);
		else
			consoleArea.append(output +"\n");
		lastCommandPosition = consoleArea.getDocument().getLength();
		consoleArea.setCaretPosition(lastCommandPosition);
	}
}
