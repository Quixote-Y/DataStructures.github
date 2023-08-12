package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//��ͨ����
public class ConnectedComponent {
/*
 * ʹ��markde������Ѱ��ÿһ��������Ϊdfs�Ķ��㣬�Ա�����е���ͨ����
 * ʹ��id ���飬��ͬһ����ͨ�����ı�ʶ������������0�ǵ�һ����ͨ�����еĽڵ㣬1����һ����������Ҫ��Ϊmarked��boolean������
 */
	
	private boolean[] marked;
	private int[] id;
	private int count;       //��ͨ������
	
	public ConnectedComponent(Graph G) {
		//���캯������ȡ���е���ͨ����id           ---->��ͼ����Ԥ����
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for(int i = 0;i<G.V();i++) {
			if(!marked[i]) {
				//ÿ��ѭ���ҵ���һ��û�б���ǵĽڵ㣬��Ϊ��һ��dfs�����
				dfs(G,i);
				count++;
			}
		}
		
	}
	private void dfs(Graph G,int s) {
		marked[s] = true;
		id[s] = count;              //����
		for(int t : G.adj(s)) {
			//��ȡ��s���ڵ����нڵ㣬Ȼ��һ��һ��ȥ�������
			if(!marked[t]) {
				//û�б����
				dfs(G,t);
			}
		}
	}
	
	//��������ڵ��Ƿ���ͨ
	public boolean connected(int a,int b) {
		if( id[a]==id[b]) {
			return true;
		}else {
			return false;
		}
	}
	//��ͨ������
	public int count() {
		return count;
	}
	//�ڵ�v����ͨ��ʶ��
	public int id(int v) {
		return id[v];
	}
	
	//test
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Graph G = new Graph(new FileInputStream("src\\graphTest.txt"));
		ConnectedComponent cc = new ConnectedComponent(G);
		//�������ܻ�ȡÿһ����ͨ������Ȼ������ǵ���������
		System.out.println(cc.count());
		System.out.println(cc.id(0));
		System.out.println(cc.connected(1, 11));      //ok
	}
}
