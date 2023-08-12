package priorityQueue;

//���ȶ���    --

/*
  ���ȶ�����һ������Ķ��У����ܹ�����Ԫ�ص����ȼ�ȷ��Ԫ���ڶ����е�˳��ÿ��Ԫ�ض���һ����֮��ص����ȼ���
  ���ȼ��ߵ�Ԫ���ȱ��������ȼ�����ͨ��ʹ�ö��������ݽṹ��ʵ�֣���Ϊ���ܹ���O(log n)��ʱ�临�Ӷ��ڶ�Ԫ�ؽ��в����ɾ��������
  ���ȶ��п���֧�����²�����
  ����Ԫ�أ���Ԫ�ذ������ȼ����뵽�����С�
  ɾ��Ԫ�أ�ɾ�����������ȼ���ߵ�Ԫ�ء�
  ����Ԫ�أ����Ҷ��������ȼ���ߵ�Ԫ�أ�����ɾ������
  �޸�Ԫ�أ��޸Ķ����е�ĳ��Ԫ�ص����ȼ���
  ���ȶ��г�����������ȡ��¼�����ϵͳ�����·���㷨�ȳ�����
 */
//���ȶ���ʵ�ֵ�ʱ�����飨���򣩣����飨���򣩣�������ʵ��Ч�����ŵĻ��Ƕ���ѵķ�ʽ
/*
 * ������ÿ���ڵ㶼�������������ӽڵ�
 *       ��һ���������ƣ����������洢��ȫ���������������а��ղ㼶���洢�������ڵ㣬��1��λ�ÿ�ʼ��0�Ų��ã�
 *         λ��k�Ľڵ㣬���ĸ��ڵ�λ��k/2�����������ӽڵ�λ��2k��2k+1
 */

//���������ʼ���ķ�ʽ��Stack�ļ������ƣ�ֻ����ʱ��Object���滻Ϊ�˸������ʵ����Comparable��
/* �����ҵ�һ�����Գ�ʼ����������ķ�ʽ�� 
* (T[])Array.newInstance(Object.class, <size int>); 
* ��ʵ���� һ�� Object[] 
* ��Ϊ Java�ķ���ʵ�ֻ����� type erasure������ʱ���Ͳ��������ͱ���֮���� Object �� ǿ������ת����
* ��Ҫע����ǣ���ķ������Է��� T, List<T> �ⶼ�У���Ҫ���� T[]�� �������ģ���Ϊ�ⲿʹ�õ�ʱ�� T ������ʵ�ʵ����ͣ�
* ��ʱ���������ͼ����ȷ�� ���ڲ���һ�� Object[]����������Ԫ�ض��� T ���ͣ����Ե����ó��� cast ��û���⣬���� Object[]
* �������Ҫ cast ��ĳ�־������͵����飬���׳�����ʱ�쳣��
*/
public class PriorityQueue<T extends Comparable<T>> {// ��һ�����ͽ��������洴�����Ķ���

	// ��������
	private T[] pq;
	private int length = 0;

	// ���ȶ��г�ʼ��
	public PriorityQueue(int maxLength) {
		// ���������޷�����ʼ���������Դ���Comparable��Ȼ��ǿ������ת��ΪT������
		pq = (T[]) new Comparable[maxLength + 1];// 0��λ����
	}

	// null�ж�
	public boolean isEmpty() {
		return length == 0;
	}

	// ��Ч����
	public int size() {
		return length;
	}

	// ����
	public void insert(T t) {
		pq[++length] = t;
		swim(length); // �¼�������λ�õ�Ԫ���ϸ�
	}

	// ɾ��--->���ȶ��У�ɾ����Ӧ����˼�� �ó�����Ԫ��
	public T delete() {
		T max = pq[1];
		pq[1] = pq[length];// �����һ��λ�õ�Ԫ���õ�ͷ������Ȼ���³����¶�����
		pq[length--] = null; // Ȼ�����һ��λ�õ�Ԫ���ÿ�
		sink(1);
		return max;
	}

	// ��������̻��õ��ķ���
	// �ϸ�
	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {// �����ڵ�k/2С���ӽڵ�kʱ�滻
			exch(k / 2, k);
			k = k / 2;
		}
	}

	// �³�
	private void sink(int k) {
		// �ж���û�е���Ҷ�ӽڵ�
		while (2 * k <= length) {
			// ���j�õģ�������
			int j = 2 * k;
			// ��¼�ӽڵ��нϴ��һ��
			if (j < length && less(j, j + 1))//�����ж�����j < headLenth����Ȼ��ֻ����������ʱÿ���³�����©һ���ڵ�
				j++;
			if (!less(k, j))
				break; // ������ڵ㲻С�������ӽڵ㣬�Ǿ�ֱ�ӽ��������ˣ��Ѿ�������
			exch(k, j);
			k = j;
		}
	}

	// �ȽϽڵ��С
	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0; // �����ڵ�ֵС���Ҳ�ڵ�ֵʱ����true
	}

	// �滻λ��
	private void exch(int i, int j) {
		T t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

	// ����һ��
	public static void main(String[] args) {
		PriorityQueue<Integer> pQ = new PriorityQueue<>(800000);
		for (int i = 0; i < 800000; i++) {
			pQ.insert((int) (Math.random() * 80000));
		}
		// ����ȡ��Ԫ�أ����Ƿ�����
		for (int i = 0; i < 10; i++) {
			System.out.println(pQ.delete());
		}
		// successfully
	}
}
