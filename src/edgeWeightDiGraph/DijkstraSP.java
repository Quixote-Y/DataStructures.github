package edgeWeightDiGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

//迪杰斯特拉最短路径算法 ---》权值为正
/*
 * 迪杰斯特拉算法和 Prim最小生成树（即时版） 的算法结构是一样的
 * 可以说内核是一样的
 * Prim 算法 每次添加 的都是离最小生成树最近的非树节点
 *      distTo[w]:顶点w到树现在的最小生成树最短的路径
 *      edgeTo[w]:最短路径对应的边
 *      pq : 索引优先队列，我使用了很简单的hashMap和优先队列PriorityQueue来实现，里面有一些细节，
 *                      首先：索引优先队列的优先队列排的是（权重），然后hashMAp（下标,权重）这样的方式，当添加元素时，都添加，查是否包含
 *                            某个节点，就去hahsMap里查是否有对应的下标，删除节点需要 返回优先队列的最小值，然后遍历map，找到对应的值，然后返回key，删除这条记录
 *      核心是visit（v）方法，访问：标记顶点v，检查顶点v的所有邻接点，被标记的就忽略，没被标记的说明还没有加入树，检查v到w的权重
 *                           是否小于现在的w的权重（上一个和他临街的点到他的权重），如果不小于，那不更新，如果小于，那就
 *                           更新distTo[w]的值（权重），然后更新edgeTo[w]的边，更新为自己，
 *                           然后很关键的：更新优先队列里的值，如果优先队列里有这个顶点，那就更新他的最小权值，没有这个点就加入
 * DijkstraSP 算法 每次添加的都是 离起点最近 的非树节点
 *     distTo[w]:顶点w到起点目前的最段路径
 *     edgeTo[w]:对应这条最短路径，节点w的父节点是谁 ，这就是union-find中的id[]数组____---->这里是我的理解出现了偏差，书上是一个DirectedEdge类的数组，存储的是对应的内条边，有一些出入，适用范围更大
 *     pq ： 索引优先队列 ，和上面一样，不过多赘述
 *     核心是relax（G，v）方法,放松：检查顶点v的所有邻接点，检查他的对端节点w，他的distTo[w]是否大于distTo[v]+e.weight,如果大于
 *                                  说明：之前到节点w的内条路径，他的总权值大于从v到w的这条路径的权值，那就更新distTo[w]为小的权值
 *                                  然后更新w的edgeTo[w]的值为v--->父节点更新； 如果不小于那就不发生变化
 *                                  然后也是很关键的，更新优先队列   
 *
 */
public class DijkstraSP {

	private int[] edgeTo;          //边，类似union-find的路径记录
	private double[] distTo;         //权重和，记录起点到当前节点的最短路径权重
	private IndexMinPQ pq;         //索引优先队列，实时取出所有邻接点里里起点最近的内个节点
	private int root;             //根节点
	private final int MAX=100;
	
	public DijkstraSP(EdgeWeightDiGraph G,int v) {
		//构造函数，构建节点v到 图G 中的所有节点的最短路径
		edgeTo = new int[G.V()];
		distTo = new  double[G.V()];
		pq = new IndexMinPQ();
		root = v;
		//初始化 父节点 距离，
		for(int i=0;i<G.V();i++) {
			edgeTo[i] = i;
			distTo[i] = MAX;
		}
		
		//起点设置
		distTo[v] = 0.0;
		//起点进入优先队列
		pq.insert(v, 0.0);//起点的权值肯定时0。0
		//开始松弛
		while(!pq.isEmpty()) {
			relax(G,pq.delMin());
		}
	}
	
	//松弛
	public void relax(EdgeWeightDiGraph G,int v) {
		/*检查顶点v的所有邻接点，检查他的对端节点w，他的distTo[w]是否大于distTo[v]+e.weight,如果大于
		 *    说明：之前到节点w的内条路径，他的总权值大于从v到w的这条路径的权值，那就更新distTo[w]为小的权值
		 *    然后更新w的edgeTo[w]的值为v--->父节点更新； 如果不小于那就不发生变化
		 *    然后也是很关键的，更新优先队列   
         */
		for(DirectedEdge t: G.adj(v)) {
			int to = t.to();
			if(distTo[to] > distTo[v]+t.weight()) {
				//原来的总权值大于现在的，更新权值
				distTo[to] = distTo[v]+t.weight();
				//更新路劲
				edgeTo[to] = v;
				//更新优先队列
				if(pq.contains(to)) {
					pq.change(to, distTo[to]);
				}else {
					pq.insert(to, distTo[to]);
				}
			}
		}
	}
	
	//一些小api
	/**
	 * 到顶点v的最短路劲权值
	 * @param v
	 * @return
	 */
	public double disTo(int v) {
		return distTo[v];
	}
	/**
	 * 是否连通（有路）到达节点v
	 * 不用使用marked数组来标识是否节点被访问过，如果某个节点的distTo是 MAX，那说明起点无法到达该节点，不连通
	 * @param v
	 * @return
	 */
	public boolean hasPath(int v) {
		return distTo[v]<MAX;
	}
	/**
	 * 返回可迭代对象，到达v的路劲
	 * @param v
	 * @return
	 */
	public Iterable<Integer> pathTo(int v){
		if(!hasPath(v))return null;
		Stack<Integer> stack = new Stack<>();
		for(int i = v;i != edgeTo[i];i = edgeTo[i]) {
			//查询路劲的方式，向上一个个找父节点，根节点的值是自己
			stack.push(i);
		}
		stack.push(root);
		return stack;
	}
	
	
	
	//测试
	public static void main(String[] args) throws FileNotFoundException {
		EdgeWeightDiGraph G = new EdgeWeightDiGraph(new FileInputStream("src\\tinyEWD.txt"));
		DijkstraSP sp = new DijkstraSP(G, 0);
		System.out.println("0到节点1的最短路径：");
		for(int t:sp.pathTo(1)) {
			System.out.print(t + " -- ");
		}
	}
}
