package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.SystemColor;

/**
 * Create he basic Display Panel
 * This panel has been made as a separate class purely for visual reasons. 
 * I used a tool that would allow me to preview panels but it only worked if panels had there own classes.
 * 
 * @author Mark
 *
 */
public class PanelDisplay extends JPanel {
	
	public JButton btn_table = new JButton("Display Popout Basic Table");
	/**
	 * Create the panel.
	 */
	public PanelDisplay() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		add(panel);
		panel.setLayout(null);
		
		
		btn_table.setBounds(10, 23, 391, 130);
		panel.add(btn_table);

	}
}
