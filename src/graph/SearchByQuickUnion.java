package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//在图的设计中，通常将图的任务和图的表示分开，创建不同的类
//使用quick union来实现search类，（检查连通性）
public class SearchByQuickUnion {	

	private UF uf ;
	private int sRoot ; 
	public SearchByQuickUnion(Graph G , int s) {
		//构造函数，找到所有和起点s连通的顶点
		uf = new UF(G.V());        //获取图的顶点数，建立UF对象 ---》初始化UF里的数组
		//G.adj(s);     -->和s相邻的所有顶点,返回的是一个迭代器
		for(int i =0 ;i<G.V();i++) {
			for(int t:G.adj(i)) {
				//获取和i相邻的所有顶点,相邻就联通 ，那就把他们union
				if(uf.connected(i, t)) {
					continue; //如果已经相连，那就继续
				}
				uf.union(i, t);
			}
		}
		//quick union 就建立完成了
		sRoot = uf.find(s);
	}
	
	//判断 v 和 s是否联通
	public boolean marked(int v) {
		return uf.connected(sRoot, v);
	}
	
	//与s相连的所有顶点总数
	public int count() {
		return uf.szFind(sRoot);
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Graph graph = new Graph(new FileInputStream("src\\graphTest.txt")) ; 
		SearchByQuickUnion search = new SearchByQuickUnion(graph, 0);
		System.out.println("0和11是否连通："+search.marked(11));
		System.out.println("和0联通的数计数" +search.count());                   //ok功能全部实现
	}
}

class UF {
	//在回忆一下quick' union ，是使用一个额外的数组，和已有的所有顶点相对应，然后 利用这个数组创建一个联通森林，像3和2联通，那就把 id【3】 = 2，使
		// 3 的父节点是2  --逻辑上
		private int[] id;       //分量id
		private int count ;      //分量数量
		private int[] sz ;        //这是用来实现加权quick union的，记录每个联通分量里节点的数量，用节点数来近似树的高度
		
		public UF(int n) {
			count = n;
			id = new int[n];
			sz = new int[n];
			//每个初始化都是指向自己
			for(int i=0;i<n;i++) {
				id[i] = i;
				sz[i] = 1;
			}
			
		}
		
		public int count() {
			return count;            //count计录的是有几颗树，也就是有几个不连通的部分
		}
		
		public boolean connected(int p,int q) {
			//判断 p 和 q 是否连接
			return find(p)==find(q);
		}
		
		//核心， find（） 和 union（）
		public int find(int p) {
			//找到节点 p 的根节点 ，根节点肯定是 id【i】=i
			while(id[p]!=p) {
				p = id[p];   //往跟的方向走
			}
			return p;
		}
		
		public void union(int p,int q) {
			//将p 和 q 两部分连接 ，加权的 union要判断 p q所在树的大小 ，小树加大树 ，让高度不增加
			//这里默认p ，q 不是联通的
			int pRoot = find(p);
			int qRoot = find(q);
			if(sz[pRoot]>sz[qRoot]) {
				//p的树大小大于q ,将q子树加到 p的下面，---->q的 id[q] = p ,把pRoot的父节点设为 qRoot
				id[qRoot] = pRoot;
				//更新sz[q]
				sz[pRoot]+= sz[qRoot];
			}else {
				id[pRoot] = qRoot;
				sz[qRoot]+= sz[pRoot];    //这里容易出错，这里增加长度的是长的节点，然后上面给改父节点的是短的节点，两个肯定是相反的
			}
			count--;
		}
		
		public int szFind(int p) {
			int root = find(p);
			return sz[root];
		}
}

