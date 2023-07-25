package sort;

import java.util.Arrays;

//�鲢����
public class MergeSort {

	/*
	 * �鲢���� ԭ�ع鲢�Ļ� �ռ临�Ӷ� n ��ʱ�临�Ӷ�nlgn ��Ϊ�Զ����� �Ե����� ���ֹ鲢��ʽ ·�߲�ͬ
	 * ���ģ�����ܽ����������������Ǿ���ͨ���鲢�������������������������򣬵�������ֻ��һ��Ԫ��ʱ�����϶��������
	 */

	private static int[] aux;// ��������ֱ����Ϊȫ�ֱ���������ݹ����

	public static void main(String[] args) {
		// ����������ٶ�
		int[] arr = new int[100000000];
		for (int i = 0; i < 100000000; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
		long start = System.currentTimeMillis();
		// ���Ż���ʩ���Զ�����i
		mergeSort(arr); // 80000�� 0.012 1���� 12.856
		// �Ե����� 1���� 12.091 ����޼�

		//ʹ���޶�������Ĺ鲢 80000�� 0.45  80���� 47.501 �Ѿ��Ǻ������� ������1�����ܾö�û��������û�в��ԣ���Ч�ʿ��ǣ��Ͼ�����ʱ�任�ռ�
		
		//��С����ʱʹ�� �������� 8���� 0.009 1���� 6.027 ���Կ�����Ч�ʷ��� ����ǿ��
		
		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.println(time);

//		int arr[] = { 12, 11,21,-4, 13, 5, 6, 7 };
//
//		mergeSort(arr, 0, arr.length - 1);
//
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i] + " ");
//		}
	}

	public static void mergeSort(int[] a) {
		// ֻ����һ�����������ʼ��aux
		aux = new int[a.length];
		mergeSort(a, 0, a.length - 1);
		// mergeSortBU(a);
	}

	// �����Զ����µĹ鲢
	private static void mergeSort(int[] a, int lo, int hi) {
		if (hi-lo<=15) {
			insertSort(a,lo,hi);
			return;
		}
		int mid = lo + (hi - lo) / 2;
		mergeSort(a, lo, mid); // �������й鲢���� ��һֱ�ݹ鵽ֻ��һ��Ԫ�ص����
		mergeSort(a, mid + 1, hi); // �Ұ�
		merge(a, lo, mid, hi); // ���ݹ鵽ֻ��һ��Ԫ�ص�ʱ��ʼ ԭ�ع鲢 һ��һ���ʹ������
	}

	private static void insertSort(int[] a, int lo, int hi) {// ��������,��lo��ʼ��hi
		int tmp = 0; // �������Ԫ��
		int index = 0; // ɨ���������飬�ҵ�����λ��
		for (int i = lo + 1; i < hi - lo; i++) {
			tmp = a[i];
			index = i - 1;
			while (index >= i - 1 && tmp < a[index]) {
				// �ƶ�ֵ
				a[index + 1] = a[index];
				index--;
			}
			// ���ҵ�λ�ú�
			if (index != i - 1) {
				a[index + 1] = tmp;
			}
		}

	}
	// �Ե����Ϲ鲢
	private static void mergeSortBU(int[] a) {
		// ����lgN�������鲢
		int length = a.length;
		for (int sz = 1; sz < length; sz += sz) { // sz��������Ĵ�С ���ȴ�1��ʼ��Ȼ��2��4��8����
			for (int lo = 0; lo < length - sz; lo += sz + sz) { // lo�ǹ鲢ʱ�����������ߵ�����
				// ��ʼ�鲢
				merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, length - 1));
				// ���һ��������Ĵ�Сֻ���� ���鳤����sz��ż����ʱ �Ż����sz�����������szС������Ϊʲô-1�ܼ򵥣���Ϊ�Ǵ�lo��ʼ����
			}

		}
	}

	// ���ģ�ԭ�ع鲢�㷨 ��������Ԫ�ظ�ֵ��һ�����������У��ڰѹ鲢����Ż�ԭ����

	/**
	 * ������a�� [lo,mid]��[mid+1,hi]���й鲢
	 * 
	 * @param a   Ŀ������
	 * @param lo  �鲢���ݶε����������
	 * @param mid �м䣬������һ�ι鲢������
	 * @param hi  ���Ҷ�
	 */
	private static void merge(int[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;// �ϲ���ʱ��ֱ�ָ������������

		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];// ���Ƶ�aux��
		}

		// ������
		for (int k = lo; k <= hi; k++) {// ��aux���ݹ鲢�ص�a�У���aux��࿪ʼɨ�裬һ��ɨһ����
			if (i > mid)
				a[k] = aux[j++];// �������ɨ��
			else if (j > hi)
				a[k] = aux[i++];// �ұ�ɨ��
			else if (aux[i] > aux[j])
				a[k] = aux[j++];// ��ߵ������ұߵ�������
			else
				a[k] = aux[i++];// �ұߵ������ڵ�����ߵ���
		}

	}

	// ���ڿ���һ������Ż�����㷨
	// 1.����ռ� ���ܷ�ֱ����ԭ�����Ϲ鲢 ������Ҫע����ǣ����ַ�����ʱ�临�ӶȲ������ڱ�׼�Ĺ鲢����ʹ�ö���ռ䣩��������Ҫ�ŵ��ǽ�ʡ�˿ռ䡣
	// 2.С�����ٽ� �� С�����л�Ϊ��������
	private static void merge2(int[] a, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;// �ϲ���ʱ��ֱ�ָ������������

		if (a[mid] < a[mid + 1]) {// ���������С����������С����ֱ�Ӿ����������
			return;
		}
		int value = 0;
		int index = 0; //
		while (i <= mid && j <= hi) {//����������ˣ����˺ܾã���ߵ���ͷ�����ұߵ���ͷ�ͽ���
			if (a[i] > a[j]) {
				// ��ߵĵ�i��λ�ô����ұߵĵ�j��λ��
				// �ǾͰ����i��֮�������ֵ����һ��Ȼ���a[j]��������������������һ�� ��ע���ʱ��Ϊ�����������������������midҲҪ+1������
				value = a[j];
				index = j - 1;
//				while (index >= i && value < a[index]) {//��ʵ��û�б�Ҫ�жϣ���Ϊ���϶���Ҫ�ƶ���iλ�õ�
//					a[index+1]=a[index];//����
//					index--;
//				}
				while (index >= i) {
					a[index + 1] = a[index];
					index--;
				}
				a[index + 1] = value;
				mid++;
				i++; // ��Ϊi��ֵ����������һλ
				j++;
			} else {
				// ��ߵ�С���ұߵģ��ǾͲ��䣬i++
				i++;
			} // ��������Ͳ���ͺ���
		}

	}
	//�Ե����ϵĹ鲢���Զ����µĹ鲢������Ƚϴ����ͷ��ʴ�����ͬ�����Ե����ϵĹ鲢����������������
	//ֻ����֯�������Ӿ���ʵ��ԭ�ع鲢���������ռ�
}
