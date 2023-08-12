package edgeWeightGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//Prim��С�������㷨��ʱʵ��
public class LazyPrimMST {
	private boolean[] marked;      //���ڱ����С�������еĽڵ�
	private Queue<Edge> mst;       //��С�������еı�
	private PriorityQueue<Edge> pq;   //�洢���бߣ������Ѿ�ʧЧ�ıߣ������ڵ㶼��������С��������
	
	//���캯����������С������
	public LazyPrimMST(EdgeWeightGraph G) {
		marked = new boolean[G.V()];
		mst = new LinkedList<>();
		pq = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO �Զ����ɵķ������
				return o1.compareTo(o2);
			}
		});
		
		//�����һ����һ����ͨͼ
		//ʹ��һ��visit����������Ϊ ��С����������һ���ڵ� ������marked���Ϊtrue ��Ȼ�� ����������δ����ǵĶ���ı� ���� ���ȶ��� ���Ա�֤���бߵ�������
		visit(G,0);
		while(!pq.isEmpty()) {
			//��ȥ���ȶ�����ȡ��Ȩ����С�ĺ��б���
			Edge tmp = pq.poll();
			//�����Ƿ�ʧЧ--���������Ƿ񱻱��
			int either = tmp.either();
			int other = tmp.other(either);
			if(marked[either] && marked[other]) {
				continue;//����ʧЧ�ı�
			}
			//ûʧЧ
			//�Ƚ��߼��뵽��С��������
			mst.add(tmp);
			//���бߣ����ȶ���
			if(!marked[either]) visit(G,either);
			if(!marked[other]) visit(G,other);
		}
	}
	
	//vist����
	private void visit(EdgeWeightGraph G,int v) {
		//����һ���ڵ�v������������С��������Ȼ��  ����������δ����ǵĶ���ı� �������ȶ���
		marked[v] = true;
		for(Edge t:G.adj(v)) {
			//�����һ�������Ƿ񱻱��
			if(!marked[t.other(v)]) {
				//��һ������û�б����
				pq.add(t);
			}
		}
	}
	
	//������С�������еı�
	public Iterable<Edge> edges(){
		return mst;
	}
	//
	public String toString() {
		String str = "��������"+marked.length+"������"+mst.size()+"\n";
		for(Edge t: mst) {
			str = str+t.toString()+"\n";
		}
		return str;
	}
	
	//����
	public static void main(String[] args) throws FileNotFoundException {
		EdgeWeightGraph EWG = new EdgeWeightGraph(new FileInputStream("src\\tinyEWG.txt"));
		LazyPrimMST mst = new LazyPrimMST(EWG);
		System.out.println(mst.toString());
	}
}
