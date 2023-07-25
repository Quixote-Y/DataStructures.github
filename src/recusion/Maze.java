package recusion;

//�ݹ��Թ�
public class Maze {

	public static void main(String[] args) {

		int[][] maze = new int[10][10];// һ��10X10���Թ�
		for (int i = 0; i < 10; i++) {
			maze[0][i] = 1;
			maze[9][i] = 1;
			maze[i][0] = 1;
			maze[i][9] = 1;// ��ǽ
		}
		// ����һ���Թ�
		// ���Թ�������20���������
		for (int i = 0; i < 20; i++) {
			maze[(int) (9 * Math.random() + 1)][(int) (9 * Math.random() + 1)] = 1;
		}
		maze[1][1] = 0;
		maze[8][8] = 0;

		for (int[] i : maze) {
			for (int j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}

		getMazeBetter(maze, 1, 1);
		System.out.println("=======================================");
		for (int[] i : maze) {
			for (int j : i) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}

	// ���Թ�
	/**
	 * 
	 * @param maze �Թ�
	 * @param i    С��λ�ڵ�x����
	 * @param j    С��λ�ڵ�y����
	 * 
	 *             ������ʱ�Թ����޸ģ�1 ǽ 2 ��ȷ·�� 3 ���߹������һ��·�� �յ�9 9
	 */
	public static boolean getMaze(int[][] maze, int i, int j) {
		if (maze[8][8] == 2) {
			return true;// ���յ�
		}
		if (maze[i][j] == 1 || maze[i][j] == 2 || maze[i][j] == 3) {
			// �߲�ͨ
			return false;
		}
		maze[i][j] = 2;// ��ǽڵ㱻�߹�
		//��������ˣ���Ϊ��һ�ε���2֮�󣬽��뵽�ж��ܷ�������ʱ���ͻ�ֱ�ӽ��е�һ���жϣ�����true��Ȼ��ݹ�һ·���أ�����
//		if (i == 8 && j == 8) {  //����һ�ε� 88�յ�ʱ������������ıȽϣ�ֱ������
//			return true;
//		}
		// ��������
		if (getMaze(maze, i, j + 1)) {
			return true;
		} else if (getMaze(maze, i + 1, j)) {// ��������߲�ͨ���Ǿͻ᷵��false��Ȼ���������ж�
			// ������
			return true;
		} else if (getMaze(maze, i, j - 1)) {
			// ������
			return true;
		} else if (getMaze(maze, i - 1, j)) {
			// ������
			return true;
		} else {
			// �������Ҷ��߲���
			maze[i][j] = 3;
			return false;
		}
	}
	
	
	//���õĴ����ʽ--->�߼�������
	public static boolean getMazeBetter (int[][] maze, int i, int j) {
		if(maze[8][8]==2) {
			return true;
		}else {
			if(maze[i][j]!=0) {
				return false;
				//���λ�ò�Ϊ0���Ǿ��ǲ������ߵ�λ�ã�ֱ�ӷ��ؼ�
			}else{
				//���λ�ÿ�����
				maze[i][j]=2;
				// ��������
				if (getMaze(maze, i, j + 1)) {
					return true;
				} else if (getMaze(maze, i + 1, j)) {// ��������߲�ͨ���Ǿͻ᷵��false��Ȼ���������ж�
					// ������
					return true;
				} else if (getMaze(maze, i, j - 1)) {
					// ������
					return true;
				} else if (getMaze(maze, i - 1, j)) {
					// ������
					return true;
				} else {
					// �������Ҷ��߲���
					maze[i][j] = 3;
					return false;
				}
			}
		}
	}
}
