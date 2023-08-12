package edgeWeightDiGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

//边权有向图
public class EdgeWeightDiGraph {

	private int V;
	private int E;
	private ArrayList<DirectedEdge>[] adj;      //邻接表
	
	public EdgeWeightDiGraph(int v) {
		V = v;
		E = 0;
		adj = new ArrayList[v];
		for(int i =0;i< v;i++) {
			adj[i] = new ArrayList<>();
		}
	}
	
	public EdgeWeightDiGraph(InputStream in) {
		Scanner scanner = new Scanner(in);
		V = scanner.nextInt();
		int e = scanner.nextInt();
		
		adj = new ArrayList[V];
		for(int i=0;i<V;i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=0;i<e;i++) {
			addEdge(new DirectedEdge(scanner.nextInt(),scanner.nextInt(),scanner.nextDouble()));
		}
	}
	
	public void addEdge(DirectedEdge G) {
		adj[G.from()].add(G);
		E++;
	}
	public Iterable<DirectedEdge> adj(int from){
		return adj[from];
	}
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public Iterable<DirectedEdge> edges(){
		ArrayList<DirectedEdge> edge = new ArrayList<>();
		for(int i=0;i<V;i++) {
			edge.addAll(adj[i]);
		}
		return edge;
	}
	public String toString() {
		String str ="顶点数:"+ V+" ,边数:"+E+"\n";
		for(DirectedEdge t : this.edges()) {
			str = str+t.toString()+"\n";
		}
		return str;
	}
	
	//test
	public static void main(String[] args) throws FileNotFoundException {
		EdgeWeightDiGraph G = new EdgeWeightDiGraph(new FileInputStream("src\\tinyEWD.txt"));
		System.out.println(G.toString());                              //ok
	}
}
