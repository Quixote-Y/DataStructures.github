package sort;

/*ϣ������ ʱ�临�Ӷ������Զ�������
 * 
 * ϣ��������һ�ֻ��ڲ�������������㷨�������˼���ǽ����������зֳ����ɸ������У�
 * ��ÿ�������н��в�����������ʹ�������дﵽ�������ڲ������������л�������ʱ
 * Ч�ʽϸߣ�ϣ������ͨ����С�����еļ�����������еľֲ������ԣ��Ӷ��ӿ�������ٶȡ�
 */
public class ShellSort {

	public static void main(String[] args) {
		// ����������ٶ�
		int[] arr = new int[80000];
		for (int i = 0; i < 80000; i++) {
			arr[i] = (int) (Math.random() * 80000000);
		}
		long start = System.currentTimeMillis();
		//���Կ���shell�����Ч�����е����ݼ�����ʮ�������
		shellSort(arr);         // 0.014��
		long end = System.currentTimeMillis();
		double time = (end - start) / 1000.0;
		System.out.println(time);
	}

	/*
	 * Ҳ��������λ��������Խ����¼Ҫ�������ݣ�û���ҵ�ȷ��λ��֮ǰ��ֻ�ǽ����ݺ���һλ���ҵ���Ų��� �����ð�ݵ�ÿ�ζ�������һ�αȽϾ�Ҫ����3�ν���
	 */
	// ������λ���� ��ϣ������
	public static void shellSort(int[] arr) {
		int tmp = 0;
		int index = 0;
		for (int gap = arr.length / 2; gap >= 1; gap /= 2) {
			// gap��ÿһ������ ��ͬһ��Ԫ�ؼ����Ĳ�������10��Ԫ�أ���һ�β���Ϊ5����Ϊ5�飬ÿ������
			// Ԫ�أ�����Ԫ�ؽ��в������򣬾ֲ��������򻯣�Ȼ����һ���ֱ�Ϊ����Ϊ2��2�飬ÿ��5������������
			// ���õľ��ǵ����������Ѿ��󲿷�����ʱ������������бȽϴ����ٵ��ŵ����Ż��㷨--������Ļ�ÿ��ֻҪ�͵�һ��Ԫ�رȽϾ�
			// ��ȷ��λ�ã����������ʱ�临�Ӷ�nֻ��Ҫn-1�αȽϣ����Ҳ���Ҫ�κ��ƶ�������������������ʱ�临�Ӷ�n*2
			for (int i = 0; i < gap;i++) {
				// ÿһ�飬ÿ�����gapλ
				for (int j = i; j < arr.length; j += gap) {
					tmp = arr[j];
					index = j - gap;
					while (index >= 0 && tmp < arr[index]) {
						//������ò����������λ���������ŵķ�������λ���������Ľ������٣����ÿ�ζ���ð��һ���Ľ����������ʾͻ����Ϻܶ�
						// û�б�����ͷ������tmpС��indexָ���Ԫ�أ�����λ
						arr[index + gap] = arr[index];
						// ���򲿷�Ҳ��gapλ���λһ��
						index -= gap;
					}
					// �ҵ�λ��
					if (index + gap != j) {
						arr[index + gap] = tmp;
					}
				}
			}

		}
	}
}