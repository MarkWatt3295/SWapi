package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import console.Person;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


/** 
 * This class creates a seperate JFrame which holds a table.
 * The table contains information on all of the people that have been searched in the GUI.
 * @author Mark
 *
 */
public class TableArray {

	public static JDialog dialog = new JDialog(AppWindow.frame,
			"Character Details");


	/**
	 * @wbp.parser.entryPoint
	 */
	public static void createWindow(List<Person> people) { //CREATE the dialog window



		dialog.setFont(new Font("Arial", Font.PLAIN, 14));
		dialog.setIconImage(GuiController.getFrameImage());
		dialog.setTitle("Total Character Count : "+people.size());


		DefaultTableModel model = new DefaultTableModel();


		JTable table = new JTable(model){

			public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
				Component returnComp = super.prepareRenderer(renderer, row, column);
				Color alternateColor = new Color(194,220,246);
				Color whiteColor = new Color(238,243,247);
				if (!returnComp.getBackground().equals(getSelectionBackground())){
					Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
					returnComp .setBackground(bg);
					bg = null;
				}
				return returnComp;
			}
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; //Disallow the editing of any cell
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				//table.setCursor(Cursor.getPredefinedCursor(Cursor.));
			}
		});

		table.setRowHeight(40);
		Color clr = new Color(98, 199,135);
		table.setSelectionBackground(clr);

		Font f = new Font("Arial", Font.CENTER_BASELINE, 14);
		table.setFont(f);
		table.setAutoCreateRowSorter(true);


		//model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Gender");
		model.addColumn("Species");
		model.addColumn("Height");
		model.addColumn("Mass");
		model.addColumn("Hair Colour");
		model.addColumn("Skin Colour");
		model.addColumn("Eye Colour");
		model.addColumn("Birth Year");
		//model.addColumn("Films");
		//model.addColumn("Vehicles");



		people.forEach(p -> {

			model.addRow(new Object[] { 

					p.getName(),
					p.getGender(),
					p.getSpecies(),
					p.getHeight(),
					p.getMass(),
					p.getHair_color(),
					p.getSkin_color(),
					p.getEye_color(),
					p.getBirthYear(),
					//p.getAll_films(),
					//p.getAll_vehicles()
			
			});
		});

			TableColumnModel columnModel = table.getColumnModel();

			//columnModel.getColumn(0).setPreferredWidth(20);   //Name
			//columnModel.getColumn(1).setPreferredWidth(120);  //Count



			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );

			table.setDefaultRenderer(String.class, centerRenderer);
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(true);

			for(int x=0;x<table.getColumnCount();x++){
				table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
			}



			JScrollPane jsp = new JScrollPane(table);
			jsp.setColumnHeaderView(table.getTableHeader());
			JTableHeader header = table.getTableHeader();
			header.setFont(new Font("Arial", Font.BOLD, 20));

			jsp.setColumnHeader(new JViewport() {
				@Override public Dimension getPreferredSize() {
					Dimension d = super.getPreferredSize();
					int HEADER_HEIGHT = 30;
					d.height = HEADER_HEIGHT ;
					return d;
				}
			});


			dialog.getContentPane().add(jsp);

			//Show it.
			dialog.setSize(new Dimension(1200, 600));
			dialog.setLocationRelativeTo(AppWindow.frame);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(dialog.HIDE_ON_CLOSE);
			dialog.setResizable(true);

		}



	}



