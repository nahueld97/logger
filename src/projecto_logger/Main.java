package projecto_logger;

public class Main {

	public static void main(String[] args) {
		Graph grafo = new Graph();
		grafo.addNode(1);
		grafo.addNode(2);
		grafo.addEdge(1, 2);
		grafo.addEdge(1, 2);
		grafo.removeNode(3);
		grafo.removeNode(1);
		grafo.addNode(3);
		grafo.addNode(3);
		grafo.addEdge(2, 3);
		grafo.addEdge(3, 2);
		grafo.removeEdge(5, 2);
		grafo.removeEdge(3, 2);
	}

}
