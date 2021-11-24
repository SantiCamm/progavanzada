package files;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import grafos.Graph;


public class IncidenceListImporter extends GraphImporter {

	public IncidenceListImporter(boolean edgesWithCost) {
		super(edgesWithCost);
	}

	public IncidenceListImporter(int initialNode, boolean edgesWithCost) {
		super(initialNode, edgesWithCost);
	}

	@Override
	public void importFromFile(Graph graph, File file) throws IOException, InvalidGraphFormatException{
		Scanner in = new Scanner(new FileReader(file));
		totalNodes = in.nextInt();

		int totalEdges = in.nextInt();

		for (int i = 0; i < totalEdges; i++) {
			if(edgesWithCost) {
				graph.setEdge(validate(in.nextInt()), validate(in.nextInt()), in.nextDouble());
			} else {
				graph.setEdge(validate(in.nextInt()), validate(in.nextInt()));
			}
		}

		in.close();

	}
	
	public int validate(int fileNode) throws InvalidGraphFormatException{
		int node = fileNode - initialNode;
		if(node < 0 || node >= totalNodes) {
			throw new InvalidGraphFormatException();
		}
		return node;
	}

}
