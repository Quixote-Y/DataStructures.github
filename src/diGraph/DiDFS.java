package diGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

//有向图的深度优先遍历
/*
 * 单点可达性     多点可达性 
 */
public class DiDFS {

	private boolean[] marked;
	
	//单点可达
	public DiDFS(DiGraph G ,int s) {
		marked = new boolean[G.V()];
		dfs(G,s);
	}
	
	 //多点可达，传入一个可迭代对象，对里面的所有值当作起点进行dfs，
	//使得用列能够判断给定的一个或一组节点能到达那些其他顶点
	public DiDFS(DiGraph G,Iterable<Integer> source) {
		marked = new boolean[G.V()];
		
		for(int t: source) {
			if(!marked[t]) {
				dfs(G,t);
			}
		}
	}
	
	//dfs
	public void dfs(DiGraph G ,int s) {
		marked[s] = true;
		
		for(int t: G.adj(s)) {
			if(!marked[t]) {
				dfs(G,t);
			}
		}
	}
	//检测是否可达
	public boolean marked(int v) {
		return marked[v];
	}
	public static void main(String[] args) throws FileNotFoundException {
		DiGraph G = new DiGraph(new FileInputStream("src\\tinyDG.txt"));
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		DiDFS reach = new DiDFS(G,stack);//传入一个栈，里面是0，1，2，3，看他们能到达哪些节点
		for(int i = 0 ;i<G.V();i++) {
			System.out.print(i+"->"+ reach.marked(i)+"\t");
		}            //ok
	}
}
