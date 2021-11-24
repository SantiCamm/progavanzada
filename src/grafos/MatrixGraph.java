package grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MatrixGraph extends Graph {

	private Double[][] graph;

	public MatrixGraph(int size) {
		graph = new Double[size][size];
		emptyGraph();
	}
	
	// Misc
	
	@Override
	public Double[][] getCostMatrix(){
		Double[][] f = new Double[graph.length][graph.length];

		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f[i].length; j++) {
				f[i][j] = graph[i][j];
				if (i == j) {
					f[i][j] = 0.0;
				}
			}
		}

		return f;
	}
	
	public void printGraph() {
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				System.out.print(String.format("%-10.0f", graph[i][j]) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void emptyGraph() {
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].length; j++) {
				graph[i][j] = Double.POSITIVE_INFINITY;
			}
		}
	}

	@Override
	public Iterator<Edge> getAdjacentsIterator(int node) {
		ArrayList<Edge> adyacents = new ArrayList<Edge>();
			for (int j = 0; j < graph[node].length; j++) {
				adyacents.add(new Edge(node, j, graph[node][j]));
			}
		return adyacents.iterator();
	}
	// Misc

	// Algoritmos
	public int buscarMenorNodo(Double[] d, boolean[] visitados) {
		int nodo = -1;
		Double min = Double.POSITIVE_INFINITY;
		for (int i = 0; i < d.length; i++) {
			if (visitados[i] == false && d[i] <= min) {
				min = d[i];
				nodo = i;
			}
		}

		return nodo;
	}

	@Override
	public Double[] dijsktra(int nodoSalida) {
		// validarNodo(nodoSalida);

		boolean[] visitados = new boolean[graph.length];
		Integer[] predecesores = new Integer[graph.length];
		Double[] d = new Double[graph.length];

		Arrays.fill(d, Double.POSITIVE_INFINITY);
		Arrays.fill(visitados, false);
		Arrays.fill(predecesores, null);

		d[nodoSalida] = 0.0;

		for (int i = 0; i < graph.length - 1; i++) {
			int w = buscarMenorNodo(d, visitados);
			visitados[w] = true;

			// Para cada adyacente de w
			for (int v = 0; v < graph.length; v++) {
				if (visitados[v] == false && graph[w][v] != Double.POSITIVE_INFINITY && d[w] != Double.POSITIVE_INFINITY
						&& d[v] > d[w] + graph[w][v]) {
					d[v] = d[w] + graph[w][v];
					predecesores[v] = w;
				}
			}
		}

		return d;
	}

	@Override
	public Integer[] dijkstraPath(int nodoSalida) {
		// validarNodo(nodoSalida);

		boolean[] visitados = new boolean[graph.length];
		Integer[] predecesores = new Integer[graph.length];
		Double[] d = new Double[graph.length];

		Arrays.fill(d, Double.POSITIVE_INFINITY);
		Arrays.fill(visitados, false);
		Arrays.fill(predecesores, null);

		d[nodoSalida] = 0.0;

		for (int i = 0; i < graph.length - 1; i++) {
			int w = buscarMenorNodo(d, visitados);
			visitados[w] = true;

			// Para cada adyacente de w
			for (int v = 0; v < graph.length; v++) {
				if (visitados[v] == false && graph[w][v] != Double.POSITIVE_INFINITY && d[w] != Double.POSITIVE_INFINITY
						&& d[v] > d[w] + graph[w][v]) {
					d[v] = d[w] + graph[w][v];
					predecesores[v] = w;
				}
			}
		}

		return predecesores;
	}
	
	public Graph prim(int nodoSalida) {
		return null;
	}

	// Algoritmos

	// Getters & setters
	@Override
	public int getNodes() {
		return graph.length;
	}

	@Override
	public Double getEdge(int from, int to) {
		return graph[from][to];
	}

	@Override
	public void setEdge(int from, int to, double cost) {
		graph[from][to] = cost;
	}
	
	@Override
	public void setEdge(int from, int to) {
		graph[from][to] = Double.POSITIVE_INFINITY;
	}

	@Override
	public Integer getDegree(int node) {
		int degree = 0, i;
		for (i = 0; i < graph.length; i++) {
			if (graph[node][i] != -1) {
				degree++;
			}
		}

		return degree;
	}
	// Getters & setters

	public static void main(String args[]) {
		System.out.println("DIJKSTRA");
		Graph g = new MatrixGraph(5);
		g.setEdge(0, 1, 10);
		g.setEdge(0, 3, 30);
		g.setEdge(0, 4, 100);
		g.setEdge(1, 2, 50);
		g.setEdge(2, 4, 10);
		g.setEdge(3, 2, 20);
		g.setEdge(3, 4, 60);

		g.printGraph();

		Double[] d = g.dijsktra(0);
		g.printDijkstra(d);

		Integer[] p = g.dijkstraPath(0);
		g.rebuildDijkstraPath(p, 1);

		System.out.println("FLOYD Y WARSHALL");
		Graph g2 = new MatrixGraph(7);
		g2.setEdge(0, 1, 200);
		g2.setEdge(0, 2, 100);
		g2.setEdge(1, 0, 200);
		g2.setEdge(1, 2, 4);
		g2.setEdge(2, 0, 100);
		g2.setEdge(2, 1, 4);
		g2.setEdge(2, 3, 20);
		g2.setEdge(2, 4, 5);
		g2.setEdge(3, 2, 20);
		g2.setEdge(4, 2, 5);
		g2.setEdge(5, 6, 5);

		g2.printGraph();

		Double floyd[][] = g2.floyd();
		
		System.out.println("MATRIZ DE MENORES COSTOS");
		for (int i = 0; i < g2.getNodes(); i++) {
			for (int j = 0; j < g2.getNodes(); j++) {
				System.out.print(String.format("%-10.0f", floyd[i][j]) + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		boolean warshall[][] = g2.warshall();
		
		System.out.println("MATRIZ DE CAMINOS POSIBLES");
		for (int i = 0; i < g2.getNodes(); i++) {
			for (int j = 0; j < g2.getNodes(); j++) {
				System.out.print(String.format("%-10b", warshall[i][j]) + " ");
			}
			System.out.println();
		}
		System.out.println();
		
	}

}
