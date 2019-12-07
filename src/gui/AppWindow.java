package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AppWindow extends JFrame {

	private JPanel contentPane;
	JPanel panel_main = new JPanel();
	JPanel panel_blue = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow frame = new AppWindow();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1129, 675);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[297.00px][788.00px]", "[630px]"));
		
		JPanel panel_side = new JPanel();
		panel_side.setBackground(Color.BLACK);
		panel.add(panel_side, "cell 0 0,grow");
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_main.removeAll();
				panel_main.repaint();
				panel_main.revalidate();
				panel_main.add(panel_blue);
				panel_main.repaint();
				panel_main.revalidate();
			}
		});
		panel_side.setLayout(new GridLayout(0, 1, 0, 0));
		panel_side.add(btnNewButton_1);
		
		JButton button_1 = new JButton("New button");
		panel_side.add(button_1);
		
		JButton btnNewButton = new JButton("New button");
		panel_side.add(btnNewButton);
		
		JButton button = new JButton("New button");
		panel_side.add(button);
		panel.add(panel_main, "cell 1 0,grow");
		panel_main.setLayout(new GridLayout(1, 0, 0, 0));
		
		
		panel_blue.setBackground(Color.BLUE);
		panel_blue.setBounds(0, 0, 714, 549);
		//panel_main.add(panel_blue);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		panel_1.setBounds(0, 0, 714, 549);
		//panel_main.add(panel_1);
	}
}
