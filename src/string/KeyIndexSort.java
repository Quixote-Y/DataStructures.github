package string;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

//����������
/**
 *    �����������㷨�Ƕ��� С����������ǳ���Ч���㷨������ʱ�� �����Լ���ģ���Ϊ����������������Ҫ�Ƚϣ�ֻͨ��key���������ַ�����ʱ��ͻ����
 *    �����㷨 NlgN������
 *    
 *    �����ǣ� count���� int�� ��0��λ����
 *           1��������ĳ���Ƶ�ʣ�count[a[i].key()+1]++; �����ֵΪ1�ַ��� �ĳ��ִ������洢�� count[2]�У�����Դ�����
 *           2����Ƶ�ʻ�Ϊ������count[i+1]+=count[i] ;   ��ֵΪ2���ַ����ĵ�һ��λ�þ���ǰһ����ֵΪ1�ַ�����������
 *              ע�⣬��ʱcount[1]�Ǽ�ֵ1���ַ����Ŀ�ʼλ�ã�count[2]�Ǽ�ֵ2�Ŀ�ʼλ��
 *           3��Ԫ�ط���д�븨�����飬���ռ�ֵ�Ͷ�Ӧ�� countλ��д�븨������ aux �У�Ҳ���ǽ��ַ�������Ĺ���
 *              aux[count[i].key()]++] =a[i];    
 *              //����auxÿ���һ��Ԫ�أ�����Ӧ��count��Ҫ++һ�Σ� ����ȷ��count��Ӧ������ֵ��λ��һ�������µģ�Ҳ������һ������Ҫ�����λ��
 * 
 *
 */
public class KeyIndexSort {
	//��дһ�£���Ƶ�һЩapi�Ͳ�ȥʵ����
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileInputStream("src\\tinyString.txt"));
		String[] a; //һ��String���͵�����
		int N = scanner.nextInt();
		scanner.nextLine();
		a= new String[N];
		for(int i=0;i<N;i++) {
			a[i] = scanner.nextLine();
		}
		
		String[] aux = new String[N];
		//R�Ǽ�ֵ������
		int R = 5;
		int[] count = new int[R+1];
		
		//����Ƶ��
		for(int i =0;i<N;i++) {
			count[a[i].charAt(0)-'0'+1]++;//�����ַ���һ���Ǽ�ֵ
		}
		//Ƶ��ת��Ϊ����
		for(int i=0;i<R;i++) {
			count[i+1]+=count[i];
		}
		//��������
		for(int i=0;i<N;i++) {
			aux[count[a[i].charAt(0)-'0']++] = a[i];
		}
		//д��
		for(int i=0;i<N;i++) {
			a[i] = aux[i];
		}
		
		for(int i=0;i<N;i++) {
			System.out.println(a[i]);          //ok������ɹ�
		}
	}
	

}
