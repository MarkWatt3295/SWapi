package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

/**
 * Create the basic search Panel
 * This panel has been made as a separate class purely for visual reasons. 
 * I used a tool that would allow me to preview panels but it only worked if panels had there own classes.
 * 
 * @author Mark
 *
 */
public class PanelSearch extends JPanel {

	public JPanel panel = new JPanel();
	
	public JToggleButton btn_character_search = new JToggleButton("");
	public JToggleButton btn_random_character = new JToggleButton("");
	public JToggleButton btn_search_vehicle = new JToggleButton("");
	public JToggleButton btn_search_planet = new JToggleButton("");
	public JToggleButton btn_search_spaceship = new JToggleButton("");
	public JToggleButton btn_clear_all = new JToggleButton("");
	
	public JButton btn_save_results = new JButton("");
	public JButton btn_copyClipboard = new JButton("");
	public 	JButton btn_clear = new JButton("");
	
	
	public JTextField textField = new JTextField();
	public JComboBox comboBox = new JComboBox();
	
	public JPanel panel_button_box = new JPanel();
	public JLabel lblSwapiSearch = new JLabel("SWapi Search");
	public JButton btn_search = new JButton("");
	public JTextArea textArea = new JTextArea();
	public JScrollPane scrollPane = new JScrollPane();
	
	
	/**
	 * Create the panel.
	 */
	public PanelSearch() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		
		panel.setBackground(Color.ORANGE);
		add(panel);
		panel.setLayout(null);
		
		
		lblSwapiSearch.setBackground(Color.WHITE);
		lblSwapiSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSwapiSearch.setForeground(Color.BLACK);
		lblSwapiSearch.setFont(new Font("Segoe UI Black", Font.PLAIN, 60));
		lblSwapiSearch.setBounds(0, 0, 869, 88);
		panel.add(lblSwapiSearch);
		
		
		panel_button_box.setBounds(10, 96, 849, 122);
		panel.add(panel_button_box);
		panel_button_box.setLayout(new GridLayout(0, 6, 0, 0));
		
		//BUTTONS
		//=====================================================================================================
		btn_character_search.setToolTipText("Character Search");
		btn_character_search.setIcon(new ImageIcon(PanelSearch.class.getResource("/Resources/img/new_character_search_1.png")));
		panel_button_box.add(btn_character_search);
		
		btn_random_character.setToolTipText("Random Character Search");
		btn_random_character.setIcon(new ImageIcon(PanelSearch.class.getResource("/Resources/img/random_character_search.png")));
		panel_button_box.add(btn_random_character);
		
		
		btn_search_vehicle.setIcon(new ImageIcon(PanelSearch.class.getResource("/Resources/img/car.png")));
		btn_search_vehicle.setToolTipText("Vehicle Search");
		panel_button_box.add(btn_search_vehicle);
		
		
		btn_search_planet.setIcon(new ImageIcon(PanelSearch.class.getResource("/Resources/img/planet.png")));
		btn_search_planet.setToolTipText("Planet Search");
		panel_button_box.add(btn_search_planet);
		
		btn_search_spaceship.setIcon(new ImageIcon(PanelSearch.class.getResource("/Resources/img/space_2.png")));
		btn_search_spaceship.setToolTipText("Spaceship Search");
		panel_button_box.add(btn_search_spaceship);
		
		btn_clear_all.setIcon(new ImageIcon(PanelSearch.class.getResource("/Resources/img/remove_all.png")));
		btn_clear_all.setToolTipText("Clear All");
		panel_button_box.add(btn_clear_all);
		//=====================================================================================================
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 229, 849, 100);
		panel.add(panel_1);
		panel_1.setLayout(null);
		textField.setBounds(7, 7, 551, 48);
		
		textField.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		panel_1.add(textField);
		textField.setColumns(10);
		btn_search.setBounds(562, 7, 280, 89);
		
		
		btn_search.setIcon(new ImageIcon(PanelSearch.class.getResource("/Resources/img/tick.png")));
		btn_search.setToolTipText("Search");
		panel_1.add(btn_search);
		
		
		comboBox.setBounds(7, 55, 551, 41);
		panel_1.add(comboBox);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 340, 849, 305);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(740, 0, 109, 305);
		panel_3.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		btn_save_results.setIcon(new ImageIcon(PanelSearch.class.getResource("/Resources/img/floppy-disk.png")));
		btn_save_results.setToolTipText("Save Results");
		panel_2.add(btn_save_results);
		
		
		btn_copyClipboard.setIcon(new ImageIcon(PanelSearch.class.getResource("/Resources/img/copy.png")));
		btn_copyClipboard.setToolTipText("Copy To Clip Board");
		panel_2.add(btn_copyClipboard);
		
	
		btn_clear.setIcon(new ImageIcon(PanelSearch.class.getResource("/Resources/img/delete.png")));
		btn_clear.setToolTipText("Clear Text");
		panel_2.add(btn_clear);
		scrollPane.setBounds(0, 0, 741, 305);
		
		panel_3.add(scrollPane);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 22));
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setWrapStyleWord(true);

	}
}
