package edgeWeightGraph;
//�ߣ����ڴ洢�ߵ�Ȩ�غͱߵ������˵�
public class Edge implements Comparable<Edge>{//ʵ��Comparable�ӿڣ�ʵ��compareTo����
	private int v;            //����֮һ
	private int w;            //��һ������
	private double weight;    //Ȩ��
	
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
			throw new RuntimeException("�޶�Ӧ��");
		}
		return this.v==v ? w:v; 
	}
	/**
	 * �� this ���� that ��Ȩ�رȽ� 
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
