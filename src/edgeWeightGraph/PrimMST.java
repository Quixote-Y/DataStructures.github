package edgeWeightGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//实时更新索引优先队列的Prim最小生成树算法
public class PrimMST {
	private Edge[] edgeTo;      //edgeTo【i】记录节点i到当前最小生成树的最小的边
	private double[] distTo;    //distTo[i] 记录节点i到当前最小生成树最小的边的权重
	private boolean[] marked;  //标记是否是树中的节点
	private IndexMinPQ pq;     //索引优先队列，用于存储实时的所有树中的顶点到邻接顶点中的最小的边，索引键为：边的顶点号，值为：权重
	private int MAX = 50;
	public PrimMST(EdgeWeightGraph G) {
		//构造函数，用来找到最小生成树
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		pq = new IndexMinPQ();
		
		//先把默认的distTo边的长度设为最大值
		for(int i=0;i<G.V();i++) {
			distTo[i] = MAX;
		}
		//将顶点0，0.0 加入优先索引
		pq.insert(0, 0.0);
		distTo[0] = 0.0;
		while(!pq.isEmpty()) {
			visit(G,pq.delMin());//每次取出pq索引优先队列里权重最小的节点来进行visit
		}
	}
	
	public void visit(EdgeWeightGraph G,int v) {
		marked[v] = true;
		int  other;
		for(Edge t : G.adj(v)) {
			other = t.other(v);
			if(marked[other]) {
				continue;        //如果另一个节点也被标记了，那就说明对方节点也在树里，跳过
			}
			//如果连接的对端节点没有被标记，那就比较他和distTo中记录的最小权重相比，大于，那就不影响原来的到对端节点的最小边，小于，那就更新为这条边
			if(distTo[other] > t.weight()) {
				//更新 树中节点到节点other的  最小边 最小权重
				edgeTo[other] = t;
				distTo[other] = t.weight();
				//更新索引优先队列
				if(pq.contains(other)) {
					pq.change(other, t.weight());
				}else {
					pq.insert(other, t.weight());
				}
			}
		}
	}
	
	public String toString() {
		String str = "";
		for(int i=1;i<edgeTo.length;i++) {
			str = str + edgeTo[i].toString()+"  "+ distTo[i]+"\n";
		}
		return str;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		EdgeWeightGraph EWG = new EdgeWeightGraph(new FileInputStream("src\\tinyEWG.txt"));
		PrimMST pM = new PrimMST(EWG);
		System.out.println(pM.toString());               //ok,出现错误在索引优先队列的比较器处
	}

}

