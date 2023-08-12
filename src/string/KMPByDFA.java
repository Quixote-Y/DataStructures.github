package string;
//基于dfa状态机的kmp字符串匹配算法的实现
public class KMPByDFA {
	private String pat; //模式串
	private int[][] dfa; //状态转移数组
	
	public KMPByDFA(String pat) {
		//由模式串构造DFA
		this.pat = pat;
		int M =pat.length();
		int R = 256; //ascal字符集大小
		dfa = new int[R][M];
		dfa[pat.charAt(0)][0]=1;  //第一个匹配成功状态设置为1，这里一定要理清，dfa状态第一个参数对应的是ascal字符集，是所有的字符，初始化都
		                          //是0（也就是状态0），只有和模式串的第一个匹配上才会进入状态1
		for(int X =0,j=1;j<M;j++) {
			//j是模式串的指， X是状态机重启的位置，这里一直不是很理解 X的更新方式
			//复制dfa[所有][X]到新的状态--》当不匹配时，返回的位置
			for(int i=0;i<R;i++) {
				dfa[i][j] = dfa[i][X];
			}
			//当匹配时，状态转移到下一个状态
			dfa[pat.charAt(j)][j] = j+1;
			//更新重启状态
			X = dfa[pat.charAt(j)][X]; 
		}
	}
	
	public int search(String txt) {
		int i,j,N = txt.length() ,M = pat.length();
		for(i=0,j=0;i<N && j<M;i++) {
			j = dfa[txt.charAt(i)][j];
		}
		if(j == M)return i-M;      //找到匹配（到达模式串的末尾状态）
		else      return N;        //没找到（到达文本串的末尾）
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
	}                               //沃焯，。不理解啊

}
