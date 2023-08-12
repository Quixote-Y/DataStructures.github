package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

//单点路径，如何再深度优先遍历的基础上 找到图中一个点到另一个点的路径呢
/*
 * 基于quick union的图的遍历算法只能找出联通的节点数和是否连通，无法找出具体路径
 * 但DFS深度优先遍历只需要简单的添加一个属性就能实现
 */
public class DepthFirstPaths { //深度优先搜索查找图中的路径
	private boolean marked[]; //是否标记过
	private int[] edgeTo ;  //他构造了和quick union一样的一颗树，他记录的是每个顶点到起点的路径，而不是当前节点到起点的路径，
	                          // 这很关键 ，一棵树 中，edgeTo【i】记录的是如果你要去到根节点，那你下一个节点应该是他的父节点
	private int s ;           //起点
	
	public DepthFirstPaths(Graph G, int s) {
		//构造函数 ，用于进行深度优先遍历 ，获取G中所有和 s联通的节点 ，找到所有起点为s的路径
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G,s);
	}
	
	//深度优先遍历
	public void dfs(Graph G ,int v) {
		marked[v] = true; //标记节点
		for(int t: G.adj(v)) {
			//adj返回的是邻接表中的adj[v]，也就是所有和v直接相连的节点
			if(!marked[t]) {
				//如果这个节点没有被标记，那就进去
				//先记录位置，把下一个位置的父节点设置我当前节点
				edgeTo[t] = v;         //一个节点只可能被进去一次，第一次进去后就被标记了，其他的就进不去了
				dfs(G,t);
			}
		}
	}
	
	public boolean hasPath(int v) {
		//节点v和 起点s是否有一条路径
		return marked[v];
	}
	public Iterator<Integer> pathTo(int v){
		//返回一个迭代器，储存结点s 到 v的路径 ，一次一条边
		if(!hasPath(v)) {
			return null; //没有路
		}
		Stack<Integer> path = new Stack<>();        
		for(int i =v ;i!=s ;i = edgeTo[i]) {
			//当回溯路径树到根节点时就停止
			path.push(i);           //把节点放进栈里
		}
		path.push(s);          //跟也放进去
		return (Iterator<Integer>) path;     
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Graph graph = new Graph(new FileInputStream("src\\graphTest.txt")) ; //把图读经来
		DepthFirstPaths dfp = new DepthFirstPaths(graph, 0);
		
		System.out.println("节点0和10之间是否存在路径："+dfp.hasPath(10));
		System.out.println("节点0和3之间是否存在路径："+dfp.hasPath(3));
		
		Iterator it = dfp.pathTo(3);
		System.out.println("根节点0到节点3之间的路径：");
		while(it.hasNext()) {
			int next = (int)it.next();
			System.out.println(next + "--");
		}
	}
	

}
