package console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to create a table from arraylists manually.
 * This lists can be used to print Character data.
 * @author Mark
 *
 */
public class ArrayListTable {

	//Each list is a row in the table
	public  List<String> row0 = new ArrayList<>();
	public  List<String> row1 = new ArrayList<>();
	public  List<String> row2 = new ArrayList<>();
	public  List<String> row3 = new ArrayList<>();
	public  List<String> row4 = new ArrayList<>();
	public  List<String> row5 = new ArrayList<>();
	public  List<String> row6 = new ArrayList<>();
	public  List<String> row7 = new ArrayList<>();
	public  List<String> row8 = new ArrayList<>();
	public  List<String> row9 = new ArrayList<>();
	
	private static List<List<String>> table;

	/**
	 * Build a basic table (Only prints name, gender and species)
	 * @param spacing - the gap between items
	 */
	public void buildTable(int spacing) {
		row0.add(0,"Number");
		row1.add(0,"Name");
		row2.add(0,"Gender");
		row3.add(0,"Species");
		
		table = Arrays.asList(row0, row1, row2, row3);
		printTable(spacing);
	}
	
	/**
	 * Build a more advanced table displaying name, gender, 
	 * species, height, mass, hair color, eye color, skin color and the characters birth year.
	 * @param spacing - The gap between table items
	 */
	public void buildAdvancedTable(int spacing) {
		row0.add(0,"Number");
		row1.add(0,"Name");
		row2.add(0,"Gender");
		row3.add(0,"Species");
		row4.add(0,"Height");
		row5.add(0,"Mass");
		row6.add(0,"Hair Colour");
		row7.add(0,"Skin Colour");
		row8.add(0,"Eye Colour");
		row9.add(0,"Birth Year");
		
		table = Arrays.asList(row0, row1, row2, row3, row4, row5, row6, row7, row8, row9);
		printTable(spacing);
	}

	/**
	 * Loop through the table array (which contains all other stored arraylists).
	 * This is then printed.
	 * @param spacing
	 */
	private static void printTable(int spacing) {
		List<Integer> maxLengths = findMaxLengths();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < table.get(0).size(); i++) {
			for (int j = 0; j < table.size(); j++) {
				String currentValue = table.get(j).get(i);
				sb.append(currentValue);
				for (int k = 0; k < (maxLengths.get(j) - currentValue.length() + spacing); k++) {
					sb.append(' ');
				}
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	/*
	 * Used to calculate the string lengths in arrays to apply the right spacing
	 */
	private static List<Integer> findMaxLengths() {
		List<Integer> maxLengths = new ArrayList<>();
		for (List<String> row : table) {
			int maxLength = 0;
			for (String value : row) {
				if (value.length() > maxLength) {
					maxLength = value.length();
				}
			}
			maxLengths.add(maxLength);
		}
		return maxLengths;
	}



}
