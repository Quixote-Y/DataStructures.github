package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//通过前面的quick union计算图的连通性 ，不难，但也不容易，中途还出了一次错误，去调试了一遍，现在学习一个更好的算法 
/**
 * 
 * 深度优先遍历 ： dfs 就是走迷宫，递归，但要添加一个数组，marked【】，用于标记已经被走过的节点
 *
 */
public class SearchByDfs {

	private boolean[] marked; // 是否到过节点
	private int count; // 联通的节点数

	public SearchByDfs(Graph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}

	/**
	 *          //这才是代码
	 * @param G   图
	 * @param s   需要所有联通节点的节点
	 * 
	 *   这算法简洁的！！！！
	 *   核心是什么 ： 就像是走迷宫，我们拿到起点的 adj(s)，它里面是所有它可以通往的地方，让后拿出第一个地方，走过去
	 *                 ，然后再内层递归中先标记这个点我到过了，再拿出第二个地方的所有可以通向的地方，在拿出第一个方向接着走，直到
	 *                 这个地方被我标记过了，已经到过了，那我就不走这个方向，返回
	 *   当碰到一条边V -W 时：
	 *                 1.要么 W 没被递归过，没有被标记，那就递归调用，走过去
	 *                 2.要么 W 已经被标记过了 ，不走这个位置，跳过 ，return
	 *                 3.如果没被标记过，第二次从另一个方向 W - V 回来的时候，肯定会跳过这条边，因为 V 肯定已经被访问过了
	 */
	public void dfs(Graph G, int s) {
		marked[s] = true; // 先把节点标记
		count++;
		for (int t : G.adj(s)) {
			if (!marked[t]) {
				dfs(G, t);
			}
		}
	}

	public boolean marked(int w) {
		return marked[w]; // 就看dfs遍历的时候有没有到过这个节点，没到过那就说明不连通
	}

	public int count() {
		return count;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Graph graph = new Graph(new FileInputStream("src\\graphTest.txt")) ; 
		SearchByQuickUnion search = new SearchByQuickUnion(graph, 0);
		System.out.println("0和1是否连通："+search.marked(1));
		System.out.println("和0联通的数计数" +search.count());                   //ok功能全部实现
	}
}
