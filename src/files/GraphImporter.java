package files;

import java.io.File;
import java.io.IOException;

import grafos.Graph;

public abstract class GraphImporter {
	protected int initialNode;
	protected int totalNodes;
	protected boolean edgesWithCost;

	public GraphImporter(boolean edgesWithCost) {
		this(0, edgesWithCost);
	}

	public GraphImporter(int initialNode, boolean edgesWithCost) {
		this.initialNode = initialNode;
		this.totalNodes = 0;
		this.edgesWithCost = edgesWithCost;
	}

	public abstract void importFromFile(Graph g, File file) throws IOException, InvalidGraphFormatException;
}
