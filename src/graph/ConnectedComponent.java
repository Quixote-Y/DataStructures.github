package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//连通分量
public class ConnectedComponent {
/*
 * 使用markde数组来寻找每一个顶点作为dfs的顶点，以标记所有的连通分量
 * 使用id 数组，将同一个连通分量的标识符关联起来，0是第一个连通分量中的节点，1是另一个这样，主要因为marked是boolean型数组
 */
	
	private boolean[] marked;
	private int[] id;
	private int count;       //连通分量数
	
	public ConnectedComponent(Graph G) {
		//构造函数，获取所有的连通分量id           ---->对图进行预处理
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for(int i = 0;i<G.V();i++) {
			if(!marked[i]) {
				//每次循环找到下一个没有被标记的节点，作为这一轮dfs的起点
				dfs(G,i);
				count++;
			}
		}
		
	}
	private void dfs(Graph G,int s) {
		marked[s] = true;
		id[s] = count;              //新增
		for(int t : G.adj(s)) {
			//获取和s相邻的所有节点，然后一个一个去检查他们
			if(!marked[t]) {
				//没有被标记
				dfs(G,t);
			}
		}
	}
	
	//检查两个节点是否连通
	public boolean connected(int a,int b) {
		if( id[a]==id[b]) {
			return true;
		}else {
			return false;
		}
	}
	//连通分量数
	public int count() {
		return count;
	}
	//节点v的连通标识符
	public int id(int v) {
		return id[v];
	}
	
	//test
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Graph G = new Graph(new FileInputStream("src\\graphTest.txt"));
		ConnectedComponent cc = new ConnectedComponent(G);
		//这样就能获取每一个连通分量，然后对他们单独处理了
		System.out.println(cc.count());
		System.out.println(cc.id(0));
		System.out.println(cc.connected(1, 11));      //ok
	}
}
