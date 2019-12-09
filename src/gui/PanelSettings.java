package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;

/**
 * Create the basic Settings Panel
 * This panel has been made as a separate class purely for visual reasons. 
 * I used a tool that would allow me to preview panels but it only worked if panels had there own classes.
 * 
 * @author Mark
 *
 */
public class PanelSettings extends JPanel {
	public JButton btn_theme = new JButton("     Theme");
	public JPanel panel = new JPanel();
	public JToggleButton btn_debug = new JToggleButton("     Debug Mode");
	public JToggleButton btn_threads = new JToggleButton("     Allow Threads");
	public JButton btn_folder = new JButton("     Open SWapi");
	private final JPanel panel_1 = new JPanel();
	/**
	 * Create the panel.
	 */
	public PanelSettings() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		
		panel.setBackground(Color.ORANGE);
		add(panel);
		panel.setLayout(null);
		panel_1.setBounds(21, 33, 377, 589);
		
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		panel_1.add(btn_theme);
		btn_theme.setIcon(new ImageIcon(PanelSettings.class.getResource("/Resources/img/palette.png")));
		panel_1.add(btn_debug);
		btn_debug.setIcon(new ImageIcon(PanelSettings.class.getResource("/Resources/img/ladybug.png")));
		panel_1.add(btn_threads);
		btn_threads.setIcon(new ImageIcon(PanelSettings.class.getResource("/Resources/img/needle.png")));
		btn_folder.setIcon(new ImageIcon(PanelSettings.class.getResource("/Resources/img/folder.png")));
		panel_1.add(btn_folder);

	}
}
