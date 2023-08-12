package diGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

//有向环检测
/*  多用于优先级限制下的调度问题，检测是否问题出现错误 ,发现环，只发现有环，而不是多少个环
 * 一旦找到一条 v--->w的边，w 已经在递归栈中，那么这个环就形成了 
 */
public class DiCycle {

	private boolean[] marked;
	private int[] edgeTo;
	private boolean[] onStack; // 记录递归栈内的所有顶点
	private Stack<Integer> cycle; // 如果环存在，有向环中的所有顶点

	public DiCycle(DiGraph G) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		onStack = new boolean[G.V()];
		for (int i = 0; i < G.V(); i++) {
			if (!marked[i]) {
				dfs(G, i);
			}
		}
	}

	// dfs
	public void dfs(DiGraph G, int s) {
		onStack[s] = true;
		marked[s] = true;

		for (int t : G.adj(s)) {
			if (this.hasCycle()) {
				return;
			}
			if (!marked[t]) {
				edgeTo[t] = s;
				dfs(G, t);
			} else if (onStack[t]) {
				cycle = new Stack<>();
				for (int i = s; i != t; i = edgeTo[i]) {
					// i从s开始，因为t已经在栈里了，所以找父节点找回到t时，那就说明环所有节点全部加入
					cycle.push(i);
				}
				cycle.push(t);
				cycle.push(s);
			}
		}

		// 到这说明一次递归结束了，要把stack置为false；
		onStack[s] = false;
	}

	// hasCycle
	public boolean hasCycle() {
		return cycle != null;
	}
	
	//cycle
	public Iterable<Integer> cycle(){
		return cycle;
	}
 
	public static void main(String[] args) throws FileNotFoundException {
		DiGraph G = new DiGraph(new FileInputStream("src\\tinyDG.txt"));
		DiCycle cycle = new DiCycle(G);
		System.out.println(cycle.hasCycle());
		
		for(int t : cycle.cycle()) {
			System.out.print(t+"-->");
		}
	}

}
