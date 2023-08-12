package diGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

//有向图 注意区分有向图的可达性和无向图的连通性的区别
/*
 * 有向图的api于无向图基本相似，不过就增加了一个
 *   reserve（）将有向图反转：这样可以找出 谁指向了 我
 *                                       adj是 我 指向了 谁
 */
public class DiGraph {
	
	private int V;                        //顶点数
	private int E;                       //边数
	private LinkedList<Integer>[] adj;    //邻接表
	
	//构造函数，一个参数
	public DiGraph(int v) {
		//构造一个v个顶点，不含边的图
		V = v;
		adj = new LinkedList[v];
		for(int i=0;i<v;i++) {
			adj[i] = new LinkedList<>();
		}
	}
	
	//传入流文件构造图
	public DiGraph(InputStream in) {
		Scanner scanner = new Scanner(in);
		V = scanner.nextInt();
		adj = new LinkedList[V];
		for(int i=0;i<V;i++) {
			adj[i] = new LinkedList<>();
		}
		int E2 = scanner.nextInt();
		//添加边
		int start ,end;
		for(int i =0;i<E2;i++) {
			start = scanner.nextInt();
			end = scanner.nextInt();
			addEdge(start,end);
		}
	}
	
	//添加边
	public void addEdge(int v,int w) {
		//v--->w
		adj[v].add(w);
		E++;     //边数更新别忘了
	}
	
	// 返回值改为 Iterable<Integer>     //返回的是一个接口类型，而不是一个类，如果返回Iterator类，那么就会类型转换错误
	public Iterable<Integer> adj(int v) {
	    return adj[v];
	}
	/*
	 * LinkedList 类实现了 Iterable 接口，因此可以将 LinkedList 类型的对象赋值给 Iterable 类型的变量，
	 * 同时也可以在方法的返回值中使用 Iterable 类型。这样做的好处在于，可以让方法返回一个更加通用的类型，
	 * 而不是具体的实现类，从而提高了代码的灵活性和可复用性。

      Iterable 接口是 Java 中用于表示可迭代对象的接口，它包含一个抽象方法 iterator()，返回一个迭代器对象。
      实现了 Iterable 接口的类可以被 foreach 循环遍历，或者使用迭代器进行遍历。
	 */

	// 图反转生成副本图
	public DiGraph reverse() {
	    DiGraph R = new DiGraph(V);
	    for (int v = 0; v < V; v++) {
	        for (int w : adj(v)) {  // 使用 foreach 循环遍历邻接表
	            R.addEdge(w, v);  // 反转边的起点和终点
	        }
	    }
	    return R;
	}
	
	
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public String toString() {
		String s = V + "个顶点，" +E + "条边\n";
		for(int i=0 ;i<V;i++) {
			s+= i + " ";
			for(int t : adj[i]) {
				s+=t + " ";
			}
			s+="\n";
		}
		return s;
	}
	
	
	//test
	public static void main(String[] args) throws FileNotFoundException {
		DiGraph DG = new DiGraph(new FileInputStream("src\\tinyDG.txt"));
		System.out.println(DG.toString());
		DiGraph R = DG.reverse();
		System.out.println("==========反转后=================");
		System.out.println(R.toString());                //ok
	}
	

}
