package grafos;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class ListGraph extends Graph {

	protected List<Edge>[] graph;

	@SuppressWarnings("unchecked")
	public ListGraph(int size) {
		graph = new List[size];
		for (int i = 0; i < size; i++) {
			graph[i] = new LinkedList<Edge>();
		}
	}

	@SuppressWarnings("unchecked")
	public ListGraph(boolean asd) {
		graph = new List[0];
	}

	// Misc

	@Override
	public Double[][] getCostMatrix() {
		Double[][] d = new Double[graph.length][graph.length];
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				d[i][j] = Double.POSITIVE_INFINITY;
			}
		}

		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].size(); j++) {
				if (i == j) {
					d[i][j] = 0.0;
				}

				d[i][graph[i].get(j).getTo()] = graph[i].get(j).getCost();
			}
		}

		/*
		 * for (int i = 0; i < graph.length; i++) { for (int j = 0; j < graph.length;
		 * j++) {
		 * 
		 * System.out.print(d[i][j] + " "); ; } System.out.println(); }
		 */

		return d;
	}

	@Override
	public void printGraph() {
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[i].size(); j++) {
				System.out.println("Nodo " + i + " --> Nodo " + graph[i].get(j).getTo() + " --> Costo "
						+ graph[i].get(j).getCost());
			}
		}
	}

	@Override
	public Iterator<Edge> getAdjacentsIterator(int node) {
		/*
		 * List<Edge> adyacents = new LinkedList<Edge>(); for (int i = 0; i <
		 * graph.length; i++) { Double cost = getEdge(node, i); if(cost != null) {
		 * adyacents.add(new Edge(i, cost)); } } return adyacents.iterator();
		 */
		return graph[node].iterator();
	}
	// Misc

	// Getters & setters
	@Override
	public int getNodes() {
		return graph.length;
	}

	@Override
	public Double getEdge(int from, int to) {
		for (Edge current : graph[from]) {
			if (current.to == to) {
				return current.cost;
			}
		}
		return null;
	}

	@Override
	public void setEdge(int from, int to, double cost) {
		for (Edge current : graph[from]) {
			if (current.to == to) {
				current.cost = cost;
				return;
			}
		}
		// Si no existe la arista actual, la genero
		graph[from].add(new Edge(from, to, cost));
	}

	@Override
	public void setEdge(int from, int to) {
		for (Edge current : graph[from]) {
			if (current.to == to) {
				return;
			}
		}
		// Si no existe la arista actual, la genero
		graph[from].add(new Edge(from, to));
	}

	@Override
	public Integer getDegree(int node) {
		return graph[node].size();
	}
	// Getters & setters

	// Algoritmos
	@Override
	public Double[] dijsktra(int nodoSalida) {
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		
		boolean[] visited = new boolean[graph.length];
		Integer[] predecesores = new Integer[graph.length];
		Double[] d = new Double[graph.length];

		Arrays.fill(d, Double.POSITIVE_INFINITY);
		Arrays.fill(visited, false);
		Arrays.fill(predecesores, null);

		d[nodoSalida] = 0.0;
		pq.add(new Pair(nodoSalida, 0.0));

		while (!pq.isEmpty()) {
			Pair u = pq.poll();
			visited[u.getNodo()] = true;

			// Para cada adyacente de u
			for (int i = 0; i < graph[u.getNodo()].size(); i++) {
				Integer v = graph[u.getNodo()].get(i).getTo();
				if (!visited[v] && d[v] > d[u.getNodo()] + graph[u.getNodo()].get(i).getCost()) {
					d[v] = d[u.getNodo()] + graph[u.getNodo()].get(i).getCost();
					predecesores[v] = u.getNodo();
					pq.add(new Pair(v, d[v]));
				}
			}
		}

		return d;
	}

	@Override
	public Integer[] dijkstraPath(int nodoSalida) {
		boolean[] visited = new boolean[graph.length];
		Integer[] predecesores = new Integer[graph.length];
		Double[] d = new Double[graph.length];
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		Arrays.fill(d, Double.POSITIVE_INFINITY);
		Arrays.fill(visited, false);
		Arrays.fill(predecesores, null);

		d[nodoSalida] = 0.0;
		pq.add(nodoSalida);

		while (!pq.isEmpty()) {
			Integer u = pq.poll();
			visited[u] = true;

			// Para cada adyacente de u
			for (int i = 0; i < graph[u].size(); i++) {
				Integer v = graph[u].get(i).getTo();
				if (!visited[v] && d[v] > d[u] + graph[u].get(i).getCost()) {
					d[v] = d[u] + graph[u].get(i).getCost();
					predecesores[v] = u;
					pq.add(v);
				}
			}
		}

		return predecesores;
	}

	@Override
	public Graph prim(int nodoSalida) {
		// TODO Auto-generated method stub
		return null;
	}
	// Algoritmos

	public static void main(String args[]) {
		/*
		 * ListUnidirectionalGraph g = new ListUnidirectionalGraph(13); g.setEdge(0, 1,
		 * 1); g.setEdge(0, 8, 1); g.setEdge(1, 2, 1); g.setEdge(1, 5, 1); g.setEdge(2,
		 * 3, 1); g.setEdge(4, 5, 1); g.setEdge(4, 8, 1); g.setEdge(5, 6, 1);
		 * g.setEdge(5, 9, 1); g.setEdge(6, 7, 1); g.setEdge(8, 9, 1); g.setEdge(9, 10,
		 * 1); g.setEdge(11, 12, 1);
		 * 
		 * g.printGraph(); g.dfs(4); Integer[] hoops = g.bfs(0);
		 * 
		 * for (int i = 0; i < hoops.length; i++) { System.out.println(hoops[i] +
		 * " saltos al nodo " + i); }
		 */

		Graph g = new ListGraph(5);
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
		g.rebuildDijkstraPath(p, 3);

	}
}
