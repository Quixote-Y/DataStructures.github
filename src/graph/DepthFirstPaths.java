package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

//����·���������������ȱ����Ļ����� �ҵ�ͼ��һ���㵽��һ�����·����
/*
 * ����quick union��ͼ�ı����㷨ֻ���ҳ���ͨ�Ľڵ������Ƿ���ͨ���޷��ҳ�����·��
 * ��DFS������ȱ���ֻ��Ҫ�򵥵����һ�����Ծ���ʵ��
 */
public class DepthFirstPaths { //���������������ͼ�е�·��
	private boolean marked[]; //�Ƿ��ǹ�
	private int[] edgeTo ;  //�������˺�quick unionһ����һ����������¼����ÿ�����㵽����·���������ǵ�ǰ�ڵ㵽����·����
	                          // ��ܹؼ� ��һ���� �У�edgeTo��i����¼���������Ҫȥ�����ڵ㣬������һ���ڵ�Ӧ�������ĸ��ڵ�
	private int s ;           //���
	
	public DepthFirstPaths(Graph G, int s) {
		//���캯�� �����ڽ���������ȱ��� ����ȡG�����к� s��ͨ�Ľڵ� ���ҵ��������Ϊs��·��
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G,s);
	}
	
	//������ȱ���
	public void dfs(Graph G ,int v) {
		marked[v] = true; //��ǽڵ�
		for(int t: G.adj(v)) {
			//adj���ص����ڽӱ��е�adj[v]��Ҳ�������к�vֱ�������Ľڵ�
			if(!marked[t]) {
				//�������ڵ�û�б���ǣ��Ǿͽ�ȥ
				//�ȼ�¼λ�ã�����һ��λ�õĸ��ڵ������ҵ�ǰ�ڵ�
				edgeTo[t] = v;         //һ���ڵ�ֻ���ܱ���ȥһ�Σ���һ�ν�ȥ��ͱ�����ˣ������ľͽ���ȥ��
				dfs(G,t);
			}
		}
	}
	
	public boolean hasPath(int v) {
		//�ڵ�v�� ���s�Ƿ���һ��·��
		return marked[v];
	}
	public Iterator<Integer> pathTo(int v){
		//����һ����������������s �� v��·�� ��һ��һ����
		if(!hasPath(v)) {
			return null; //û��·
		}
		Stack<Integer> path = new Stack<>();        
		for(int i =v ;i!=s ;i = edgeTo[i]) {
			//������·���������ڵ�ʱ��ֹͣ
			path.push(i);           //�ѽڵ�Ž�ջ��
		}
		path.push(s);          //��Ҳ�Ž�ȥ
		return (Iterator<Integer>) path;     
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Graph graph = new Graph(new FileInputStream("src\\graphTest.txt")) ; //��ͼ������
		DepthFirstPaths dfp = new DepthFirstPaths(graph, 0);
		
		System.out.println("�ڵ�0��10֮���Ƿ����·����"+dfp.hasPath(10));
		System.out.println("�ڵ�0��3֮���Ƿ����·����"+dfp.hasPath(3));
		
		Iterator it = dfp.pathTo(3);
		System.out.println("���ڵ�0���ڵ�3֮���·����");
		while(it.hasNext()) {
			int next = (int)it.next();
			System.out.println(next + "--");
		}
	}
	

}
