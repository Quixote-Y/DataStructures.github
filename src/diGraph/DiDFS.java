package diGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

//����ͼ��������ȱ���
/*
 * ����ɴ���     ���ɴ��� 
 */
public class DiDFS {

	private boolean[] marked;
	
	//����ɴ�
	public DiDFS(DiGraph G ,int s) {
		marked = new boolean[G.V()];
		dfs(G,s);
	}
	
	 //���ɴ����һ���ɵ������󣬶����������ֵ����������dfs��
	//ʹ�������ܹ��жϸ�����һ����һ��ڵ��ܵ�����Щ��������
	public DiDFS(DiGraph G,Iterable<Integer> source) {
		marked = new boolean[G.V()];
		
		for(int t: source) {
			if(!marked[t]) {
				dfs(G,t);
			}
		}
	}
	
	//dfs
	public void dfs(DiGraph G ,int s) {
		marked[s] = true;
		
		for(int t: G.adj(s)) {
			if(!marked[t]) {
				dfs(G,t);
			}
		}
	}
	//����Ƿ�ɴ�
	public boolean marked(int v) {
		return marked[v];
	}
	public static void main(String[] args) throws FileNotFoundException {
		DiGraph G = new DiGraph(new FileInputStream("src\\tinyDG.txt"));
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		stack.push(1);
		stack.push(2);
		stack.push(3);
		DiDFS reach = new DiDFS(G,stack);//����һ��ջ��������0��1��2��3���������ܵ�����Щ�ڵ�
		for(int i = 0 ;i<G.V();i++) {
			System.out.print(i+"->"+ reach.marked(i)+"\t");
		}            //ok
	}
}
