package sort;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.rules.Stopwatch;


//ð�ݣ�ÿ����Ԫ�ض�Ҫ�Ƚϣ�Ȼ������˺ܶ���Ч�Ľ���
public class BubleSort {

	public static void main(String[] args) {
		//����ð��������ٶ�  ��ʱ�临�Ӷ���O(2*n)
		int[] arr = new int[80000];
		for(int i=0;i<80000;i++) {
			arr[i]=(int )(Math.random()*80000000);
		}
		long start = System.currentTimeMillis();
		bubleSort2(arr);        //7.259 ��
		//bubleSort1(arr);          //6.962
		//��ʵ������������޼�������7������
		long end = System.currentTimeMillis();
		double time = (end- start)/1000.0;
	    System.out.println(time);
	}
	
	public static void bubleSort1(int[] arr1) {
		//�����ҳ��õķ�ʽ���ϸ���˵Ӧ�����³�����
		int[] arr = arr1;
		for(int i=0;i<arr.length;i++) {
			for(int j=i+1;j<arr.length;j++) {
				if(arr[i]>arr[j]) {
					int tmp = arr[i];
					arr[i]=arr[j];
					arr[j]=tmp;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
	}
	//ÿ�������������ݱȽϣ��������ϸ���ֱ�������棬���о�ϰ��һ����������
	@Test
	public static void bubleSort2(int[] arr1) {
		//�����ʽ���Ż�
		int[] arr = arr1;
		int tmp =0;
		for(int i=0;i<arr.length;i++) {
			boolean flag = false;
			//������Ż��Ĺؼ�
			for(int j=0;j<arr.length-1-i;j++) {
				//���ǰ������Ⱥ��������ͽ���
				if(arr[j]>arr[j+1]) {
					flag=true;
					tmp=arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=tmp;
				}
			}
			if(!flag) {
				//���һ�ν��涼û�У��Ǿ�˵���������Ѿ���ȫ������ˣ�ֱ���˳�
				break;
			}
		}
		//System.out.println(Arrays.toString(arr));
	}
	
}
