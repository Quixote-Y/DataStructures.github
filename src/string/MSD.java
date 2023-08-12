package string;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//��λ���ȵ��ַ�������
/*
 * �����Ե��ַ��������㷨�������ڸ������͵��ַ���
 * ��������ɨ��
 * 
 * �ؼ��������� count��r��������
 */
public class MSD {

	private static int R = 256; //������������
	private static int M =0 ;//������л������㷨����ֵ���������Ϊ0�ͺ���,�ò��ϣ�������
	private static String[] aux; //��������
	private static int charAt(String s,int d) {
		//��д�ַ�����charAt�������ܹ�����d�����ַ������ȵ����
		if(d<s.length())return s.charAt(d);
		else return -1;   //��d�����ַ���ʱ����-1
	}
	
	public static void sort(String[] a) {
		int N = a.length;
		aux = new String[N];
		sort(a,0,N-1,0);
	}
	/**
	 *     �ݹ��ַ�������
	 * @param a           Ҫ������ַ�������
	 * @param lo          ��������ʼ��λ��
	 * @param hi                  ����
	 * @param d           ������ڵ��ǵڼ����ַ�
	 * 
	 * 
	 *  �������� ���ǵ�һ�ζ������ַ�������ĸ����� a ��һ�ѣ�b��һ�� c��һ��... ��Ȼ��ݹ��ȥ���ȶ�a��һ�����򣬻��ڵڶ����ַ���
	 *  Ȼ��ڶ����ַ�֮������һ�ѣ����Ž�ȥ��ֱ�����еĽ��������ص���һ�εݹ�ĵط�����ȥ��b��һ��ͬ��������
	 *  һ���ؼ����ǣ� �������ַ���ĩβʱ������Ƶ�ʵĲ���charAt��д�ķ������ص�ֵ��-1��+2���ͻ���count[1]��֮��Ƶ��ת��Ϊ������
	 *   count[1]+=count[0];��ʱcount[0]���ǳ���Ϊd���ַ������������ʼλ�ã�count[1]�����ַ�������Ϊd�� ��ֹλ��
	 */
	private static void sort(String[] a,int lo,int hi,int d) { 
		//�Ե�d���ַ�Ϊ����a[lo]��a[hi]����ǰ���Ѿ�������ˣ��������
		if(hi<=lo+M) {
			//���ñ�����򷽷������Ż��㷨
			return;
		}
		int[] count = new int[R+2];   //+2������+1����Ϊ����һ��״̬0�� count[0]��ʾ�ַ�������Ϊd���ַ���
		//����Ƶ��
		for(int i =lo;i<=hi;i++) {
			count[charAt(a[i], d)+2]++; //+2 
		}
		//ת��Ϊ����
		for(int i= 0;i<R+1;i++) {
			count[i+1]+=count[i];
		}
		//��������
		for(int i=lo;i<=hi;i++) {
			//count[charAt(a[i], d)+1] :��������dʱ���浽count[0]��Ӧ��λ��
			aux[count[charAt(a[i], d)+1]++] = a[i];
			//aux[]�м��count����ֵ���±�
		}
		
		//��д
		for(int i=lo;i<=hi;i++) {
			a[i] = aux[i-lo]; //aux[i-lo]��Ҫ���������aux��a��[r] rֵ������������count���ַ��������ַ���ͳ�ƣ����߲�һ��
		}
		//�ݹ� ��ÿ���ַ�Ϊ����������
		//    a2131 a413221 a313232 b23123 b321321 b3213   a����һ������ b����һ������
		for(int i =0;i<R;i++) {
			//�����е��ַ�������һ�εݹ�����
			sort(a,lo+count[i],lo+count[i+1]-1,d+1);
			//���lo+count[i] ����������ӣ��������ascal�ַ������е��ַ���ǰ��һֱ����0����Ϊû�е�dλ��ĸ����һλ����Ӧ���ַ���
			//������97ʱ��a���ҵ�����Ӧ��a��3������i=97ʱ��count[97]=0,count[98]=3+1����Ϊcount����ָ����һ���ַ������λ�����Զ�+1
			//��ô ��a ����С������ʱ ��ʼ���� 0 �� count[98]-1 =3;
			// ��ȥ֮�����ǵݹ� ������һ�ε���ĳ���ַ���ĩβʱ�����ͻᱻ��¼��count[1]��count[0]��������ʼ��λ�ã��ڷ�������ʱ�ͻᱻ�ŵ�
			// С�����ǰ��
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream("src\\tinyStringMSD.txt"));
		String[] a; //һ��String���͵�����
		int N = scanner.nextInt();
		scanner.nextLine();
		a= new String[N];
		for(int i=0;i<N;i++) {
			a[i] = scanner.nextLine();
		}
		
		sort(a);
		for(int i=0;i<N;i++) {
			System.out.println(a[i]);
		}
	}
}
