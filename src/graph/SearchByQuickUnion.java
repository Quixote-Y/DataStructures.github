package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//��ͼ������У�ͨ����ͼ�������ͼ�ı�ʾ�ֿ���������ͬ����
//ʹ��quick union��ʵ��search�࣬�������ͨ�ԣ�
public class SearchByQuickUnion {	

	private UF uf ;
	private int sRoot ; 
	public SearchByQuickUnion(Graph G , int s) {
		//���캯�����ҵ����к����s��ͨ�Ķ���
		uf = new UF(G.V());        //��ȡͼ�Ķ�����������UF���� ---����ʼ��UF�������
		//G.adj(s);     -->��s���ڵ����ж���,���ص���һ��������
		for(int i =0 ;i<G.V();i++) {
			for(int t:G.adj(i)) {
				//��ȡ��i���ڵ����ж���,���ھ���ͨ ���ǾͰ�����union
				if(uf.connected(i, t)) {
					continue; //����Ѿ��������Ǿͼ���
				}
				uf.union(i, t);
			}
		}
		//quick union �ͽ��������
		sRoot = uf.find(s);
	}
	
	//�ж� v �� s�Ƿ���ͨ
	public boolean marked(int v) {
		return uf.connected(sRoot, v);
	}
	
	//��s���������ж�������
	public int count() {
		return uf.szFind(sRoot);
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Graph graph = new Graph(new FileInputStream("src\\graphTest.txt")) ; 
		SearchByQuickUnion search = new SearchByQuickUnion(graph, 0);
		System.out.println("0��11�Ƿ���ͨ��"+search.marked(11));
		System.out.println("��0��ͨ��������" +search.count());                   //ok����ȫ��ʵ��
	}
}

class UF {
	//�ڻ���һ��quick' union ����ʹ��һ����������飬�����е����ж������Ӧ��Ȼ�� ����������鴴��һ����ͨɭ�֣���3��2��ͨ���ǾͰ� id��3�� = 2��ʹ
		// 3 �ĸ��ڵ���2  --�߼���
		private int[] id;       //����id
		private int count ;      //��������
		private int[] sz ;        //��������ʵ�ּ�Ȩquick union�ģ���¼ÿ����ͨ������ڵ���������ýڵ������������ĸ߶�
		
		public UF(int n) {
			count = n;
			id = new int[n];
			sz = new int[n];
			//ÿ����ʼ������ָ���Լ�
			for(int i=0;i<n;i++) {
				id[i] = i;
				sz[i] = 1;
			}
			
		}
		
		public int count() {
			return count;            //count��¼�����м�������Ҳ�����м�������ͨ�Ĳ���
		}
		
		public boolean connected(int p,int q) {
			//�ж� p �� q �Ƿ�����
			return find(p)==find(q);
		}
		
		//���ģ� find���� �� union����
		public int find(int p) {
			//�ҵ��ڵ� p �ĸ��ڵ� �����ڵ�϶��� id��i��=i
			while(id[p]!=p) {
				p = id[p];   //�����ķ�����
			}
			return p;
		}
		
		public void union(int p,int q) {
			//��p �� q ���������� ����Ȩ�� unionҪ�ж� p q�������Ĵ�С ��С���Ӵ��� ���ø߶Ȳ�����
			//����Ĭ��p ��q ������ͨ��
			int pRoot = find(p);
			int qRoot = find(q);
			if(sz[pRoot]>sz[qRoot]) {
				//p������С����q ,��q�����ӵ� p�����棬---->q�� id[q] = p ,��pRoot�ĸ��ڵ���Ϊ qRoot
				id[qRoot] = pRoot;
				//����sz[q]
				sz[pRoot]+= sz[qRoot];
			}else {
				id[pRoot] = qRoot;
				sz[qRoot]+= sz[pRoot];    //�������׳����������ӳ��ȵ��ǳ��Ľڵ㣬Ȼ��������ĸ��ڵ���Ƕ̵Ľڵ㣬�����϶����෴��
			}
			count--;
		}
		
		public int szFind(int p) {
			int root = find(p);
			return sz[root];
		}
}

