package string;
//����dfa״̬����kmp�ַ���ƥ���㷨��ʵ��
public class KMPByDFA {
	private String pat; //ģʽ��
	private int[][] dfa; //״̬ת������
	
	public KMPByDFA(String pat) {
		//��ģʽ������DFA
		this.pat = pat;
		int M =pat.length();
		int R = 256; //ascal�ַ�����С
		dfa = new int[R][M];
		dfa[pat.charAt(0)][0]=1;  //��һ��ƥ��ɹ�״̬����Ϊ1������һ��Ҫ���壬dfa״̬��һ��������Ӧ����ascal�ַ����������е��ַ�����ʼ����
		                          //��0��Ҳ����״̬0����ֻ�к�ģʽ���ĵ�һ��ƥ���ϲŻ����״̬1
		for(int X =0,j=1;j<M;j++) {
			//j��ģʽ����ָ�� X��״̬��������λ�ã�����һֱ���Ǻ���� X�ĸ��·�ʽ
			//����dfa[����][X]���µ�״̬--������ƥ��ʱ�����ص�λ��
			for(int i=0;i<R;i++) {
				dfa[i][j] = dfa[i][X];
			}
			//��ƥ��ʱ��״̬ת�Ƶ���һ��״̬
			dfa[pat.charAt(j)][j] = j+1;
			//��������״̬
			X = dfa[pat.charAt(j)][X]; 
		}
	}
	
	public int search(String txt) {
		int i,j,N = txt.length() ,M = pat.length();
		for(i=0,j=0;i<N && j<M;i++) {
			j = dfa[txt.charAt(i)][j];
		}
		if(j == M)return i-M;      //�ҵ�ƥ�䣨����ģʽ����ĩβ״̬��
		else      return N;        //û�ҵ��������ı�����ĩβ��
	}
	
	
	public static void main(String[] args) {
		String pat = "ACACA";
		String txt = "ACAACAACAACACAAA";
		KMPByDFA kmp = new KMPByDFA(pat);
		System.out.println(txt);
		int offest = kmp.search(txt);
		for(int i =0;i<offest;i++) {
			System.out.print(" ");
		}
		System.out.println(pat);
	}                               //���̣�������Ⱑ

}
