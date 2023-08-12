package edgeWeightGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
//克鲁斯卡尔最小生成树算法 ：思路是将所有的边按权重排入优先队列，然后每次从优先队列里取出权重最小的边，
//                     检验边是否会和之前挑出来的边形成环，形成环那就说明这条边是失效的，直到取出V-1条合格的边
//                     他们的连通图就是一个最小生成树
// 数据结构： 一个队列来保存最小生成树的所有边，       一条优先队列保存未被检查的边
//                 一个union-find数据结构来检验 是否会成环
public class KruskalMST {

	private Queue<Edge> mst ;         //队列，存放最小生陈述的边
	
	public KruskalMST(EdgeWeightGraph G) {
		mst = new LinkedList<>();
		//优先队列，存放所有的为比较边,由小到大
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
		    @Override
		    public int compare(Edge e1, Edge e2) {
		        // 比较权值
		        int weightComparison = e1.compareTo(e2);
		        if (weightComparison != 0) {
		            return weightComparison;
		        }else {
		        	return 0;
		        }
		    }
		});
		for(Edge t: G.Edges()) {
			pq.add(t);
		}
		//初始化UF
		UF uf = new UF(G.V());
		
		//循环取最小边，判断是否成环
		while(!pq.isEmpty()&& mst.size()<G.V()-1) {
			Edge tmp = pq.poll();
			int either  = tmp.either();
			int other = tmp.other(either);
			
			//判断连个点是否在树里，如果在已经在树里，那么他们肯定是联通的
			if(uf.connected(either, other))continue;
			//没有连通，那就连起来
			uf.union(either, other);
			mst.add(tmp);
		}
		
	}
	
	public Iterable<Edge> edges(){
		return mst;
	}
	
	public String toString() {
		String str ="";
		for(Edge t: edges()) {
			str = str+t.toString()+"\n";
		}
		return str;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		EdgeWeightGraph EWG = new EdgeWeightGraph(new FileInputStream("src\\tinyEWG.txt"));
		KruskalMST kM = new KruskalMST(EWG);
		System.out.println(kM.toString());             //这个简单多了
	}
}


//在写一遍quick - union
class UF{
	private int[] id;
	private int count ;
	private int[] size; //是连通分量中的节点数，代指高度
	
	public UF(int n) {
		count = n;
		id = new int[n];
		size = new int[n];
		//初始化每个节点的父节点都是自己
		for(int i=0;i<n;i++) {
			id[i] = i;
		}
	}
	
	//返回根节点
	public int find(int v) {
		while(id[v] != v) {
			v = id[v];
		}
		return v;
	}
	
	public void union(int a,int b) {
		if(connected(a,b)) {
			return ;
		}
		int aRoot = find(a);
		int bRoot = find(b);
		if(size[aRoot]>size[bRoot]) {
			id[bRoot] = aRoot;
			size[aRoot]+= size[bRoot];
		}else {
			id[aRoot] = bRoot;
			size[bRoot]+=size[aRoot];
		}
		//连通分量数减少
		count--;
	}
	
	public boolean connected(int a, int b) {
		return find(a) == find(b);
	}
	
	public int count() {
		return count;
	}
}