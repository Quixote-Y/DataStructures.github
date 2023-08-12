package diGraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//基于深度优先搜索的顶点排序 实现拓扑排序    ：就像树的前中后序访问一样
/*
 * 深度优先遍历算法：递归时对所有的顶点之访问一次， 如果将 dfs 遍历的顶点放入一个数据结构中 ，那就可以得到所有的顶点
 *    遍历的顺序取决于 这个数据结构的性质（栈，队列），以及递归调用之前还是之后保存
 */
public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre;     //所有顶点的前序排列
	private Queue<Integer> post;    //后序排列
	private Stack<Integer>  reservePost; //逆后序排列
	
	public DepthFirstOrder(DiGraph G) {
		pre = new LinkedList<>();
		post = new LinkedList<>();
		reservePost = new Stack<>();
		marked = new boolean[G.V()];
		
		for(int i =0 ;i<G.V();i++) {
			if(!marked[i]) {
				dfs(G,i);
			}
		}
		
	}
	
	//dfs
	public void dfs(DiGraph G,int s) {
		pre.add(s);              //前序进队
		
		marked[s] = true;
		for(int t : G.adj(s)) {
			if(!marked[t]) {
				dfs(G,t);
			}
		}
		
		post.add(s);           //后序入队
		reservePost.push(s);     //逆后序入栈
	}
        //这样排序就结束了
	
	public Iterable<Integer> pre(){
		return pre;
	}
	public Iterable<Integer> post(){
		return post;
	}
	public Iterable<Integer> reservePost(){
		return reservePost();
	}
}
