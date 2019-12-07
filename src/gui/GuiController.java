package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import console.*;

public class GuiController {

	public ButtonGroup seach_panel_buttons;
	public List<JToggleButton> toggle = new ArrayList<>();
	JButton selected_button = new JButton();
	JToggleButton panel_search_temp = new JToggleButton();

	public PanelSearch panel_search = new PanelSearch();
	public PanelDashboard panel_dashboard = new PanelDashboard();
	public PanelExport panel_export = new PanelExport();
	public PanelSettings panel_settings = new PanelSettings();
	public PanelDisplay panel_display = new PanelDisplay();
	public PanelAbout panel_about = new PanelAbout();

	public String searchCommand;

	public MenuActions menuactions = new MenuActions();


	public void buttonDefaults(JButton button) {
		button.setOpaque(false);
		button.setForeground(Color.ORANGE);
		button.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setBorder(null);
		button.setBackground(Color.BLACK);
		button.repaint();
		button.revalidate();
	}




	/**
	 * Set the dashboard button to be selected
	 * @param button
	 */
	public void dashboardStart(JButton button) {
		button.setOpaque(false);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		button.setFocusPainted(false);
		button.setContentAreaFilled(true);
		button.setBorder(null);
		button.setBackground(Color.ORANGE);
	}

	/**
	 * Used to set the hover sequence for the side menu
	 * @param button - to set hover control
	 */
	public void mouseHover(JButton button){
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(selected_button.getText() != button.getText()) {
					button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					button.setOpaque(false);
					button.setForeground(Color.BLACK);
					button.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
					button.setFocusPainted(false);
					button.setContentAreaFilled(true);
					button.setBorder(null);
					button.setBackground(Color.ORANGE);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if(selected_button.getText() != button.getText()) {
					button.setOpaque(false);
					button.setForeground(Color.ORANGE);
					button.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
					button.setFocusPainted(false);
					button.setContentAreaFilled(false);
					button.setBorder(null);
					button.setBackground(Color.BLACK);
				}
			}


		});


	}

	public void buttonRepaint(JButton button) {
		button.setOpaque(false);
		button.setForeground(Color.ORANGE);
		button.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setBorder(null);
		button.setBackground(Color.BLACK);
	}

	/**
	 * Used to control Side Menu buttons
	 * @param button
	 * @param panel_to_refresh - (Usually the main panel)
	 * @param panel_to_add - (Which panel do you want to add to main)
	 */
	public void menuButtonPress(JButton button, JPanel panel_to_refresh, JPanel panel_to_add){

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected_button != button) {
					buttonRepaint(selected_button);

					panel_to_refresh.removeAll();
					panel_to_refresh.repaint();
					panel_to_refresh.revalidate();
					panel_to_refresh.add(panel_to_add);
					panel_to_refresh.repaint();
					panel_to_refresh.revalidate();
					button.setContentAreaFilled(true);
					selected_button = button;
				}
				System.out.println("Selected button is "+selected_button.getText());

			}
		});
	}


	/**
	 * Set design of buttons 
	 * @param button
	 */
	public void buttonDraw(JButton button, Color forColor, Color backColor, int font_size) {
		//button.setOpaque(false);
		button.setForeground(forColor);
		button.setFont(new Font("Segoe UI Black", Font.PLAIN, font_size));
		button.setFocusPainted(false);
		button.setContentAreaFilled(true);
		button.setBorder(null);
		button.setBackground(backColor);
		button.repaint();
		button.revalidate();
	}


	//==============================================================================================================
	//SEARCH PANEL CONTROLS
	//==============================================================================================================

	/**
	 * 
	 * @param button
	 */
	public void searchPanelToggleButtons(JToggleButton button, JLabel label) {
		button.setBackground(Color.BLACK);
		button.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		button.setFocusPainted(false);
		button.setContentAreaFilled(true);

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				label.setText(button.getToolTipText());
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				label.setText("SWapi Search");
			}
		});
	}

	public void spButtonListener(JButton button) {


		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
	}

	public void searchPanelButtonController(JToggleButton button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				panel_search_temp = button;
				resetSearchToggles();
				button.setSelected(true);

				if(button == panel_search.btn_clear_all) {
					System.out.println("Clearing all settings");
					resetSearchToggles();
				}
			}
		});
	}
	
	
	
	public void spButtonActions(JButton button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if(button == panel_search.btn_search) {
					String s = panel_search.textField.getText();
					System.out.println("string is "+s);
					if(s.equals(null) || s.equals("") || s.equals(" ")) {
						System.out.println("Empty");
						dialogWarning("You need to enter a phrase to search", "Enter some Text");
					}
					else {
						panel_search.textField.setText(null);
						System.out.println("Launching");

						toggle.forEach(button -> {
							if(button.isSelected() == true) {
								System.out.println("Im selected "+button.getToolTipText());
								searchCommand = button.getToolTipText();
							}
						});
						if(searchCommand.equals("Character Search")) {
						menuactions.app.swapiCharacterSearch(s, null, "search_by_name");
						}
						else {
							dialogWarning("Theres no Toggles selected", "No Toggle Selected");
						}
					}
				}
			}
		});
	}


	/**
	 * Use a loop to add button commands for all buttons
	 */
	public void createSearchPanel() {

		toggle.add(panel_search.btn_character_search);
		toggle.add(panel_search.btn_random_character);
		toggle.add(panel_search.btn_search_vehicle);
		toggle.add(panel_search.btn_search_planet);
		toggle.add(panel_search.btn_search_spaceship);
		toggle.add(panel_search.btn_clear_all);

		toggle.forEach(button -> {
			searchPanelToggleButtons(button, panel_search.lblSwapiSearch);
			searchPanelButtonController(button);
		});

		buttonDraw(panel_search.btn_search, Color.black, new Color(51, 153, 102), 0);
		spButtonListener(panel_search.btn_search);
		spButtonActions(panel_search.btn_search);

	}




	public void resetSearchToggles() {
		toggle.forEach(button -> {
			button.setSelected(false);
			button.repaint();
			button.revalidate();
		});
	}

	
	
public void StartUpCheck() {
		
	menuactions.app.createDir();
		
		try {
			URL url = new URL("https://swapi.co/");
			URLConnection connection = url.openConnection();
			connection.connect();
			App.networkConnected = true; 	
			App.clearLogs();
			

		} catch (MalformedURLException e) {
			App.networkError();
		} catch (IOException e) {
			App.networkError();
		}
	}

public static void dialogWarning(String message, String title) {
	
	JOptionPane.showMessageDialog(AppWindow.frame,
			message,
			title,
			JOptionPane.ERROR_MESSAGE);
}

}