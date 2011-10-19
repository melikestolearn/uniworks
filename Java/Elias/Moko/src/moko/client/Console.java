package moko.client;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Console extends JPanel implements ActionListener {
	
	private final JTextArea textArea;
	
	private final JTextField textField;
	
	private final JFrame frame;
	
	public Console(String title) {
		super(new GridBagLayout());		
		
		textArea = new JTextArea(600, 300);
		textArea.setEditable(false);
		textField = new JTextField(300);
		textField.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridwidth = GridBagConstraints.REMAINDER;
		c2.fill = GridBagConstraints.BOTH;
        c2.weightx = 1.0;
        c2.weighty = 1.0;
        add(scrollPane, c2);
        
		GridBagConstraints c1 = new GridBagConstraints();
        c1.gridwidth = GridBagConstraints.REMAINDER;
        c1.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c1);
        

		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.add(this);
		frame.setBounds(0, 0, 600, 400);
		
		Color backColor = Color.BLACK;
		Color fontColor = new Color(50, 205, 50);
		Color fontFieldColor = Color.WHITE;
		textArea.setBackground(backColor);
		textArea.setForeground(fontColor);
		textField.setBackground(backColor);
		textField.setForeground(fontFieldColor);
		
		frame.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		synchronized(textField) {
			textField.notifyAll();
		}
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public JTextField getTextField() {
		return textField;
	}
}
