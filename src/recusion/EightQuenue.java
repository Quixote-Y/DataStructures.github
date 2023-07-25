package recusion;

import org.junit.jupiter.api.Test;

//8�ʺ�������ݵݹ�
public class EightQuenue {

	static int max =8;//8���ʺ�
	//һ�����飬���ڴ�Żʺ����λ�õĽ��
	static int[] arr = new int[max];
	static int count=0;
	
	public static void main(String[] args) {
		getEightQuenue(0);
		System.out.println(count);
	}
	
	//��ӡ8���ʺ�λ��
	public static void printQuenue() {
		for(int i =0;i<max;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	//�жϷ��õĵ�n���ʺ��Ƿ���ǰ��n-1���ʺ�����ͻ
	public static boolean judge(int n) {
		for(int i=0;i<n;i++) {
			if(arr[i]==arr[n]||Math.abs(n-i)==Math.abs(arr[n]-arr[i])) {
				//��ǰ��ĵ�i���ʺ��������ӵĻʺ�λ��ͬһֱ�ߣ�������б��ʱ��������ͻ
				return true;
			}
		}
		return false;
	}
	
	//��ʼѰ������8�ʺ�λ�õķ���
	//n �ڼ����ʺ�ʼ
	public static void getEightQuenue(int n) {
		for(int i=0;i<max;i++) {
			arr[n]=i;      //�ѵ�nλ�ʺ���õ�������
			if(judge(n)) {
				//���������ͻ������������һ����
				continue;
			}else {
				//û������ͻ�������λ����ʱ����
				//����ŵ��˵�8λ�ʺ�ͨ����judge
				if(n==7) {
					printQuenue();
					count++;
					continue;
				}
				getEightQuenue(n+1); //��ʼ����һ���ʺ�
			}
		}
		return;
	}
}
