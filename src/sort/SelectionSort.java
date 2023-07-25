package sort;

import java.util.Arrays;

public class SelectionSort {
	// ѡ������ÿһ��ѡ����������������С��Ԫ�غ�û�����Ԫ�صĵ�һ��Ԫ�ؽ���
    // ���ð�����򣬸��ٵıȽϴ�������Ч�������٣��ٶȿ�Ķ�
	public static void main(String[] args) {
		// ����ѡ��������ٶ� ��ʱ�临�Ӷ���O(2*n)
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
		long start = System.currentTimeMillis();
		selectionSort(arr);           //0.835 �� ���ð�������ٶ�����˺ܶ�
		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.println(time);
	}

	// ѡ������
	public static void selectionSort(int[] arr1) {
		int[] arr = arr1;
		int minIndex = 0;
		int min = arr[0];

		for (int i = 0; i < arr.length; i++) {
			min = arr[i];
			for (int j = i + 1; j < arr.length; j++) {
				if (min > arr[j]) {
					min = arr[j];
					minIndex = j;
				}
			}
			if (minIndex != i) {
				arr[minIndex] = arr[i];
				arr[i] = min;
			}
		}
	}
}
