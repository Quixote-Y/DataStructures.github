package edgeWeightGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//Prim最小生成树算法延时实现
public class LazyPrimMST {
	private boolean[] marked;      //用于标记最小生成树中的节点
	private Queue<Edge> mst;       //最小生成树中的边
	private PriorityQueue<Edge> pq;   //存储横切边，包含已经失效的边（两个节点都加入了最小生成树）
	
	//构造函数，构造最小生成树
	public LazyPrimMST(EdgeWeightGraph G) {
		marked = new boolean[G.V()];
		mst = new LinkedList<>();
		pq = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO 自动生成的方法存根
				return o1.compareTo(o2);
			}
		});
		
		//传入的一定是一个联通图
		//使用一个visit（）方法来为 最小生成树加入一个节点 ，将其marked标记为true ，然后将 所有他链接未被标记的顶点的边 加入 优先队列 ，以保证横切边的完整性
		visit(G,0);
		while(!pq.isEmpty()) {
			//先去优先队列里取出权重最小的横切边来
			Edge tmp = pq.poll();
			//检查边是否失效--》看两端是否被标记
			int either = tmp.either();
			int other = tmp.other(either);
			if(marked[either] && marked[other]) {
				continue;//跳过失效的边
			}
			//没失效
			//先将边加入到最小生成树中
			mst.add(tmp);
			//横切边，优先队列
			if(!marked[either]) visit(G,either);
			if(!marked[other]) visit(G,other);
		}
	}
	
	//vist方法
	private void visit(EdgeWeightGraph G,int v) {
		//传入一个节点v，将他加入最小生成树，然后将  所有他链接未被标记的顶点的边 加入优先队列
		marked[v] = true;
		for(Edge t:G.adj(v)) {
			//检查另一个顶点是否被标记
			if(!marked[t.other(v)]) {
				//另一个顶点没有被标记
				pq.add(t);
			}
		}
	}
	
	//返回最小生成树中的边
	public Iterable<Edge> edges(){
		return mst;
	}
	//
	public String toString() {
		String str = "顶点数："+marked.length+"边数："+mst.size()+"\n";
		for(Edge t: mst) {
			str = str+t.toString()+"\n";
		}
		return str;
	}
	
	//测试
	public static void main(String[] args) throws FileNotFoundException {
		EdgeWeightGraph EWG = new EdgeWeightGraph(new FileInputStream("src\\tinyEWG.txt"));
		LazyPrimMST mst = new LazyPrimMST(EWG);
		System.out.println(mst.toString());
	}
}
