package coloring;

public class Node {
	private int number;
	private double cost;
	
	public Node(int number, double cost) {
		this.number = number;
		this.cost = cost;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Node [number=" + number + ", cost=" + cost + "]";
	}
	
}
