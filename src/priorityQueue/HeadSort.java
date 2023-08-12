package priorityQueue;

import java.util.Arrays;

@SuppressWarnings("all")
public class HeadSort<T extends Comparable<T>> {

	// �������ȶ��ж���� �� �����㷨
	// ������

	/*
	 * ʱ�临�Ӷ��ȶ����������ʱ�临�Ӷ�ΪO(nlogn)�����������ݵĳ�ʼ״̬�޹أ�����������ȶ���ʱ�临�Ӷȡ�
	 * 
	 * �ռ临�Ӷȵͣ�������ֻ��Ҫһ��������Ϊ�����ռ䣬����Ҫ������ڴ�ռ䣬������Ŀռ临�Ӷ���O(1)����ġ�
	 * 
	 * �������ݷֲ�Ӱ�죺�����κ��������ݣ��������ʱ�临�Ӷȶ����ȶ��ģ��������ݷֲ���Ӱ�졣
	 * 
	 * ��ռ�ö����ڴ�: ��������һ��ԭ�������㷨������Ҫռ�ö�����ڴ�ռ䣬������������ڴ����޵������
	 * 
	 * ���Է����ʵ�����ȼ����У������������ȼ����еĻ��������Է����ʵ�����ȼ����е���ع��ܡ�
	 */
	// ����Ҫ��дһ�¶���ѵ��³��㷨��ʹ������ָ������
	/**
	 * ָ������a��������k��ʼ�³�
	 * 
	 * @param a         ���³�������
	 * @param k         �³�Ԫ��
	 * @param headLenth ����ѳ��ȣ���Ϊ�����³�����Ҫ������Ԫ�طŵ����Ȼ������Ӷ������ȥ��
	 */

	// ��д�������һЩ����
	public void sink(Comparable[] a, int k, int headLenth) {
		while (2 * k <= headLenth) {
			int j = 2 * k;
			if (j < headLenth && less(a, j, j + 1))//�����ж�����j < headLenth����Ȼ��ֻ����������ʱÿ���³�����©һ���ڵ�
				j++;
			if (!(less(a, k, j)))
				break;// ���k��С���ӽڵ�
			exch(a, k, j);
			k = j;
		}
	}

	private boolean less(Comparable[] a, int i, int j) {
		return a[i].compareTo(a[j]) < 0;
	}

	private void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public void headSort(Comparable[] a) {
		// �Ȱ����������
		// �����³�֦�ڵ�ķ�ʽ�����ٶԽڵ�ķ��ʱȽϴ��������Ա������Ҷ�ڵ�
		int length = a.length - 1;
		for (int i = length / 2; i > 0; i--) {
			// length/2 Ϊ���һ��֦�ڵ��λ��
			sink(a, i, length);
		} // ������
			// �³�����
		int headLength = length;
		while (headLength > 1) { // һֱ���н������³��������������û�нڵ�

			exch(a, 1, headLength--);// ��������ѵ�1��Ԫ�غ����һ��Ҷ�ӽڵ�Ԫ�أ��������
			sink(a, 1, headLength);
		}
	}

	// ����һ��
	public static void main(String[] args) {
		HeadSort<Integer> hS = new HeadSort<>();
		//
		// ���ɲ���0��
		Integer[] arr = new Integer[10+1];
		for (int i = 1; i < 10+1; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
		long start = System.currentTimeMillis();
		hS.headSort(arr);    //80000 �� 0.004  1���� 210.981 �о�ʱ�仨����װ��ͺͲ�������
		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.println(time);
		System.out.println(Arrays.toString(arr));
	}
}
