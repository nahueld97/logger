package projecto_logger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.*;

public class Graph {
	private List<Integer> nodes;
	private List<Edge<Integer,Integer>> edges;
	private static Logger logger;
	
	public Graph() {
		nodes = new LinkedList<Integer>();
		edges = new LinkedList<Edge<Integer,Integer>>();
		
		//logger
		if (logger == null){
			
			logger = Logger.getLogger(Graph.class.getName());
			
			Handler hnd = new ConsoleHandler();
			hnd.setLevel(Level.FINE);
			logger.addHandler(hnd);
			
			logger.setLevel(Level.WARNING);
			
			Logger rootLogger = logger.getParent();
			for (Handler h : rootLogger.getHandlers()){
				h.setLevel(Level.OFF);
			}
		}
	}
	
	public void addNode(int node){
		if (!nodes.contains(node)) {
			nodes.add(node);
			logger.info("node "+node+" added.");
		}else {
			logger.warning("Can't create node. Node already exist.");
		}
	}
	
	public void addEdge(int node1,int node2) {
		if(nodes.contains(node1) && nodes.contains(node2) ) {
			Edge<Integer,Integer> aux;
			boolean found=false;
			for (int i = 0; i < edges.size() && !found; i++) {
				aux=edges.get(i);
				if((aux.getKey() == node1) && (aux.getValue() == node2)) {
					found=!found;
				}
			}
			if (!found) {
				aux = new Edge<Integer,Integer>(node1,node2);
				edges.add(aux);
				logger.info("Edge added. From "+node1+" to "+node2+".");
			}else {
				logger.warning("Can't create edge. The edge already exist.");
			}
		}else {
			logger.warning("Can't create edge. One node doesn't exist.");
		}
	}
	
	public void removeNode(int node) {
		boolean found = false;
		for (int i = 0; i<nodes.size() && !found; i++) {
			if (nodes.get(i)==node) {
				nodes.remove(i);
				found=!found;
			}
		}
		Iterator<Edge<Integer,Integer>> it = edges.iterator();
		Edge<Integer,Integer> cursor;
		while(it.hasNext()) {
			cursor=it.next();
			if(cursor.getKey()==node || cursor.getValue() == node) {
				edges.remove(cursor);
			}
		}
		if(found) {
			logger.info("The node "+node+" has been deleted, and the related edges.");
		}else {
			logger.warning("The node doesn't exist.");
		}
	}
	
	public void removeEdge(int node1,int node2) {
		boolean found = false;
		Iterator<Edge<Integer,Integer>> it = edges.iterator();
		Edge<Integer,Integer> cursor;
		while(it.hasNext() && !found) {
			cursor=it.next();
			if(cursor.getKey()==node1 && cursor.getValue() == node2) {
				edges.remove(cursor);
				found =! found;
			}
		}
		if(found) {
			logger.info("The edge "+node1+" to "+node2+" has been deleted.");
		}else {
			logger.warning("The edge "+node1+" to "+node2+" can't be found.");
		}
		
	}
}
