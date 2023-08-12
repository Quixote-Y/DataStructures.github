package diGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

//����ͼ ע����������ͼ�Ŀɴ��Ժ�����ͼ����ͨ�Ե�����
/*
 * ����ͼ��api������ͼ�������ƣ�������������һ��
 *   reserve����������ͼ��ת�����������ҳ� ˭ָ���� ��
 *                                       adj�� �� ָ���� ˭
 */
public class DiGraph {
	
	private int V;                        //������
	private int E;                       //����
	private LinkedList<Integer>[] adj;    //�ڽӱ�
	
	//���캯����һ������
	public DiGraph(int v) {
		//����һ��v�����㣬�����ߵ�ͼ
		V = v;
		adj = new LinkedList[v];
		for(int i=0;i<v;i++) {
			adj[i] = new LinkedList<>();
		}
	}
	
	//�������ļ�����ͼ
	public DiGraph(InputStream in) {
		Scanner scanner = new Scanner(in);
		V = scanner.nextInt();
		adj = new LinkedList[V];
		for(int i=0;i<V;i++) {
			adj[i] = new LinkedList<>();
		}
		int E2 = scanner.nextInt();
		//��ӱ�
		int start ,end;
		for(int i =0;i<E2;i++) {
			start = scanner.nextInt();
			end = scanner.nextInt();
			addEdge(start,end);
		}
	}
	
	//��ӱ�
	public void addEdge(int v,int w) {
		//v--->w
		adj[v].add(w);
		E++;     //�������±�����
	}
	
	// ����ֵ��Ϊ Iterable<Integer>     //���ص���һ���ӿ����ͣ�������һ���࣬�������Iterator�࣬��ô�ͻ�����ת������
	public Iterable<Integer> adj(int v) {
	    return adj[v];
	}
	/*
	 * LinkedList ��ʵ���� Iterable �ӿڣ���˿��Խ� LinkedList ���͵Ķ���ֵ�� Iterable ���͵ı�����
	 * ͬʱҲ�����ڷ����ķ���ֵ��ʹ�� Iterable ���͡��������ĺô����ڣ������÷�������һ������ͨ�õ����ͣ�
	 * �����Ǿ����ʵ���࣬�Ӷ�����˴��������ԺͿɸ����ԡ�

      Iterable �ӿ��� Java �����ڱ�ʾ�ɵ�������Ľӿڣ�������һ�����󷽷� iterator()������һ������������
      ʵ���� Iterable �ӿڵ�����Ա� foreach ѭ������������ʹ�õ��������б�����
	 */

	// ͼ��ת���ɸ���ͼ
	public DiGraph reverse() {
	    DiGraph R = new DiGraph(V);
	    for (int v = 0; v < V; v++) {
	        for (int w : adj(v)) {  // ʹ�� foreach ѭ�������ڽӱ�
	            R.addEdge(w, v);  // ��ת�ߵ������յ�
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
		String s = V + "�����㣬" +E + "����\n";
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
		System.out.println("==========��ת��=================");
		System.out.println(R.toString());                //ok
	}
	

}
