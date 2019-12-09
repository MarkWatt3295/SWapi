package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class PanelExport extends JPanel {
	
public JButton btnCsv = new JButton("     Export as CSV");
public JButton btnExportAsJson = new JButton("     Export as Json");
public JButton btnExportAsTxt = new JButton("     Export as TXT");
public JButton btnImport = new JButton("     Import");
public JButton btnSerialize = new JButton("     Serialize");
	/**
	 * Create the panel.
	 */
	public PanelExport() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 0, 51));
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(97, 78, 714, 514);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		btnCsv.setIcon(new ImageIcon(PanelExport.class.getResource("/Resources/img/csv.png")));
		panel_1.add(btnCsv);
		btnExportAsJson.setIcon(new ImageIcon(PanelExport.class.getResource("/Resources/img/json.png")));
		
		
		panel_1.add(btnExportAsJson);
		btnExportAsTxt.setIcon(new ImageIcon(PanelExport.class.getResource("/Resources/img/txt.png")));
		
		
		panel_1.add(btnExportAsTxt);
		btnSerialize.setIcon(new ImageIcon(PanelExport.class.getResource("/Resources/img/serial.png")));
		
		
		panel_1.add(btnSerialize);
		btnImport.setIcon(new ImageIcon(PanelExport.class.getResource("/Resources/img/import.png")));
		panel_1.add(btnImport);

	}
}
