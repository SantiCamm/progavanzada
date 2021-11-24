package coloring;

import java.util.Arrays;
import java.util.Comparator;

import grafos.Graph;

public class MatulaOrder implements OrderMethod {

	@Override
	public Order generateOrder(Graph g) {
		Order matulaOrder = new Order(g.getNodes());

		for (int node = 0; node < g.getNodes(); node++) {
			matulaOrder.setNode(node, new Node(node, g.getDegree(node)));
		}

		matulaOrder.shuffle();

		// Ordeno los nodos por grado
		Arrays.sort(matulaOrder.getOrder(), new Comparator<Node>() {
			public int compare(Node o1, Node o2) {
				return (int) (o1.getCost() - o2.getCost());
			}
		});
		
/*
		System.out.print("ORDEN MATULA: ");
		// Seteo en matulaOrder
		for (int node = 0; node < g.getNodes(); node++) {
			System.out.print(matulaOrder.getNode(node) + " ");
		}
		System.out.println();
*/
		return matulaOrder;
	}

}
