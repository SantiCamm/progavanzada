package grafos;

import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayDeque;

public abstract class Graph {
	// Getters & setters
	public abstract int getNodes();

	public abstract Double getEdge(int from, int to);

	public abstract void setEdge(int from, int to, double cost);

	public abstract void setEdge(int from, int to);

	public abstract Integer getDegree(int node);

	public boolean hasEdge(int from, int to) {
		return getEdge(from, to) != null;
	}
	// Getters & setters

	// Misc
	public abstract Double[][] getCostMatrix();

	public abstract void printGraph();

	public abstract Iterator<Edge> getAdjacentsIterator(int node);
	// Misc

	// Algoritmos
	public abstract Double[] dijsktra(int nodoSalida);

	public abstract Integer[] dijkstraPath(int nodoSalida);
	
	public abstract Graph prim(int nodoSalida);
	
	public Double[][] floyd() {

		Double[][] d = getCostMatrix();

		for (int k = 0; k < getNodes(); k++) {
			for (int i = 0; i < getNodes(); i++) {
				for (int j = 0; j < getNodes(); j++) {
					if (d[i][k] + d[k][j] < d[i][j])
						d[i][j] = d[i][k] + d[k][j];
				}
			}
		}
		
		return d;
	}

	public boolean[][] warshall() {
		Double[][] costMatrix = getCostMatrix();

		boolean[][] e = new boolean[getNodes()][getNodes()];
		for (int i = 0; i < getNodes(); i++) {
			for (int j = 0; j < getNodes(); j++) {
				e[i][j] = costMatrix[i][j] != Double.POSITIVE_INFINITY;
			}
		}
		
		for (int k = 0; k < getNodes(); k++) {
			for (int i = 0; i < getNodes(); i++) {
				for (int j = 0; j < getNodes(); j++) {
					e[i][j] = e[i][j] || e[i][k] && e[k][j];
				}
			}
		}
		
		return e;
	}

	public void printDijkstra(Double[] d) {
		System.out.println("Nodo \t\t Minima distancia desde origen");
		for (int i = 0; i < d.length; i++) {
			System.out.println((i+1) + "\t\t\t" + d[i]);
		}
		System.out.println();
	}

	public Integer[] rebuildDijkstraPath(Integer[] path, int nodoLlegada) {
		Integer[] rebuiltPath = new Integer[path.length];
		int j = 0, i = nodoLlegada;
		rebuiltPath[j] = nodoLlegada;
		j++;
		while (path[i] != null) {
			rebuiltPath[j] = path[i];
			i = path[i];
			j++;
		}

		System.out.print("Shortest path to node " + nodoLlegada + ": ");
		for (int k = j - 1; k >= 0; k--) {
			System.out.print(rebuiltPath[k] + " ");
		}
		System.out.println();
		System.out.println();
		return rebuiltPath;
	}

	public void dfs(int source) {
		boolean[] visited = new boolean[getNodes()];
		Stack<Integer> stack = new Stack<Integer>();

		visited[source] = true;
		stack.add(source);

		while (!stack.isEmpty()) {
			Integer current = stack.pop();

			Iterator<Edge> adjacents = getAdjacentsIterator(current);
			while (adjacents.hasNext()) {
				Edge e = adjacents.next();
				if (!visited[e.getTo()]) {
					visited[e.getTo()] = true;
					stack.push(e.getTo());
				}
			}
		}
	}

	public Integer[] bfs(int source) {
		Integer[] hoops = new Integer[getNodes()];
		Queue<Integer> queue = new ArrayDeque<Integer>();
		hoops[source] = 0;
		queue.add(source);

		while (!queue.isEmpty()) {
			Integer current = queue.poll();

			Iterator<Edge> adjacents = getAdjacentsIterator(current);
			while (adjacents.hasNext()) {
				Edge e = adjacents.next();
				if (hoops[e.getTo()] == null) {
					hoops[e.getTo()] = hoops[current] + 1;
					queue.add(e.getTo());
				}
			}
		}
		return hoops;
	}
	// Algoritmos

}
