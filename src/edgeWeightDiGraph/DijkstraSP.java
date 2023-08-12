package edgeWeightDiGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

//�Ͻ�˹�������·���㷨 ---��ȨֵΪ��
/*
 * �Ͻ�˹�����㷨�� Prim��С����������ʱ�棩 ���㷨�ṹ��һ����
 * ����˵�ں���һ����
 * Prim �㷨 ÿ����� �Ķ�������С����������ķ����ڵ�
 *      distTo[w]:����w�������ڵ���С��������̵�·��
 *      edgeTo[w]:���·����Ӧ�ı�
 *      pq : �������ȶ��У���ʹ���˺ܼ򵥵�hashMap�����ȶ���PriorityQueue��ʵ�֣�������һЩϸ�ڣ�
 *                      ���ȣ��������ȶ��е����ȶ����ŵ��ǣ�Ȩ�أ���Ȼ��hashMAp���±�,Ȩ�أ������ķ�ʽ�������Ԫ��ʱ������ӣ����Ƿ����
 *                            ĳ���ڵ㣬��ȥhahsMap����Ƿ��ж�Ӧ���±꣬ɾ���ڵ���Ҫ �������ȶ��е���Сֵ��Ȼ�����map���ҵ���Ӧ��ֵ��Ȼ�󷵻�key��ɾ��������¼
 *      ������visit��v�����������ʣ���Ƕ���v����鶥��v�������ڽӵ㣬����ǵľͺ��ԣ�û����ǵ�˵����û�м����������v��w��Ȩ��
 *                           �Ƿ�С�����ڵ�w��Ȩ�أ���һ�������ٽֵĵ㵽����Ȩ�أ��������С�ڣ��ǲ����£����С�ڣ��Ǿ�
 *                           ����distTo[w]��ֵ��Ȩ�أ���Ȼ�����edgeTo[w]�ıߣ�����Ϊ�Լ���
 *                           Ȼ��ܹؼ��ģ��������ȶ������ֵ��������ȶ�������������㣬�Ǿ͸���������СȨֵ��û�������ͼ���
 * DijkstraSP �㷨 ÿ����ӵĶ��� �������� �ķ����ڵ�
 *     distTo[w]:����w�����Ŀǰ�����·��
 *     edgeTo[w]:��Ӧ�������·�����ڵ�w�ĸ��ڵ���˭ �������union-find�е�id[]����____---->�������ҵ���������ƫ�������һ��DirectedEdge������飬�洢���Ƕ�Ӧ�������ߣ���һЩ���룬���÷�Χ����
 *     pq �� �������ȶ��� ��������һ����������׸��
 *     ������relax��G��v������,���ɣ���鶥��v�������ڽӵ㣬������ĶԶ˽ڵ�w������distTo[w]�Ƿ����distTo[v]+e.weight,�������
 *                                  ˵����֮ǰ���ڵ�w������·����������Ȩֵ���ڴ�v��w������·����Ȩֵ���Ǿ͸���distTo[w]ΪС��Ȩֵ
 *                                  Ȼ�����w��edgeTo[w]��ֵΪv--->���ڵ���£� �����С���ǾͲ������仯
 *                                  Ȼ��Ҳ�Ǻܹؼ��ģ��������ȶ���   
 *
 */
public class DijkstraSP {

	private int[] edgeTo;          //�ߣ�����union-find��·����¼
	private double[] distTo;         //Ȩ�غͣ���¼��㵽��ǰ�ڵ�����·��Ȩ��
	private IndexMinPQ pq;         //�������ȶ��У�ʵʱȡ�������ڽӵ��������������ڸ��ڵ�
	private int root;             //���ڵ�
	private final int MAX=100;
	
	public DijkstraSP(EdgeWeightDiGraph G,int v) {
		//���캯���������ڵ�v�� ͼG �е����нڵ�����·��
		edgeTo = new int[G.V()];
		distTo = new  double[G.V()];
		pq = new IndexMinPQ();
		root = v;
		//��ʼ�� ���ڵ� ���룬
		for(int i=0;i<G.V();i++) {
			edgeTo[i] = i;
			distTo[i] = MAX;
		}
		
		//�������
		distTo[v] = 0.0;
		//���������ȶ���
		pq.insert(v, 0.0);//����Ȩֵ�϶�ʱ0��0
		//��ʼ�ɳ�
		while(!pq.isEmpty()) {
			relax(G,pq.delMin());
		}
	}
	
	//�ɳ�
	public void relax(EdgeWeightDiGraph G,int v) {
		/*��鶥��v�������ڽӵ㣬������ĶԶ˽ڵ�w������distTo[w]�Ƿ����distTo[v]+e.weight,�������
		 *    ˵����֮ǰ���ڵ�w������·����������Ȩֵ���ڴ�v��w������·����Ȩֵ���Ǿ͸���distTo[w]ΪС��Ȩֵ
		 *    Ȼ�����w��edgeTo[w]��ֵΪv--->���ڵ���£� �����С���ǾͲ������仯
		 *    Ȼ��Ҳ�Ǻܹؼ��ģ��������ȶ���   
         */
		for(DirectedEdge t: G.adj(v)) {
			int to = t.to();
			if(distTo[to] > distTo[v]+t.weight()) {
				//ԭ������Ȩֵ�������ڵģ�����Ȩֵ
				distTo[to] = distTo[v]+t.weight();
				//����·��
				edgeTo[to] = v;
				//�������ȶ���
				if(pq.contains(to)) {
					pq.change(to, distTo[to]);
				}else {
					pq.insert(to, distTo[to]);
				}
			}
		}
	}
	
	//һЩСapi
	/**
	 * ������v�����·��Ȩֵ
	 * @param v
	 * @return
	 */
	public double disTo(int v) {
		return distTo[v];
	}
	/**
	 * �Ƿ���ͨ����·������ڵ�v
	 * ����ʹ��marked��������ʶ�Ƿ�ڵ㱻���ʹ������ĳ���ڵ��distTo�� MAX����˵������޷�����ýڵ㣬����ͨ
	 * @param v
	 * @return
	 */
	public boolean hasPath(int v) {
		return distTo[v]<MAX;
	}
	/**
	 * ���ؿɵ������󣬵���v��·��
	 * @param v
	 * @return
	 */
	public Iterable<Integer> pathTo(int v){
		if(!hasPath(v))return null;
		Stack<Integer> stack = new Stack<>();
		for(int i = v;i != edgeTo[i];i = edgeTo[i]) {
			//��ѯ·���ķ�ʽ������һ�����Ҹ��ڵ㣬���ڵ��ֵ���Լ�
			stack.push(i);
		}
		stack.push(root);
		return stack;
	}
	
	
	
	//����
	public static void main(String[] args) throws FileNotFoundException {
		EdgeWeightDiGraph G = new EdgeWeightDiGraph(new FileInputStream("src\\tinyEWD.txt"));
		DijkstraSP sp = new DijkstraSP(G, 0);
		System.out.println("0���ڵ�1�����·����");
		for(int t:sp.pathTo(1)) {
			System.out.print(t + " -- ");
		}
	}
}
