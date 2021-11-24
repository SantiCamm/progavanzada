package coloring;

import java.util.Arrays;

public class Coloring {
	private int colors;
	private int [] colorAssignment;
	
	public int getColors() {
		return colors;
	}
	
	public int[] getColorAssignment() {
		return colorAssignment;
	}
	
	public void setColorAssignment(int[] v) {
		colorAssignment = v;
	}
	
	public void setColors(int colorAmount) {
		colors = colorAmount;
	}
	
	public int getColor(int node) {
		return colorAssignment[node];
	}
	
	public void printColoring(Order o) {
		for (int i = 0; i < colorAssignment.length; i++) {
			System.out.println("Nodo: " + o.getNode(i) + " Color: " + colorAssignment[i]);
		}
	}

	@Override
	public String toString() {
		return colors + "\n" + Arrays.toString(colorAssignment) + "]";
	}
	
	
	
	
}
