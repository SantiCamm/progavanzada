package coloring;

import grafos.Graph;

public class RandomOrder implements OrderMethod{
	
	public RandomOrder() {
		
	}

	@Override
	public Order generateOrder(Graph g) {
		Order randomOrder = new Order(g.getNodes());
		for(int node = 0; node< g.getNodes(); node++) {
			randomOrder.setNode(node, new Node(node, g.getDegree(node)));
		}
		
		randomOrder.shuffle();
		
		return randomOrder;
	}

}
