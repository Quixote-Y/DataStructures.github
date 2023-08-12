package symbolTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/*
 * ���ֲ��ң������Ƕ�������lgN�������������Լ���N����������2N
 */

//������������Ķ��ֲ��ҷ��ű�
public class BinarySearchST<Key extends Comparable<Key>, Value> {

	// �������飺һ���洢����һ���洢ֵ������������ƽ�У�����������������Ч��ʵ�����Ӻ�ɾ��
	// ���������鿪ʼ�ǿյģ���ÿ�������ű���������ݶ��ᾭ��put��������Ҳ�͹�����һ�����������
	// ʵ�ַ��ű�Ĺؼ�����Rank��Key�������������ر���С�ڸ������Ľ���������Ҳ���������Ӧ�ô��ڵ�λ�ã�ʹ�õ��Ƕ��ֲ��ҵķ�ʽ
	/*
	 * ���ֲ��ң� ���������������������ÿ�β�����Ҫ�ıȽϴ����������������뱣֤����
	 * ���ǻ�������м��ֵ���бȽϣ���������С�����ң�ֱ����λ�������ֵӦ���ڵĵط� �ͺͿ�����������ҵ���kС��Ԫ��һ��
	 */
	private Key[] keys;
	private Value[] vals;
	private int length = 0;//�������������Ǵ�0��δ��ʼ�������

	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
	}

	public int size() {
		return length;
	}

	public boolean isEmpty() {
		return length == 0;
	}

	// ��Ϊ����Ҫ�õ�����������������ؼ��ķ��� rank
	// ���ֲ��ң����ؼ�ֵkey������keys��Ӧ�õ�λ��\
	//���ֲ���midԪ�ص�λ��������0.5���ȵ�λ�ã��������ݵķֲ������ͬ�������޸�mid�㷨���Ը���Ч�Ļ�ȡ��Ŀ��λ��
	//��ֵ���� �� mid = lo + (hi - lo)*(value-vals[lo])/(vals[hi]-vals[lo])   ��������value�ֲ�����ʱ����Ч
	public int rank(Key key, int lo, int hi) {
		// �ݹ�ķ�ʽ
		if (hi <= lo) {
			return lo;
		}
		int mid = lo + (hi - lo) / 2;
		int cmp = key.compareTo(keys[mid]); // Keyû�о�����࣬����Comparable��ʵ���࣬���Կ��Ե���comparable������������ֱ��ʹ�������
		if (cmp < 0) {
			return rank(key, lo, mid - 1);
		} else if (cmp > 0) {
			return rank(key, mid + 1, hi);
		} else {
			return mid;
		}
	}

	public int rank2(Key key) {
		// �����ķ�ʽ
		int lo = 0, hi = length-1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0) {
				hi = mid - 1;
				
			} else if (cmp > 0) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return lo;
	}

	public Value get(Key key) {
		if (isEmpty())
			return null;
		int i = rank2(key);
		if (i < length && keys[i].compareTo(key) == 0) {
			return vals[i];
		} else {
			return null;
		}
	}

	public void put(Key key, Value value) {
		int i = rank2(key);
		//�о͸�ֵ
		if (i < length && keys[i].compareTo(key) == 0) {
			vals[i]=value;
			return;
		}
		//û�оͼ��룬���������λ�ú�������ݶ�������
		for(int j =length;j>i;j--) {
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = value;
		length++;
	}
	
	
	public Key[] getKeys() {
		return keys;
	}

	public Value[] getVals() {
		return vals;
	}

	public static void main(String[] args) {
		BinarySearchST<Character, Integer> binarySearchST = new BinarySearchST<>(10);
		binarySearchST.put('A', 1);
		binarySearchST.put('B', 2);
		binarySearchST.put('C', 3);
		binarySearchST.put('D', 4);
		binarySearchST.put('E', 5);
		binarySearchST.put('F', 6);
		
		Comparable[] key = binarySearchST.getKeys();
		System.out.println(Arrays.toString(key));
		
	}
}
