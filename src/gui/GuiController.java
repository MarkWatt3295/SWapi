package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

	public String searchCommand; //The command to pass to search panel switch
	public String fullprint = ""; //Holds texpane print

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
	 * Create same standardised buttons. 
	 * @param button - Button to draw
	 * @param forColor - Foreground colour (text)
	 * @param backColor - Background colour
	 * @param font_size - Size of the font on text
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
	 * Controls for the Toggle button on SP (Search Panel)
	 * @param button - The toggle button
	 * @param label - Label on SP used for button titles
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


	//CONTROLS AND LISTENERS FOR NORMAL SP BUTTONS
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

	/**
	 * Toggle button ACTION listeners
	 * @param button
	 */
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

				else if(button == panel_search.btn_random_character) {
					System.out.println("Random Character button pressed");

					randomConfirmation();


				}
			}


		});
	}

	private void randomConfirmation() {
		Object[] options = {"Yes Please!", "Maybe Later"};

		ImageIcon icon = createImageIcon("/Resources/img/jango.png");
		int n = JOptionPane.showOptionDialog(AppWindow.frame,
				"Would you like to search for a Random Character?",
				"Random Character Search Confirmation",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				icon,
				options,
				options[1]);

		if (n == JOptionPane.YES_OPTION) {
			int r;
			System.out.println("The Force is selecting a Character for you...");

			if(App.character_count > 0) {

				r = App.getRandomNumberInRange(1, App.character_count);
				System.err.println("R is "+r);
				menuactions.app.swapiCharacterSearch(null, Integer.toString(r), "search_by_number");
				textDraw();
				resetSearchToggles();
			}
			else {
				r = App.getRandomNumberInRange(1, 87);
				System.err.println("R is "+r);
				menuactions.app.swapiCharacterSearch(null, Integer.toString(r), "search_by_number");
				textDraw();
				resetSearchToggles();
			}

		}
		else if (n == JOptionPane.NO_OPTION) {
			System.out.println("User said no");

		};
	}



	public void spButtonActions(JButton button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//===================================================================
				//SEARCH BUTTON
				if(button == panel_search.btn_search) {
					toggle.forEach(button -> {
						if(button.isSelected() == true) {
							System.out.println("Im selected "+button.getToolTipText());
							searchCommand = button.getToolTipText();
							System.out.println("Search Command is "+searchCommand);

						}});

					if ((searchCommand != null) && (searchCommand.length() > 0)) {
						String s = panel_search.textField.getText();
						System.out.println("string is "+s);

						if((s != null) && (s.length() > 0)){
							panel_search.comboBox.addItem(s);
							panel_search.textField.setText("");
							panel_search.comboBox.repaint();
							System.out.println("Launching");
							//panel_search.textArea.setText("Parsing Midi-chlorians... This will just be a sec.\n");
							panel_search.textArea.repaint();
							panel_search.textArea.revalidate();

							if ((searchCommand != null) && (searchCommand.length() > 0)) {
								switch (searchCommand) {
								case "Character Search":
									fullprint = "";
									panel_search.textArea.revalidate();
									try {
									menuactions.app.swapiCharacterSearch(s, null, "search_by_name");
									
									textDraw();
									}catch(IllegalArgumentException e1) {
										GuiController.yodaDialog("Invalid search, Try again you must");
										searchClear();
									}
									break;

								default:
									dialogWarning("Theres no Toggles selected", "No Toggle Selected");
									break;
								}
							}
						}
						else{
							System.out.println("Empty");
							dialogWarning("You need to enter a phrase to search", "Enter some Text");
							panel_search.comboBox.addItem(s);
							panel_search.comboBox.repaint();
						}

					}
					else {
						dialogWarning("Theres no Toggles selected", "No Toggle Selected");
					}

					//End Search Button==========================================
				}

				else if(button == panel_search.btn_clear) {
					System.out.println("Clear pressed");
					searchClear();
				}

			}

			private void searchClear() {
				fullprint = "";
				searchCommand = "";
				panel_search.textArea.setRows(0);
				panel_search.textArea.setColumns(0);
				panel_search.textArea.selectAll();
				panel_search.textArea.replaceSelection("");
				panel_search.textArea.repaint();
				panel_search.textArea.revalidate();
				
			}


		});
	}
	private void textDraw() {
		menuactions.app.People.forEach(person -> {

			//person.extraInfo();
			fullprint = fullprint 
					+"\n-------------------------------------------------------\n"
					+"  C H A R A C T E R   I N F O R M A T I O N\n"+
					"-------------------------------------------------------\n"
					+"\nName : "+person.getName()
					+"\nGender : "+person.getGender()
					+"\nSpecies : "+person.getSpecies()
					+"\nHeight : "+person.getHeight()
					+"\nMass : "+person.getMass()
					+"\nHair Colour : "+person.getHair_color()
					+"\nSkin Colour : "+person.getSkin_color()
					+"\nEye Colour : "+person.getEye_color()
					+"\nBirth Year : "+person.getBirthYear()
					+"\n-------------------------------------------------------\n"
					+"\nFilms Appeared In :\n"+ person.getAll_films()
					+"\n\nVehicles used :\n"+ person.getAll_vehicles()+"\n"
					+"-------------------------------------------------------\n";



		});
		panel_search.textArea.setText(fullprint);
		panel_search.textArea.repaint();
		panel_search.textArea.revalidate();

	}

	/**
	 * Use a loop to add button commands for all buttons.
	 * This Method gives each button in PanelSearch its actions and listeners
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
		buttonDraw(panel_search.btn_save_results, Color.black, new Color(0, 153, 255), 0);
		buttonDraw(panel_search.btn_copyClipboard, Color.black, new Color(0, 153, 204), 0);
		buttonDraw(panel_search.btn_clear, Color.black, Color.DARK_GRAY, 0);

		spButtonListener(panel_search.btn_save_results);
		spButtonListener(panel_search.btn_copyClipboard);

		spButtonListener(panel_search.btn_clear);
		spButtonActions(panel_search.btn_clear);

		spButtonListener(panel_search.btn_search);
		spButtonActions(panel_search.btn_search);

	}
	//END SP
	//================================================================




	//===================================================================
	//Display Panels
	public void createDisplayPanel() {
		buttonDraw(panel_display.btn_table, Color.white, Color.BLUE, 20);
		dpButtonListener(panel_display.btn_table);
		dpButtonActions(panel_display.btn_table);
	}

	//CONTROLS AND LISTENERS FOR DP BUTTONS
	public void dpButtonListener(JButton button) {

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


	public void dpButtonActions(JButton button) {
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(button == panel_display.btn_table) {
					if(App.tableShow == false) {
					TableArray.createWindow(menuactions.app.People);
					}
					else {
						TableArray.dialog.show();
					}
				}
			}
		});
	}



	/**
	 * A quick method to set all toggle buttons back to false
	 * {@link }
	 */
	public void resetSearchToggles() {
		toggle.forEach(button -> {
			button.setSelected(false);
			button.repaint();
			button.revalidate();
		});
	}



	public static void StartUpCheck() {

		App.createDir();

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

	public static Image getFrameImage() {
		java.net.URL imgURL = AppWindow.class.getResource("/Resources/img/yoda.png");
		if (imgURL != null) {
			return new ImageIcon(imgURL).getImage();
		} else {
			return null;
		}
	}
	
	
	public static void networkCheck() {


		try {
			URL url = new URL("https://swapi.co/");
			URLConnection connection = url.openConnection();
			connection.connect();
			App.networkConnected = true; 	

		} catch (MalformedURLException e) {
			if(App.networkErrorShow == false) {
				App.networkErrorShow = true;
				App.networkError();
			}
		} catch (IOException e) {
			if(App.networkErrorShow == false) {
				App.networkErrorShow = true;
				App.networkError();
			}
		}
	}
	/**
	 * A gui error message box (with red cross)
	 * @param message - The message to display
	 * @param title - Title of your message
	 */
	public static void dialogWarning(String message, String title) {

		JOptionPane.showMessageDialog(AppWindow.frame,
				message,
				title,
				JOptionPane.ERROR_MESSAGE);
	}

	public static void yodaDialog(String message) {
		ImageIcon icon = createImageIcon("/Resources/img/yoda.png");
		JOptionPane.showMessageDialog(AppWindow.frame,
				message,
				"Yoda Says",
				JOptionPane.INFORMATION_MESSAGE,
				icon);
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	public static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = AppWindow.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create A popup dialog message
	 * @param iconname - the image name e.g. 'alien.png'
	 * @param message - message to display
	 * @param title - title of the message
	 */
	public static void mDialog(String iconname, String message, String title) {
		ImageIcon icon = createImageIcon("/Resources/img/"+iconname);
		JOptionPane.showMessageDialog(AppWindow.frame,
				message,
				title,
				JOptionPane.INFORMATION_MESSAGE,
				icon);
	}

}