package edgeWeightGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//ʵʱ�����������ȶ��е�Prim��С�������㷨
public class PrimMST {
	private Edge[] edgeTo;      //edgeTo��i����¼�ڵ�i����ǰ��С����������С�ı�
	private double[] distTo;    //distTo[i] ��¼�ڵ�i����ǰ��С��������С�ıߵ�Ȩ��
	private boolean[] marked;  //����Ƿ������еĽڵ�
	private IndexMinPQ pq;     //�������ȶ��У����ڴ洢ʵʱ���������еĶ��㵽�ڽӶ����е���С�ıߣ�������Ϊ���ߵĶ���ţ�ֵΪ��Ȩ��
	private int MAX = 50;
	public PrimMST(EdgeWeightGraph G) {
		//���캯���������ҵ���С������
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		pq = new IndexMinPQ();
		
		//�Ȱ�Ĭ�ϵ�distTo�ߵĳ�����Ϊ���ֵ
		for(int i=0;i<G.V();i++) {
			distTo[i] = MAX;
		}
		//������0��0.0 ������������
		pq.insert(0, 0.0);
		distTo[0] = 0.0;
		while(!pq.isEmpty()) {
			visit(G,pq.delMin());//ÿ��ȡ��pq�������ȶ�����Ȩ����С�Ľڵ�������visit
		}
	}
	
	public void visit(EdgeWeightGraph G,int v) {
		marked[v] = true;
		int  other;
		for(Edge t : G.adj(v)) {
			other = t.other(v);
			if(marked[other]) {
				continue;        //�����һ���ڵ�Ҳ������ˣ��Ǿ�˵���Է��ڵ�Ҳ���������
			}
			//������ӵĶԶ˽ڵ�û�б���ǣ��ǾͱȽ�����distTo�м�¼����СȨ����ȣ����ڣ��ǾͲ�Ӱ��ԭ���ĵ��Զ˽ڵ����С�ߣ�С�ڣ��Ǿ͸���Ϊ������
			if(distTo[other] > t.weight()) {
				//���� ���нڵ㵽�ڵ�other��  ��С�� ��СȨ��
				edgeTo[other] = t;
				distTo[other] = t.weight();
				//�����������ȶ���
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
		System.out.println(pM.toString());               //ok,���ִ������������ȶ��еıȽ�����
	}

}

