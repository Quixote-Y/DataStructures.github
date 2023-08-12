package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

//广度优先遍历
//深度优先遍历是一直往深处走，走到走不了再回头              --递归，栈
//广度优先遍历是先把所有近处的走完，然后再走远一步的位置    ---迭代，队列
//二者存储数据的数据结构不同
public class BorderFirstSearch {

	private boolean[] marked; // 标识
	private int[] edgeTo; // 节点i的父节点是谁 ，最短路径树
	private int s; // 根节点
	private int count; // 和根节点联通的节点数

	public BorderFirstSearch(Graph G, int s) {
		// 构造函数，标记所有和s联通的节点，广度优先遍历去找到以s为起点到所有联通节点的最短路径树
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		// 构造
		this.s = s;
		bfs(G, s);
	}

	// bfs
	public void bfs(Graph G, int s) {
		// 创建一个队列，用来存储要访问的节点
		LinkedList<Integer> queue = new LinkedList<>();
		marked[s] = true;
		queue.add(s); // 将起点放入队列
		while (!queue.isEmpty()) {
			// 当队列为null结束
			int v = queue.remove(); // 移除队头元素
			for (int t : G.adj(v)) {
				// 获取和节点v直接相连的节点
				if (!marked[t]) {
					// 没有被标记
					marked[t] = true; // 标记
					edgeTo[t] = v; // 说明第一次到节点t的上一个节点是v
					// 入队
					queue.add(t); // 到队尾
				}
			}
		}
	}

	// 联通是否
	public boolean hasPath(int v) {
		return marked[v] == true;
	}

	// 最短路径
	public Stack<Integer> pathTo(int v) {
		// 返回一个迭代器，里面包含从起点s到v的路径
		if (marked[v] == false) {
			return null;
		}
		Stack<Integer> path = new Stack<>();
		for (int i = v; i != s; i = edgeTo[i]) {
			path.push(i);
		}
		path.push(s);
		return path;
	}

	// 测试
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Graph graph = new Graph(new FileInputStream("src\\graphTest.txt")); // 把图读入
		BorderFirstSearch bfs = new BorderFirstSearch(graph, 0);
		
		System.out.println("节点0和11是否联通："+bfs.hasPath(11));
		System.out.println("节点0和节点3之间的最短路径：");
		Stack<Integer> path = bfs.pathTo(3);
		while(!path.isEmpty()) {
			int v = path.pop();
			System.out.print(v+"-- ");
		}
	}
}
/*
 * 在Java中，实例化队列可以使用多种类型，其中最常见的类型是LinkedList和ArrayDeque。
 * 
 * LinkedList是Java集合框架中的一个类，实现了List和Deque接口，可以用作队列、栈或双向队列。
 * LinkedList基于链表实现，因此插入和删除元素的复杂度为O(1)，但访问元素的复杂度为O(n)。
 * LinkedList适合用于频繁进行插入和删除操作的场景。
 * 
 * ArrayDeque也是Java集合框架中的一个类，实现了Deque接口，可以用作队列或栈。ArrayDeque基于数组实现，因此访问元素的复杂度为O(1
 * )， 但插入和删除元素的复杂度为O(n)。ArrayDeque适合用于需要快速访问元素的场景。
 * 
 * 需要注意的是，Java中的Queue接口是一个接口，不能直接实例化。需要使用具体的类来实例化一个队列对象，例如LinkedList或ArrayDeque。
 * 同时，根据队列的需求和具体场景，还可以使用其他类型来实例化队列，例如PriorityQueue、ConcurrentLinkedQueue等。
 */
