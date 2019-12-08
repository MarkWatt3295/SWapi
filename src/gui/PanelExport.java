package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;

public class PanelExport extends JPanel {
	
public JButton btnCsv = new JButton("Export as CSV");
	/**
	 * Create the panel.
	 */
	public PanelExport() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(145, 91, 664, 406);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		panel_1.add(btnCsv);
		
		JButton btnExportAsJson = new JButton("Export as Json");
		panel_1.add(btnExportAsJson);
		
		JButton btnExportAsTxt = new JButton("Export as TXT");
		panel_1.add(btnExportAsTxt);
		
		JButton btnSerialize = new JButton("Serialize");
		panel_1.add(btnSerialize);
		
		JButton btnImport = new JButton("Import");
		btnImport.setBounds(147, 518, 217, 130);
		panel.add(btnImport);

	}
}
