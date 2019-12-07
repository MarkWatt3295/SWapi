package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class PanelDashboard extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelDashboard() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO SWapi Java");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 60));
		lblNewLabel.setBounds(0, 0, 881, 88);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(PanelDashboard.class.getResource("/Resources/img/dashboard3.png")));
		lblNewLabel_1.setBounds(0, 127, 881, 545);
		panel.add(lblNewLabel_1);

	}
}
