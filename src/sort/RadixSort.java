package sort;

import java.util.Arrays;

//��������  ����Ͱ����
//ʱ�临�Ӷ� nxk k:Ͱ������
public class RadixSort {

	public static void main(String[] args) {

		// ����������ٶ�
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
		long start = System.currentTimeMillis();
		radixSort(arr); // 0.051��
		//�����������ڴ����˶����10��Ͱ��ÿ��Ͱ�Ĵ�С����������ĳ��ȣ���ʹ��int����
		// 1000000000 1�ڸ�����
		// 1000000000*4*8 /1024/1024/1024 =29G ���ڴ滨�Ѻܴ� 
		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.println(time);
	}

	public static void radixSort(int[] arr) {

		// 10��Ͱ�����ڴ��ÿ�����ֶ�Ӧ�Ľ��
		int length = arr.length;
		int[][] bucket = new int[10][length];
		// һ�����飬���ڴ�ÿ��Ͱ���Ӧ��������
		int[] bucketElementCount = new int[10];
		int max = arr[0];
		for (int i = 0; i < length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
		}
		// ���ת��Ϊ�ַ�����������ĳ���
		int maxLength = (max + "").length();

		int rand = 0;// �ڼ���ȡ��
		int tmp = 0;
		while (rand < maxLength) {
			rand++;
			// ������ÿһ�ֵĴ������
			for (int i = 0; i < length; i++) {
				tmp = (int) (arr[i] / Math.pow(10, rand - 1) % 10);
				bucket[tmp][bucketElementCount[tmp]++] = arr[i];
			}
			// ���ȥ֮��˳��ȡ����
			tmp = 0;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < bucketElementCount[i]; j++) {
					arr[tmp++] = bucket[i][j];
				}
				bucketElementCount[i] = 0;// ����
			}

		}
	}
}
/*
            �ڲ����ȵ����ݷֲ�����£��Ƚ������㷨����������򡢹鲢���򡢶�����ȣ���ʱ�临�Ӷȿ��ܻ��˻��� O(n^2)��������Ϊ�ڲ����ȵ����ݷֲ�����£�
            ѡȡ�Ļ�׼Ԫ�ؿ��ܻᵼ�������еĳ��Ȳ���ܴ󣬴Ӷ����µݹ�������ӣ�ʱ�临�Ӷȱ�ߡ�

              ���֮�£��ǱȽ������㷨�����������Ͱ���򡢼�������ȣ���ʱ�临�ӶȲ������ݷֲ���Ӱ�졣
           �Ի�������Ϊ�������Ǹ���Ԫ�ص�λ�������������Ԫ�ط��䵽Ͱ�У�Ȼ����Ͱ��˳���������Ԫ�أ�ֱ������Ԫ�ض������ϡ�
           �������ݷֲ��Ƿ���ȣ����������ʱ�临�Ӷȶ��� O(d(n+k))������ d ��Ԫ�ص����λ����k ��Ͱ��������

           ��ˣ�������ݷֲ��Ƚϲ����ȣ�ʹ�û�������ȷǱȽ������㷨���ܻ��ʹ�ñȽ������㷨���Ӹ�Ч��������Ҫע����ǣ�
           ��������ȷǱȽ������㷨��Ҫ����Ŀռ����洢Ͱ������ڿռ����ƱȽ��ϸ������£���Ҫ���������������㷨��
*/
