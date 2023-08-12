package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;


//ͼ�ı�ʾ�ṹ�ͳ���api  ---����д�����ڽӱ�ķ�ʽ
public class Graph {
	
	private int V;           //������
	private int E ;          //����
	private LinkedList<Integer>[] adj;      //�ڽӱ�
	
	public Graph(int V) {
		//����һ��ֻ��V�������ͼ
		this.V = V;
		adj = new LinkedList[V];
		//��ʼ��
		for(int i=0; i<V ;i++) {
			adj[i] = new LinkedList<>();      //��̬���ݣ����Բ���Ҫ�ֶ�ָ����С
		}
	}
	public Graph(InputStream in) throws IOException {
		//ָ������������ȡ�������������V��E��Ȼ����һ�������ԣ���ʼ�������
		Scanner scanner = new Scanner(in);
		this.V = scanner.nextInt();
		adj = new LinkedList[V];
		//��ʼ��
		for(int i=0; i<V ;i++) {
			adj[i] = new LinkedList<>();      //��̬���ݣ����Բ���Ҫ�ֶ�ָ����С
		}
		int E2 = scanner.nextInt();              //E2����E
		//��ӱ�
		int v =0;
		int e = 0;
		for(int i =0;i<E2;i++) {
			v = scanner.nextInt();
			e = scanner.nextInt();
			addEdge(v,e);
		}
	}
	
	public void addEdge(int v,int e) {
		adj[v].add(e);
		adj[e].add(v);//��Ϊ������ͼ���������߶�Ҫ���
		E++;
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
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		int V = 10;
		//Graph graph = new Graph(V);
		Graph graph = new Graph(new FileInputStream("src\\graphTest.txt")) ;      //ok�ɹ�
		//��һ�����һ����
		graph.addEdge(10, 0);
		System.out.println(graph.toString()) ;
		//.Search search = new Search(graph);
	}

}
