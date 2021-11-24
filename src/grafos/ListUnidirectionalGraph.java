package grafos;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ListUnidirectionalGraph extends ListGraph {

	public ListUnidirectionalGraph(int size) {
		super(size);
	}

	public ListUnidirectionalGraph(boolean hasCost) {
		super(hasCost);
	}

	// Getters & setters
	@Override
	public Double getEdge(int from, int to) {
		if (graph[from].size() < graph[to].size()) {
			return super.getEdge(from, to);
		}
		return super.getEdge(to, from);
	}

	@Override
	public void setEdge(int from, int to, double cost) {
		super.setEdge(from, to, cost);
		super.setEdge(to, from, cost);
	}
	// Getters & setters

	// Algoritmos

	@Override
	public Graph prim(int nodoSalida) {
		Comparator<Edge> EdgeCostComparator = (obj1, obj2) -> {
			return (int) (obj1.getCost() - obj2.getCost());
		};

		PriorityQueue<Edge> edges = new PriorityQueue<Edge>(EdgeCostComparator);
		Double costo = 0.0;
		boolean[] visitado = new boolean[graph.length];
		ListGraph prim = new ListGraph(graph.length);
		Arrays.fill(visitado, false);

		// Guardo en la cola de prioridad las aristas adyacentes del nodoSalida
		for (int i = 0; i < graph[nodoSalida].size(); i++) {
			edges.add(graph[nodoSalida].get(i));
		}

		visitado[nodoSalida] = true;
		int cont = 1;

		// Voy sacando de la cola de prioridad los nodos visitados
		// El ultimo que saque sera el menor
		while (cont < visitado.length) {

			Edge e;
			do {
				e = edges.poll();
			} while (visitado[e.getTo()]);

			// Marco el nodo como visitado
			visitado[e.getTo()] = true;

			// Guardo la arista
			prim.setEdge(e.getFrom(), e.getTo(), e.getCost());

			// Para cada adyacente no visitado, agrego las aristas a la cola de prioridad
			for (int i = 0; i < graph[nodoSalida].size(); i++) {
				if (visitado[graph[e.getTo()].get(i).getTo()] == false) {
					edges.add(graph[e.getTo()].get(i));
				}
			}
			cont++;
			costo += e.getCost();
		}

		System.out.println("COSTO: " + costo);
		return prim;
	}
	// Algoritmos

	public static void main(String args[]) {
		/*
		 * System.out.println("FLOYD Y WARSHALL"); Graph g2 = new
		 * ListUnidirectionalGraph(7); g2.setEdge(0, 1, 200); g2.setEdge(0, 2, 100);
		 * g2.setEdge(1, 2, 4); g2.setEdge(2, 3, 20); g2.setEdge(2, 4, 5); g2.setEdge(5,
		 * 6, 5);
		 * 
		 * g2.printGraph();
		 * 
		 * Double floyd[][] = g2.floyd();
		 * 
		 * System.out.println("MATRIZ DE MENORES COSTOS"); for (int i = 0; i <
		 * g2.getNodes(); i++) { for (int j = 0; j < g2.getNodes(); j++) {
		 * System.out.print(String.format("%-10.0f", floyd[i][j]) + " "); }
		 * System.out.println(); } System.out.println();
		 * 
		 * boolean warshall[][] = g2.warshall();
		 * 
		 * System.out.println("MATRIZ DE CAMINOS POSIBLES"); for (int i = 0; i <
		 * g2.getNodes(); i++) { for (int j = 0; j < g2.getNodes(); j++) {
		 * System.out.print(String.format("%-10b", warshall[i][j]) + " "); }
		 * System.out.println(); } System.out.println();
		 */

		/*
		 * Graph g = new ListUnidirectionalGraph(6); g.setEdge(0, 2, 1); g.setEdge(0, 1,
		 * 6); g.setEdge(0, 3, 5); g.setEdge(1, 4, 3); g.setEdge(2, 1, 6); g.setEdge(2,
		 * 3, 4); g.setEdge(2, 4, 6); g.setEdge(2, 5, 6); g.setEdge(3, 5, 2);
		 * g.setEdge(4, 5, 6);
		 * 
		 * // g.printGraph();
		 * 
		 * Graph primGraph = g.prim(0); primGraph.printGraph();
		 */
	}
}
