package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;


//图的表示结构和常用api  ---这里写的是邻接表的方式
public class Graph {
	
	private int V;           //顶点数
	private int E ;          //边数
	private LinkedList<Integer>[] adj;      //邻接表
	
	public Graph(int V) {
		//创建一个只有V个顶点的图
		this.V = V;
		adj = new LinkedList[V];
		//初始化
		for(int i=0; i<V ;i++) {
			adj[i] = new LinkedList<>();      //动态扩容，所以不需要手动指定大小
		}
	}
	public Graph(InputStream in) throws IOException {
		//指定输入流，读取里面的树，先是V，E，然后是一列整数对，初始化这棵树
		Scanner scanner = new Scanner(in);
		this.V = scanner.nextInt();
		adj = new LinkedList[V];
		//初始化
		for(int i=0; i<V ;i++) {
			adj[i] = new LinkedList<>();      //动态扩容，所以不需要手动指定大小
		}
		int E2 = scanner.nextInt();              //E2不是E
		//添加边
		int v =0;
		int e = 0;
		for(int i =0;i<E2;i++) {
			v = scanner.nextInt();
			e = scanner.nextInt();
			addEdge(v,e);
		}
	}
	
	public void addEdge(int v,int e) {
		adj[v].add(e);
		adj[e].add(v);//因为是无向图，所以两边都要添加
		E++;
	}
	
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public String toString() {
		String s = V + "个顶点，" +E + "条边\n";
		for(int i=0 ;i<V;i++) {
			s+= i + " ";
			for(int t : adj[i]) {
				s+=t + " ";
			}
			s+="\n";
		}
		return s;
	}
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		int V = 10;
		//Graph graph = new Graph(V);
		Graph graph = new Graph(new FileInputStream("src\\graphTest.txt")) ;      //ok成功
		//试一试添加一条边
		graph.addEdge(10, 0);
		System.out.println(graph.toString()) ;
		//.Search search = new Search(graph);
	}

}
