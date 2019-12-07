package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;

public class PanelAbout extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelAbout() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(141, 113, 217, 130);
		panel.add(btnNewButton);

	}
}
