package sparseArray;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//ϡ������ �� ����,����ͻָ�
public class SparseArray {

	public static void main(String[] args) {
		// һ��11*11������ ��0����ʾû���� 1������ 2������
		int[][] chessArr = new int[11][11];
		chessArr[1][2] = 1;
		chessArr[2][3] = 2;
		chessArr[10][10] = 2;
		// ���һ��ԭʼ�Ķ�ά����
		System.out.println("============ԭʼ�Ķ�ά����==============");
		for (int[] row : chessArr) {
			for (int data : row) {
				System.out.printf("%d\t", data);
			}
			System.out.println("\n");
		}

		// תΪϡ������
		// 1.�ȱ�����ά���飬�õ���0���ݵĸ���

		/*
		 * Apple[] apple;//���� apple = new Apple[10];//���� for(int i=0;i<10;i++) {
		 * apple[i]=new Apple(i,10*i); }//��ֵ���������ͣ�ʵ�ʸ����Ƕ������ݵĵ�ַ
		 */
		List<Node> list = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				if (chessArr[i][j] != 0) {
					list.add(new Node(++count, i, j, chessArr[i][j]));
				}
			}
		}
		// ����ϡ�����
		int[][] sparseArr = new int[count + 1][3];
		sparseArr[0][0] = 11;
		sparseArr[0][1] = 11;
		sparseArr[0][2] = count;
		// ���Ƿ�0�ĸ���+1����Ϊ��һ��Ҫ���ά����ĳ��Ϳ���ܷ�0���������Ǻ�Ϊ3�У��ֱ���x,y,val
		// ���ֵ
		for (int i = 0; i < list.size(); i++) {
			Node tmp = list.get(i);
			sparseArr[tmp.count][0] = tmp.x;
			sparseArr[tmp.count][1] = tmp.y;
			sparseArr[tmp.count][2] = tmp.value;
		}
		for (int i = 0; i < sparseArr.length; i++) {
			System.out.printf("%d\t%d\t%d\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
		}
		
		//��ϡ������д���ļ���ȥ
		BufferedOutputStream bufferedOutputStream =null;
		try {
			bufferedOutputStream = new BufferedOutputStream(
					                                        new FileOutputStream("E:\\javaTestFile\\DataStruct\\sparseArr.txt"));
			for(int i=0;i<count+1;i++) {
				for(int j=0;j<3;j++) {
					bufferedOutputStream.write(sparseArr[i][j]);
				}
			}
			System.out.println("д�����");
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			try {
				bufferedOutputStream.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
}

/**
 * Node������Ź���ϡ������ʱ��Ҫ�Ĳ��� 
 * count :ϡ�������м���0��Ԫ�أ�����ϡ�������y���꣬  x����ֱ���0��1��2����Ӧx,y,value; 
 * x�� �����0Ԫ�ص�x����
 *  y�� �����0Ԫ�ص�y���� value ;
 * ֵ
 */
class Node {
	int count;
	int x;
	int y;
	int value;

	public Node(int count, int x, int y, int value) {
		super();
		this.count = count;
		this.x = x;
		this.y = y;
		this.value = value;
	}

}