package string;
//kmpͨ��ǰ׺��ʵ��
public class KMPbyNext {

	private int[] next; //next����
	private String pat;
	
	public KMPbyNext(String pat) {
		this.pat = pat;
		next = new int[pat.length()];
		next[0] =0;
		int i=0;
		
		for(int j=1;j<pat.length();j++) {
			while(i>0 && pat.charAt(i) != pat.charAt(j)) {
				i = next[i-1];
			}
			if(pat.charAt(i) == pat.charAt(j)) {
				i++;
				next[j] = i;
			}
		}
	}
	
	public int search(String txt) {
		int i,j;
		for(i=0,j=0;i<txt.length() && j<pat.length();i++) {
			if(txt.charAt(i) == pat.charAt(j)) {
				j++;
			}else {
				j = next[j-1];      //�����׳���ע����Ϊû��next������λ������ʹ�õ���next  j��ǰһ��λ��
			}
		}
		
		if(j == pat.length()) return i-j;
		else                  return i;
	}
	
	
	public static void main(String[] args) {
		String pat = "ACACA";
		String txt = "ACAACAACAACACAAA";
		KMPbyNext kmp =  new KMPbyNext(pat);
		System.out.println(txt);
		int offest = kmp.search(txt);
		for(int i=0;i<offest;i++) {
			System.out.print(" ");
		}
		System.out.println(pat);
	}
}
