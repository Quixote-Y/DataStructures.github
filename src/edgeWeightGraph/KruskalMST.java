package edgeWeightGraph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
//��³˹������С�������㷨 ��˼·�ǽ����еı߰�Ȩ���������ȶ��У�Ȼ��ÿ�δ����ȶ�����ȡ��Ȩ����С�ıߣ�
//                     ������Ƿ���֮ǰ�������ı��γɻ����γɻ��Ǿ�˵����������ʧЧ�ģ�ֱ��ȡ��V-1���ϸ�ı�
//                     ���ǵ���ͨͼ����һ����С������
// ���ݽṹ�� һ��������������С�����������бߣ�       һ�����ȶ��б���δ�����ı�
//                 һ��union-find���ݽṹ������ �Ƿ��ɻ�
public class KruskalMST {

	private Queue<Edge> mst ;         //���У������С�������ı�
	
	public KruskalMST(EdgeWeightGraph G) {
		mst = new LinkedList<>();
		//���ȶ��У�������е�Ϊ�Ƚϱ�,��С����
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
		    @Override
		    public int compare(Edge e1, Edge e2) {
		        // �Ƚ�Ȩֵ
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
		//��ʼ��UF
		UF uf = new UF(G.V());
		
		//ѭ��ȡ��С�ߣ��ж��Ƿ�ɻ�
		while(!pq.isEmpty()&& mst.size()<G.V()-1) {
			Edge tmp = pq.poll();
			int either  = tmp.either();
			int other = tmp.other(either);
			
			//�ж��������Ƿ������������Ѿ��������ô���ǿ϶�����ͨ��
			if(uf.connected(either, other))continue;
			//û����ͨ���Ǿ�������
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
		System.out.println(kM.toString());             //����򵥶���
	}
}


//��дһ��quick - union
class UF{
	private int[] id;
	private int count ;
	private int[] size; //����ͨ�����еĽڵ�������ָ�߶�
	
	public UF(int n) {
		count = n;
		id = new int[n];
		size = new int[n];
		//��ʼ��ÿ���ڵ�ĸ��ڵ㶼���Լ�
		for(int i=0;i<n;i++) {
			id[i] = i;
		}
	}
	
	//���ظ��ڵ�
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
		//��ͨ����������
		count--;
	}
	
	public boolean connected(int a, int b) {
		return find(a) == find(b);
	}
	
	public int count() {
		return count;
	}
}