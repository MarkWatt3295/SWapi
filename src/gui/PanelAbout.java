package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


/**
 * Create the basic About Panel.
 * 
 * This panel has been made as a separate class purely for visual reasons. 
 * I used a tool that would allow me to preview panels but it only worked if panels had there own classes.
 * 
 * @author Mark
 *
 */
public class PanelAbout extends JPanel {

	public JTextArea textArea = new JTextArea();
	/**
	 * Create the panel.
	 */
	public PanelAbout() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblAboutSwapiJava = new JLabel("About Swapi Java");
		lblAboutSwapiJava.setHorizontalAlignment(SwingConstants.CENTER);
		lblAboutSwapiJava.setForeground(Color.ORANGE);
		lblAboutSwapiJava.setFont(new Font("Segoe UI Black", Font.PLAIN, 60));
		lblAboutSwapiJava.setBackground(Color.WHITE);
		lblAboutSwapiJava.setBounds(0, 0, 881, 88);
		panel.add(lblAboutSwapiJava);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 861, 535);
		panel.add(scrollPane);
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

	}
}
