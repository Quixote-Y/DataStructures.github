package edgeWeightGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

//��Ȩ�ص�����ͼ
public class EdgeWeightGraph {

	private int V; // ������
	private int E; // ����
	private ArrayList<Edge>[] adj; // �ڽӱ�

	public EdgeWeightGraph(int V) {
		this.V = V;
		adj = new ArrayList[V];

		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}
	}

	public EdgeWeightGraph(InputStream in) {
		Scanner scanner = new Scanner(in);
		V = scanner.nextInt();
		int e = scanner.nextInt();
		adj = new ArrayList[V];

		for (int i = 0; i < V; i++) {
			adj[i] = new ArrayList<>();
		}
		
		//add edge
		for(int i =0;i<e;i++) {
			addEdge(new Edge(scanner.nextInt(),scanner.nextInt(),scanner.nextDouble()));
		}
	}
	
	public void addEdge(Edge edge) {
		int either = edge.either();
		int other = edge.other(either);
		//����ͼ
		adj[either].add(edge);
		adj[other].add(new Edge(other,either,edge.weight()));
		E++;
	}
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	/**
	 * ��һ�����㷵�������ڽӱ�
	 * @param v
	 * @return
	 */
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	/**
	 * ���ؼ�Ȩͼ�����еı�
	 * @return
	 */
	public Iterable<Edge> Edges(){
		ArrayList<Edge> edge = new ArrayList<>();
		for(int i =0;i<V;i++) {
			for(Edge t : adj[i]) {
				edge.add(t);
			}
		}
		return edge;
	}
	@Override
	public String toString() {
		String str = "��������"+V+",������"+E+"\n";
		ArrayList<Edge> edge = (ArrayList<Edge>) this.Edges();
		for(Edge t : edge) {
			str = str + t.toString()+"\n";
		}
		return str;
	}
	
	
	//test
	public static void main(String[] args) throws FileNotFoundException {
		EdgeWeightGraph EWG = new EdgeWeightGraph(new FileInputStream("src\\tinyEWG.txt"));
		System.out.println(EWG.toString());
	}
}
