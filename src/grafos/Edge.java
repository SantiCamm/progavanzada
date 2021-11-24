package grafos;

public class Edge{
	public Integer from;
	public Integer to;
	public Double cost;

	public Edge(int from, int to, double cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	public Edge(int from, double cost) {
		this.from = from;
		this.cost = cost;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", cost=" + cost + "]";
	}
	
	


}
