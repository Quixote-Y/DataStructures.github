package diGraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//����������������Ķ������� ʵ����������    ����������ǰ�к������һ��
/*
 * ������ȱ����㷨���ݹ�ʱ�����еĶ���֮����һ�Σ� ����� dfs �����Ķ������һ�����ݽṹ�� ���ǾͿ��Եõ����еĶ���
 *    ������˳��ȡ���� ������ݽṹ�����ʣ�ջ�����У����Լ��ݹ����֮ǰ����֮�󱣴�
 */
public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre;     //���ж����ǰ������
	private Queue<Integer> post;    //��������
	private Stack<Integer>  reservePost; //���������
	
	public DepthFirstOrder(DiGraph G) {
		pre = new LinkedList<>();
		post = new LinkedList<>();
		reservePost = new Stack<>();
		marked = new boolean[G.V()];
		
		for(int i =0 ;i<G.V();i++) {
			if(!marked[i]) {
				dfs(G,i);
			}
		}
		
	}
	
	//dfs
	public void dfs(DiGraph G,int s) {
		pre.add(s);              //ǰ�����
		
		marked[s] = true;
		for(int t : G.adj(s)) {
			if(!marked[t]) {
				dfs(G,t);
			}
		}
		
		post.add(s);           //�������
		reservePost.push(s);     //�������ջ
	}
        //��������ͽ�����
	
	public Iterable<Integer> pre(){
		return pre;
	}
	public Iterable<Integer> post(){
		return post;
	}
	public Iterable<Integer> reservePost(){
		return reservePost();
	}
}
