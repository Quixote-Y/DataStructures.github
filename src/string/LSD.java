package string;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//��λ���ȵ��ַ�������
/**
 * 
 *   ���ڼ���������������Ҫ�����ַ����ǵȳ��ַ�����ʱ�򣬿���ʹ��LSD������
 *   ���ַ������ұ����������ÿ��λ�õ��ַ���Ϊ�������м����������ý��������������ַ�������w��
 *   
 *   ��ʵ������� ��ײ��� ��Ϊ ���������������ȶ� �������㷨
 *
 */
public class LSD {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream("src\\tinyString.txt"));
		String[] a; //һ��String���͵�����
		int N = scanner.nextInt();
		scanner.nextLine();
		a= new String[N];
		for(int i=0;i<N;i++) {
			a[i] = scanner.nextLine();
		}
		
		//ͨ��ǰN���ַ���a����
		int R = 256;//ascal�뷶Χ��256
		String[] aux = new String[N];
		//LSD���ʵ��ֻ�����ڳ�����ͬ���ַ�����w���ַ�������
		int W = a[0].length();
		
		//���ݵ�d���ַ��ý���������ʽ��a����
		for(int d = W-1;d>=0;d--) {
			int[] count = new int[R+1];    //�������ֻ�����⣬֮ǰ��������ִ�����Ϊÿһ�ֶ�Ҫ����дcount��������Ļ�û���������ݣ��ͻ����
			//��¼Ƶ��
			for(int i=0;i<N;i++) {
				count[a[i].charAt(d)+1]++;     //����+1��������0��λ�����ں���ת��Ϊ������ʱ����� ʵ�� count[i]����i����ʼλ�ã�������������
			}
			//Ƶ��ת��Ϊ����
			for(int i=0;i<R;i++) {
				count[i+1]+=count[i];
			}
			//�������
			for(int i=0;i<N;i++) {
				aux[count[a[i].charAt(d)]++] = a[i];//��a��i��λ�õ��ַ����ŵ�aux �İ�countͳ�Ƶ�λ����
			}
			//���ƻ���
			for(int i=0;i<N;i++) {
				a[i] = aux[i];
			}
		}
		for(int i=0;i<N;i++) {
			System.out.println(a[i]);          //ok
		}
	}

}
