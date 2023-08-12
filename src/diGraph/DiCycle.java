package diGraph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

//���򻷼��
/*  ���������ȼ������µĵ������⣬����Ƿ�������ִ��� ,���ֻ���ֻ�����л��������Ƕ��ٸ���
 * һ���ҵ�һ�� v--->w�ıߣ�w �Ѿ��ڵݹ�ջ�У���ô��������γ��� 
 */
public class DiCycle {

	private boolean[] marked;
	private int[] edgeTo;
	private boolean[] onStack; // ��¼�ݹ�ջ�ڵ����ж���
	private Stack<Integer> cycle; // ��������ڣ������е����ж���

	public DiCycle(DiGraph G) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		onStack = new boolean[G.V()];
		for (int i = 0; i < G.V(); i++) {
			if (!marked[i]) {
				dfs(G, i);
			}
		}
	}

	// dfs
	public void dfs(DiGraph G, int s) {
		onStack[s] = true;
		marked[s] = true;

		for (int t : G.adj(s)) {
			if (this.hasCycle()) {
				return;
			}
			if (!marked[t]) {
				edgeTo[t] = s;
				dfs(G, t);
			} else if (onStack[t]) {
				cycle = new Stack<>();
				for (int i = s; i != t; i = edgeTo[i]) {
					// i��s��ʼ����Ϊt�Ѿ���ջ���ˣ������Ҹ��ڵ��һص�tʱ���Ǿ�˵�������нڵ�ȫ������
					cycle.push(i);
				}
				cycle.push(t);
				cycle.push(s);
			}
		}

		// ����˵��һ�εݹ�����ˣ�Ҫ��stack��Ϊfalse��
		onStack[s] = false;
	}

	// hasCycle
	public boolean hasCycle() {
		return cycle != null;
	}
	
	//cycle
	public Iterable<Integer> cycle(){
		return cycle;
	}
 
	public static void main(String[] args) throws FileNotFoundException {
		DiGraph G = new DiGraph(new FileInputStream("src\\tinyDG.txt"));
		DiCycle cycle = new DiCycle(G);
		System.out.println(cycle.hasCycle());
		
		for(int t : cycle.cycle()) {
			System.out.print(t+"-->");
		}
	}

}
