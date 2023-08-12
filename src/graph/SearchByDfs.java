package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//ͨ��ǰ���quick union����ͼ����ͨ�� �����ѣ���Ҳ�����ף���;������һ�δ���ȥ������һ�飬����ѧϰһ�����õ��㷨 
/**
 * 
 * ������ȱ��� �� dfs �������Թ����ݹ飬��Ҫ���һ�����飬marked���������ڱ���Ѿ����߹��Ľڵ�
 *
 */
public class SearchByDfs {

	private boolean[] marked; // �Ƿ񵽹��ڵ�
	private int count; // ��ͨ�Ľڵ���

	public SearchByDfs(Graph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}

	/**
	 *          //����Ǵ���
	 * @param G   ͼ
	 * @param s   ��Ҫ������ͨ�ڵ�Ľڵ�
	 * 
	 *   ���㷨���ģ�������
	 *   ������ʲô �� ���������Թ��������õ����� adj(s)��������������������ͨ���ĵط����ú��ó���һ���ط����߹�ȥ
	 *                 ��Ȼ�����ڲ�ݹ����ȱ��������ҵ����ˣ����ó��ڶ����ط������п���ͨ��ĵط������ó���һ����������ߣ�ֱ��
	 *                 ����ط����ұ�ǹ��ˣ��Ѿ������ˣ����ҾͲ���������򣬷���
	 *   ������һ����V -W ʱ��
	 *                 1.Ҫô W û���ݹ����û�б���ǣ��Ǿ͵ݹ���ã��߹�ȥ
	 *                 2.Ҫô W �Ѿ�����ǹ��� ���������λ�ã����� ��return
	 *                 3.���û����ǹ����ڶ��δ���һ������ W - V ������ʱ�򣬿϶������������ߣ���Ϊ V �϶��Ѿ������ʹ���
	 */
	public void dfs(Graph G, int s) {
		marked[s] = true; // �Ȱѽڵ���
		count++;
		for (int t : G.adj(s)) {
			if (!marked[t]) {
				dfs(G, t);
			}
		}
	}

	public boolean marked(int w) {
		return marked[w]; // �Ϳ�dfs������ʱ����û�е�������ڵ㣬û�����Ǿ�˵������ͨ
	}

	public int count() {
		return count;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Graph graph = new Graph(new FileInputStream("src\\graphTest.txt")) ; 
		SearchByQuickUnion search = new SearchByQuickUnion(graph, 0);
		System.out.println("0��1�Ƿ���ͨ��"+search.marked(1));
		System.out.println("��0��ͨ��������" +search.count());                   //ok����ȫ��ʵ��
	}
}
