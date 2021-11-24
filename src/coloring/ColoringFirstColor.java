package coloring;

import java.util.Iterator;

import grafos.Edge;
import grafos.Graph;

public class ColoringFirstColor implements ColoringMethod{

	@Override
	public Coloring paint(Graph g, Order o) {
		Coloring coloring = new Coloring();
		
		int color = 1;
		int cantColores = 0;
		boolean todosColoreados = false;
		boolean coloreable = false;
		
		int[] nodosColores = new int[g.getNodes()];
		
		for (int i = 0; i < g.getNodes(); i++) {
			nodosColores[i] = 0;
		}	
		
		while (todosColoreados == false) {
			
			for (int i = 0; i < g.getNodes(); i++) {
				
				int actual = o.getNode(i).getNumber();
				coloreable = true;
				
				// si no está coloreado
				if (nodosColores[actual] == 0) {
					// recorre todos los nodos adyacentes al actual
					Iterator<Edge> adj= g.getAdjacentsIterator(actual);
					
					while(adj.hasNext()) {
						Edge e = adj.next();
						if(nodosColores[e.getTo()] == color) {
							coloreable = false;
							break;	
						}
					}

					if (coloreable) {
						nodosColores[actual] = color; 
					}
				}
			}
			
			color++;
			cantColores++;
			
			// Se podría implementar con un contador preguntando
			// por la cantidad total de elementos del vector
			
			// Busca a ver si ya están todos coloreados
			todosColoreados = true;
			for (int i = 0; i < nodosColores.length; i++) {
				if (nodosColores[i] == 0) {
					todosColoreados = false;
				}
			}
		}
		
		coloring.setColorAssignment(nodosColores);
		coloring.setColors(cantColores);
		
		return coloring;
	}

}
