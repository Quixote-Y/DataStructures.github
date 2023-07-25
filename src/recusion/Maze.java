package recusion;

//递归迷宫
public class Maze {

	public static void main(String[] args) {

		int[][] maze = new int[10][10];// 一个10X10的迷宫
		for (int i = 0; i < 10; i++) {
			maze[0][i] = 1;
			maze[9][i] = 1;
			maze[i][0] = 1;
			maze[i][9] = 1;// 画墙
		}
		// 产生一个迷宫
		// 在迷宫里生成20个随机挡板
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

	// 走迷宫
	/**
	 * 
	 * @param maze 迷宫
	 * @param i    小人位于的x坐标
	 * @param j    小人位于的y坐标
	 * 
	 *             当结束时迷宫被修改，1 墙 2 正确路线 3 ，走过的最后一条路径 终点9 9
	 */
	public static boolean getMaze(int[][] maze, int i, int j) {
		if (maze[8][8] == 2) {
			return true;// 到终点
		}
		if (maze[i][j] == 1 || maze[i][j] == 2 || maze[i][j] == 3) {
			// 走不通
			return false;
		}
		maze[i][j] = 2;// 标记节点被走过
		//这里多余了，因为第一次等与2之后，进入到判断能否向右走时，就会直接进行第一个判断，返回true，然后递归一路返回，结束
//		if (i == 8 && j == 8) {  //当第一次到 88终点时，不进行下面的比较，直接跳出
//			return true;
//		}
		// 先向右走
		if (getMaze(maze, i, j + 1)) {
			return true;
		} else if (getMaze(maze, i + 1, j)) {// 如果向右走不通，那就会返回false，然后进行这个判断
			// 向下走
			return true;
		} else if (getMaze(maze, i, j - 1)) {
			// 向左走
			return true;
		} else if (getMaze(maze, i - 1, j)) {
			// 向上走
			return true;
		} else {
			// 上下左右都走不了
			maze[i][j] = 3;
			return false;
		}
	}
	
	
	//更好的代码格式--->逻辑跟清晰
	public static boolean getMazeBetter (int[][] maze, int i, int j) {
		if(maze[8][8]==2) {
			return true;
		}else {
			if(maze[i][j]!=0) {
				return false;
				//这个位置不为0，那就是不可以走的位置，直接返回假
			}else{
				//这个位置可以走
				maze[i][j]=2;
				// 先向右走
				if (getMaze(maze, i, j + 1)) {
					return true;
				} else if (getMaze(maze, i + 1, j)) {// 如果向右走不通，那就会返回false，然后进行这个判断
					// 向下走
					return true;
				} else if (getMaze(maze, i, j - 1)) {
					// 向左走
					return true;
				} else if (getMaze(maze, i - 1, j)) {
					// 向上走
					return true;
				} else {
					// 上下左右都走不了
					maze[i][j] = 3;
					return false;
				}
			}
		}
	}
}
