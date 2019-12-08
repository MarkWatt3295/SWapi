package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import console.App;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class AppWindow extends JFrame {



	private JPanel contentPane;
	public JPanel panel_main = new JPanel();
	JPanel panel_blue = new JPanel();



	public JButton btn_search = new JButton("SEARCH");
	public JButton btn_dashboard = new JButton("DASHBOARD");
	public JButton btn_display = new JButton("DISPLAY");
	public JButton btn_export = new JButton("EXPORT");
	public JButton btn_settings = new JButton("SETTINGS");
	public JButton btn_about = new JButton("\n");
	public GuiController controller = new GuiController();

	public JPanel panel_side = new JPanel();
	public JPanel panel = new JPanel();
	public static AppWindow frame = new AppWindow();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AppWindow() {
		App.thread.runTask1();
		controller.menuactions.app.using_gui = true;
		controller.StartUpCheck();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1107, 695);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		panel.setBounds(0, 0, 1117, 679);
		panel.setBackground(Color.BLACK);
		contentPane.add(panel);
		panel.setLayout(null);


		panel_side.setBounds(0, 0, 225, 672);
		panel_side.setBackground(Color.BLACK);
		panel.add(panel_side);
		panel_main.add(controller.panel_dashboard);
		panel_side.setLayout(new GridLayout(0, 1, 0, 0));
		sidePanelSetup();
		
		
		//START BUILDING REST OF GUI
		controller.createSearchPanel();
		controller.createDisplayPanel();
		

	}







	public void sidePanelSetup() {

		controller.buttonDefaults(btn_about);
		controller.mouseHover(btn_about);
		controller.menuButtonPress(btn_about, panel_main, controller.panel_about);
		btn_about.setIcon(new ImageIcon(AppWindow.class.getResource("/Resources/img/swapi_heading_4.png")));

		panel_side.add(btn_about);

		controller.buttonDefaults(btn_dashboard);
		controller.mouseHover(btn_dashboard);
		controller.menuButtonPress(btn_dashboard, panel_main, controller.panel_dashboard);
		panel_side.add(btn_dashboard);

		controller.buttonDefaults(btn_search);
		controller.mouseHover(btn_search);
		controller.menuButtonPress(btn_search, panel_main, controller.panel_search);
		panel_side.add(btn_search);

		controller.buttonDefaults(btn_display);
		controller.mouseHover(btn_display);
		controller.menuButtonPress(btn_display, panel_main, controller.panel_display);
		panel_side.add(btn_display);

		controller.buttonDefaults(btn_export);
		controller.mouseHover(btn_export);
		controller.menuButtonPress(btn_export, panel_main, controller.panel_export);
		panel_side.add(btn_export);

		controller.buttonDefaults(btn_settings);
		controller.mouseHover(btn_settings);
		controller.menuButtonPress(btn_settings, panel_main, controller.panel_settings);
		panel_side.add(btn_settings);

		panel_main.setBounds(226, 0, 881, 672);
		panel.add(panel_main);
		panel_main.setLayout(new GridLayout(1, 0, 0, 0));

		controller.selected_button = btn_dashboard;
		controller.dashboardStart(btn_dashboard);
	}


}
