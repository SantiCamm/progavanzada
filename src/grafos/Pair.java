package grafos;

public class Pair implements Comparable<Pair>{
	private int nodo;
	private Double costo;
	
	public Pair (int nodo, Double costo) {
		this.nodo = nodo;
		this.costo = costo;
	}
	public int getNodo() {
		return nodo;
	}
	public void setNodo(int nodo) {
		this.nodo = nodo;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	@Override
	public int compareTo(Pair other) {
		return this.getCosto().compareTo(other.getCosto());
	}
	
	
}
