package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.List;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class PanelSearch extends JPanel {

	public JPanel panel = new JPanel();
	
	public JToggleButton btn_character_search = new JToggleButton("");
	public JToggleButton btn_random_character = new JToggleButton("");
	public JToggleButton btn_search_vehicle = new JToggleButton("");
	public JToggleButton btn_search_planet = new JToggleButton("");
	public JToggleButton btn_search_spaceship = new JToggleButton("");
	public JToggleButton btn_clear_all = new JToggleButton("");
	public JTextField textField = new JTextField();
	
	public JPanel panel_button_box = new JPanel();
	public JLabel lblSwapiSearch = new JLabel("SWapi Search");
	public JButton btn_search = new JButton("");
	
	
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
		panel_1.setLayout(new MigLayout("", "[551px][280.00px]", "[100px]"));
		
		textField.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		panel_1.add(textField, "cell 0 0,grow");
		textField.setColumns(10);
		
		
		btn_search.setIcon(new ImageIcon(PanelSearch.class.getResource("/Resources/img/tick.png")));
		btn_search.setToolTipText("Search");
		panel_1.add(btn_search, "cell 1 0,grow");
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 340, 849, 305);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(0, 0, 757, 305);
		panel_3.add(textPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(762, 0, 87, 305);
		panel_3.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnNewButton_2 = new JButton("New button");
		panel_2.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel_2.add(btnNewButton_3);
		
		JButton btnNewButton_1 = new JButton("New button");
		panel_2.add(btnNewButton_1);

	}
}
