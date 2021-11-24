package coloring;

import grafos.Graph;

public interface ColoringMethod{
	public Coloring paint(Graph g, Order o);
}
