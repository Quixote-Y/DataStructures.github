package sort;

import java.util.Arrays;

/*����������õ�����ԭ������ʱ�临�Ӷ� Nlg(N)
 *  ���������㷨���з֣�
 *  ������������ֳ��������֣������������鶼�����ˣ�����������Ҳ��������
 * 
 */
public class QuickSort {

	public static void main(String[] args) {
		// ����������ٶ�
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
//		int[] arr = {1,5,3,7,-1};
		long start = System.currentTimeMillis();
		
//		quickSort(arr); // 80000�� 0.01      1���� 9.023

//		//�����з�
//		quick3WaySort(arr);      //80000�� 0.026 800000�� 0.099   1��������ʱ ����ʱҲ�� 12��  ���Ƶ������ظ����ݵ�ʱ��

//		// �ڳ���һ�� �Կ���������ж�С�����л���������
		quickSort(arr);   // 80000  0.01     1���� 7.53 �Ż����Ǻܴ��
//		//System.out.println(Arrays.toString(arr));

		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.println(time);
		
		//�ҵ�10000С��Ԫ��
		int a = kFind(arr,3);
		System.out.println(a);
		//���ǻ��ж�ջ���
//		//��ô�ҵ�k�����
//		int b = arr.length-a;
//		int c =kFind(arr,b);
//		System.out.println(c);
	}

	// ����
	public static void quickSort(int[] a) {
		// ��Ҫ���Ҵ��������˳�����������������
		// shuffle
		quickSort(a, 0, a.length - 1);
	}

	public static void quick3WaySort(int[] a) {
		quick3WaySort(a, 0, a.length - 1);
	}

	//ͨ���з� �ҵ�kС��Ԫ��
	public static int kFind(int[] a,int k) {
		return kFind(a,0,a.length-1,k);
		
	}
	private static int kFind(int[] a, int lo, int hi, int k) {
		int res = 0;
		int j =partition(a,lo,hi);
		if(j==k) {//�з�Ԫ�ط��õ�λ�Ӿ������Ԫ������Ҫ�ŵ���λ�ã���߶�С��������������������������еĴ�Сλ��
			return a[lo];
		}else if(j<k) {
			//λ���зֵ��ұ�
			res = kFind(a,j+1,hi,k);
		}else {//j>k��
			res = kFind(a,lo,j-1,k);
		}
		return res;
	}

	// ������������Ҫ�� �з�
	/**
	 * 
	 * @param a  �з�����
	 * @param lo ���鱻�з����λ�����Ҳ�����з�Ԫ��
	 * @param hi �������λ
	 * @return �����з���ɺ��з�Ԫ�ص�λ��
	 */
	private static int partition(int[] a, int lo, int hi) {
		int partitionElement = a[lo];
		int tmp = 0;
		int i = lo, j = hi + 1; // �����з�ʱ��ָ�룬����j����hi+1����Ϊ����a[--j]����ʵ�����һ���жϲ���֮��jָ��ľ����ж���Ч��λ�ã�j--�Ļ���ָ����һ��λ����
		while (true) {
			// ɨ����� , ������һ��ֵ���з�Ԫ�ش�ʱֹͣ �� ɨ����βʱֹͣ
			while (a[++i] < partitionElement) {
				if (i == hi)
					break;
			}
			// ɨ���ұߣ�������һ��ֵ���з�Ԫ��Сʱֹͣ��ɨ����ͷʱֹͣ
			while (a[--j] > partitionElement) {
				if (j == lo)
					break;
			}
			// ���ж�i �� j �����λ�� ��������ָ�뽻���ʱ��ɨ�����
			if (i >= j)
				break;
			// û�������Ǿͻ���ɨ���У���������λ��
			tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
		}
		// ɨ����������ʱ�� j ��λ�þ��� �з�Ԫ�ظ÷ŵ�λ�� �� ��Ϊ���һ��j�߹���i����ʱ��jָ��������һ��С���з�Ԫ�ص�λ��
		tmp = a[lo];
		a[lo] = a[j];
		a[j] = tmp;
		return j;// �����з�Ԫ��λ��
	}

	// ����
	private static void quickSort(int[] a, int lo, int hi) {
		if (hi - lo <= 15) {
			// �����鳤��С��15ʱ������������
			insertSort(a, lo, hi);
			return;
		}
//		//�޲����Ż�
//		if (hi <= lo) {
//			return;
//		}
		int j = partition(a, lo, hi);
		// �зֺ���໹��û�з�Ԫ��
		quickSort(a, lo, j - 1); // �ǵð�λ��j����һ���зַ��úõ�Ԫ��ȥ���з�����
		// �Ҳ໹û����,
		quickSort(a, j + 1, hi); // �ǵð�λ��j����һ���зַ��úõ�Ԫ��ȥ���з�����
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

	// ����������һ�������������׳��ֵĴ��� ��ջ���--------��emmmmm,����������Ϊ������̫���ˣ�������Ϊ�����з�ʱ�����ı��з�Ԫ��λ�õ�ʱ�������βΣ�û�������飬����û�иı䣬�����쳣���ֵ�
	// ��Ϊ����Ĵ��벢δ���κε��Ż��������Ǵ���80000�����ֺ���������ĵݹ���úܿ�Ͱ�ջ�����ˣ�������ջ�������
	// ������и���
	// ���������ջ�����һ�����������⣬����ͨ���Ż����������㷨���ֶ�ģ��ݹ���̻�������ջ�ռ�ȷ������������Ҫ���ݾ������ѡ����ʵķ������Դﵽ���ŵ�����Ч����

	// ���������㷨������
	// 1.����Ϊ����С���飬���������㷨��Ч�ʵ��ڲ��������������������Сʱ���ò���������Ϊ��������
	// 2.�������з֣������ŵ����򣩣������ִ������ظ�Ԫ�أ�����ķ������ɻ���ظ�Ԫ�ؽ����з֣�������û�б�Ҫȥ�����ǽ��в����������з�����˺ܺõ�˼·
	// Ҳ����Dijkstra�ɺ����������������Ľⷨ�������з�
	/*
	 * �����зִ������ұ�������һ�Σ�ά��һ��ָ��lt��ʹ��a[lo...lt-1]�е�Ԫ�ض�С���з�Ԫ�أ�ά��һ��gt��ʹa[gt+1.....hi]
	 * ������Ԫ�ض������з�Ԫ�أ� һ��ָ��i ʹa[lt...i-1]�е�Ԫ�ض������з�Ԫ�� a[i.....gt]���ǻ�δ���бȽϵ�Ԫ�� һ��ʼ i
	 * ����lo+1 ��lt ���� lo �� gt���� hi ---�������з����з�Ԫ��ҲҪ������λ �㷨���̣��Ƚ�a[i]���з�Ԫ��v 1).a[i]>v,��
	 * a[gt]��a[i]���� ��gt-- 2).a[i]<v, a[lt]��a[i]������ lt++ 3).a[i]=v, ��i++
	 * ��Щ�������ᱣ֤����Ԫ�ز�������Сgt-i��ֵ���������ܽ���
	 */

	private static void quick3WaySort(int[] a, int lo, int hi) {
		if (hi <= lo) {
			return;
		}
		int lt = lo, i = lo + 1, gt = hi;// �����зֵ�ָ��
		int partition = a[lo]; // �з�Ԫ��
		// ����һ���з�
		while (i <= gt) { // gtָ��������һ��û�б��зֱȽϵ�Ԫ��
			if (a[i] > partition) { // �����з�Ԫ�أ��ŵ�gt����
				exch(a, i, gt);
				gt--;
			} else if (a[i] < partition) {// �ŵ�lt��ǰ��
				exch(a, i, lt);
				i++;
				lt++;
			} else {// ��ȣ��Ǿ�i����
				i++;
			}
		}
		quick3WaySort(a, lo, lt - 1); // ���з�Ԫ����ߵ�Ԫ���ڽ����з�
		quick3WaySort(a, gt + 1, hi); // �ұߵ� lt---gt�������ͬ��

	}

	// дһ�����������Խ�������������ֵ
	private static void exch(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	
	
}
