package coloring;

import java.io.File;
import java.io.IOException;

import files.GraphImporter;
import files.IncidenceListImporter;
import files.InvalidGraphFormatException;
import grafos.Graph;
import grafos.ListUnidirectionalGraph;

public class Main {

	public static void main(String[] args) {
		try {
			
			Graph g = new ListUnidirectionalGraph(false);
			GraphImporter importer = new IncidenceListImporter(false);
			importer.importFromFile(g, new File("graph_2021_2C.in"));
			
			// g.printGraph();
			
			Coloring bestColoring = null;
			
			for (int i = 0; i < 1; i++) {
				OrderMethod om = new MatulaOrder();
				Order order = om.generateOrder(g);
				
				ColoringMethod coloringMethod = new ColoringFirstColor();
				Coloring coloring = coloringMethod.paint(g, order);
				
				// System.out.println(coloring.getColors());
				if (bestColoring == null || coloring.getColors() < bestColoring.getColors()) {
					bestColoring = coloring;
				}
			}
			
			System.out.println("COLOREO:\n" + bestColoring);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidGraphFormatException e2) {
			e2.printStackTrace();
		}
		
		/*
		 * ListUnidirectionalGraph g2 = new ListUnidirectionalGraph(5); g2.setEdge(0, 1,
		 * 1); g2.setEdge(1, 2, 1); g2.setEdge(0, 2, 1); g2.setEdge(2, 3, 1);
		 * g2.setEdge(2, 4, 1);
		 * 
		 * g2.printGraph();
		 * 
		 * OrderMethod orderMethod = new WelshPowellOrder(); Order order =
		 * orderMethod.generateOrder(g2);
		 * 
		 * OrderMethod orderMethod2 = new MatulaOrder(); Order order2 =
		 * orderMethod2.generateOrder(g2);
		 * 
		 * ColoringMethod coloringMethod = new ColoringFirstColor(); Coloring
		 * matulaColoring = coloringMethod.paint(g2, order); Coloring
		 * welshpowellColoring = coloringMethod.paint(g2, order2);
		 * 
		 * System.out.println("COLORES MATULA: " + matulaColoring.getColors());
		 * System.out.println("COLORES WELSHPOWELL: " +
		 * welshpowellColoring.getColors());
		 */
	}
}
