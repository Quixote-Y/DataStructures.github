package edgeWeightGraph;
//边，用于存储边的权重和边的两个端点
public class Edge implements Comparable<Edge>{//实现Comparable接口，实现compareTo方法
	private int v;            //顶点之一
	private int w;            //另一个顶点
	private double weight;    //权重
	
	public Edge(int v,int w,double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public double weight() {
		return weight;
	}
	
	public int either() {
		return v;
	}
	public int other(int v) {
		if(v != this.v && v != w) {
			throw new RuntimeException("无对应边");
		}
		return this.v==v ? w:v; 
	}
	/**
	 * 将 this 边与 that 边权重比较 
	 * @param that
	 * @return
	 */
	public int compareTo(Edge that) {
		if(this.weight > that.weight) {
			return 1;
		}else if(this.weight < that.weight) {
			return -1;
		}else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return ""+v+"-->"+w+"("+weight+")";
	}
	
}
