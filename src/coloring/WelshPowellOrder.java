package coloring;

import java.util.Arrays;
import java.util.Comparator;

import grafos.Graph;

public class WelshPowellOrder implements OrderMethod {

	@Override
	public Order generateOrder(Graph g) {
		Order welshPowellOrder = new Order(g.getNodes());

		for (int node = 0; node < g.getNodes(); node++) {
			welshPowellOrder.setNode(node, new Node(node, g.getDegree(node)));
		}

		welshPowellOrder.shuffle();

		// Ordeno los nodos por grado inversamente
		Arrays.sort(welshPowellOrder.getOrder(), new Comparator<Node>() {
			public int compare(Node o1, Node o2) {
				return (int) (o2.getCost() - o1.getCost());
			}
		});

		/*
		 * System.out.print("ORDEN WELLSHPOWELL: "); // Seteo en welshPowellOrder for
		 * (int node = 0; node < g.getNodes(); node++) {
		 * System.out.print(welshPowellOrder.getNode(node) + " "); }
		 * System.out.println();
		 */
		
		return welshPowellOrder;
	}

}
