package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;

public class PanelDisplay extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelDisplay() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(141, 113, 217, 130);
		panel.add(btnNewButton);

	}
}
