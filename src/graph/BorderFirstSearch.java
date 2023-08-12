package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

//������ȱ���
//������ȱ�����һֱ����ߣ��ߵ��߲����ٻ�ͷ              --�ݹ飬ջ
//������ȱ������Ȱ����н��������꣬Ȼ������Զһ����λ��    ---����������
//���ߴ洢���ݵ����ݽṹ��ͬ
public class BorderFirstSearch {

	private boolean[] marked; // ��ʶ
	private int[] edgeTo; // �ڵ�i�ĸ��ڵ���˭ �����·����
	private int s; // ���ڵ�
	private int count; // �͸��ڵ���ͨ�Ľڵ���

	public BorderFirstSearch(Graph G, int s) {
		// ���캯����������к�s��ͨ�Ľڵ㣬������ȱ���ȥ�ҵ���sΪ��㵽������ͨ�ڵ�����·����
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		// ����
		this.s = s;
		bfs(G, s);
	}

	// bfs
	public void bfs(Graph G, int s) {
		// ����һ�����У������洢Ҫ���ʵĽڵ�
		LinkedList<Integer> queue = new LinkedList<>();
		marked[s] = true;
		queue.add(s); // �����������
		while (!queue.isEmpty()) {
			// ������Ϊnull����
			int v = queue.remove(); // �Ƴ���ͷԪ��
			for (int t : G.adj(v)) {
				// ��ȡ�ͽڵ�vֱ�������Ľڵ�
				if (!marked[t]) {
					// û�б����
					marked[t] = true; // ���
					edgeTo[t] = v; // ˵����һ�ε��ڵ�t����һ���ڵ���v
					// ���
					queue.add(t); // ����β
				}
			}
		}
	}

	// ��ͨ�Ƿ�
	public boolean hasPath(int v) {
		return marked[v] == true;
	}

	// ���·��
	public Stack<Integer> pathTo(int v) {
		// ����һ����������������������s��v��·��
		if (marked[v] == false) {
			return null;
		}
		Stack<Integer> path = new Stack<>();
		for (int i = v; i != s; i = edgeTo[i]) {
			path.push(i);
		}
		path.push(s);
		return path;
	}

	// ����
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Graph graph = new Graph(new FileInputStream("src\\graphTest.txt")); // ��ͼ����
		BorderFirstSearch bfs = new BorderFirstSearch(graph, 0);
		
		System.out.println("�ڵ�0��11�Ƿ���ͨ��"+bfs.hasPath(11));
		System.out.println("�ڵ�0�ͽڵ�3֮������·����");
		Stack<Integer> path = bfs.pathTo(3);
		while(!path.isEmpty()) {
			int v = path.pop();
			System.out.print(v+"-- ");
		}
	}
}
/*
 * ��Java�У�ʵ�������п���ʹ�ö������ͣ����������������LinkedList��ArrayDeque��
 * 
 * LinkedList��Java���Ͽ���е�һ���࣬ʵ����List��Deque�ӿڣ������������С�ջ��˫����С�
 * LinkedList��������ʵ�֣���˲����ɾ��Ԫ�صĸ��Ӷ�ΪO(1)��������Ԫ�صĸ��Ӷ�ΪO(n)��
 * LinkedList�ʺ�����Ƶ�����в����ɾ�������ĳ�����
 * 
 * ArrayDequeҲ��Java���Ͽ���е�һ���࣬ʵ����Deque�ӿڣ������������л�ջ��ArrayDeque��������ʵ�֣���˷���Ԫ�صĸ��Ӷ�ΪO(1
 * )�� �������ɾ��Ԫ�صĸ��Ӷ�ΪO(n)��ArrayDeque�ʺ�������Ҫ���ٷ���Ԫ�صĳ�����
 * 
 * ��Ҫע����ǣ�Java�е�Queue�ӿ���һ���ӿڣ�����ֱ��ʵ��������Ҫʹ�þ��������ʵ����һ�����ж�������LinkedList��ArrayDeque��
 * ͬʱ�����ݶ��е�����;��峡����������ʹ������������ʵ�������У�����PriorityQueue��ConcurrentLinkedQueue�ȡ�
 */
