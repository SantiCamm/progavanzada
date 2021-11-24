package coloring;

import java.util.Random;

public class Order{
	private Node[] order;
	
	public Order(int size) {
		this.order = new Node[size];
	}
	
	public Order (Node[] order) {
		this.order = order;
	}
	
	public Node[] getOrder() {
		return order;
	}
	
	public Node getNode(int index) {
		return order[index];
	}
	
	public void setNode(int pos, Node node) {
		order[pos] = node;
	}
	
	public void shuffle() {
		Random rnd = new Random();
		
		for (int i = order.length - 1; i > 0; i--) {
	      int index = rnd.nextInt(i + 1);
	      
	      // Simple swap
	      Node aux = order[index];
	      order[index] = order[i];
	      order[i] = aux;
	    }
	}
	
	public void printOrder() {
		for (int i = 0; i < order.length; i++) {
			System.out.print(order[i] + " ");
		}
	}
}
