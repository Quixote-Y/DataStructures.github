package stack;
/**
 * 以中缀表达式实现表达式求值，花费了很多时间，依旧存在很多问题，向依旧无法处理(20+10)%2+(10+30)/3这样的问题，因为中缀表达式计算在计算机语言描述时，需要考虑的东西有点太多了
 *   运算符的优先级 括号的范围 ，特别是括号对整个算术的过程影响，依旧还是没理清 
 *   
 */


import java.util.Scanner;

//数据栈  符号栈 实现数据表达式计算
public class Calculator {

	public static void main(String[] args) {
		ArrayStack<Integer> numStack = new ArrayStack<>(10); // 数字站
		ArrayStack<Character> operStack = new ArrayStack<>(10); // 符号栈

		Scanner scanner = new Scanner(System.in);

		System.out.println("请输入你想计算的表达式：");
		String expression = scanner.next();

		// String expression = "(2+2*9)*(2+3)";
		boolean lastKuohao = false; // 判断上一个符号是否是‘（’符号，这个为true，下一个符号直接入栈
		boolean secondeKuohao = false; // 判断上一个符号是否是‘）’符号，这个为true ，辖两个符号直接入栈，因为）括号后面必跟着一个符号，哪后面的第二个符号也应该直接添加
		int count = 0;// 用于记录后括号后面出现的第几个符号

		String keepNum = ""; // 当出现多位数时用于拼接

		for (int i = 0; i < expression.length(); i++) {
			char value = expression.charAt(i);
			switch (value) {
			// 数字
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				// numStack.push(value - '0');
				// 不能直接入栈，直接入栈的话就无法处理多位数的情况
				keepNum += value; // 字符拼接
				// 判断下一个字符是不是数字
				if (i == expression.length() - 1) {
					numStack.push(Integer.parseInt(keepNum));
					break;
				}
				value = expression.charAt(i + 1);
				if (!(value - '0' <= 9 && value - '0' >= 0)) {
					// 下一个不是数字，那就直接写进栈
					numStack.push(Integer.parseInt(keepNum));
					keepNum = "";
				} // 否则--》下一个是数字，那这轮就不写，等下一轮判定
				break;
			case '(':
				operStack.push('(');
				// 调试出现一个bug，当第一个就是一个（，那会怎么样？，那就会一开始字符站就有一个字符，导致字符站不为null
				// 第一次添加运算符号本应直接入栈，但由于栈不为null，导致第一个运算符没直接加进去，这里还是应该就按照最初设想，设一个参数表明上一个符号是（的
				lastKuohao = true;
				break;
			case ')':
				operStack.push(')');
				secondeKuohao = true;
				break;
			case '*':
			case '/':
			case '%':
				// 符号栈为空
				if (operStack.isEmpty() || lastKuohao == true || secondeKuohao == true) {
					operStack.push(value);
					lastKuohao = false;
					if (secondeKuohao == true) {
						if (++count == 2) {
							secondeKuohao = false;
							count = 0;
						}
					}
					break;
				}
				// 符号栈不为null
				// 取出栈顶符号
				char tmp = operStack.pop();
				// 比较符号优先级
				// 优先级大于或等于 》》注意，等于必须和大于一起判断，如果和小于一起判断，那就可能会出现
				// 几个优先级一样的*/在一起，之后来个+，只取出了第一个*或/，然后就+进来了，出现问题
				int a = numStack.pop();
				int b = numStack.pop();
				switch (tmp) {

				// 先取数计算
				case '*':
					numStack.push(b * a);
					break;
				case '/':
					numStack.push(b / a);
					break;
				case '%':
					numStack.push(b % a);
					break;
				// 添加

				case '(':
				case ')':
				case '+':
				case '-':
					// 将取出来数据重新入栈
					// 顺序恒重要
					numStack.push(b);
					numStack.push(a);
					operStack.push(tmp);
					break;
				}
				// 新符号入栈
				operStack.push(value);
				break;
			// 加减操作的话符号栈只要有符号，就把栈顶符号取出来计算，新符号入栈--》栈顶只会有两种情况：*%/中的一个 或+-
			case '+':
			case '-':
				// 符号栈为空
				if (operStack.isEmpty() || lastKuohao == true || secondeKuohao == true) {
					operStack.push(value);
					lastKuohao = false;
					if (secondeKuohao == true) {
						if (++count == 2) {
							secondeKuohao = false;
							count = 0;
						}
					}
					break;
				}
				// 符号栈不为null
				// 取出栈顶符号
				char tmp2 = operStack.pop();
				// 比较符号优先级
				int a2 = numStack.pop();
				int b2 = numStack.pop();
				switch (tmp2) {
				// 先取数计算
				case '*':
					numStack.push(b2 * a2);
					break;
				case '/':
					numStack.push(b2 / a2);
					break;
				case '%':
					numStack.push(b2 % a2);
					break;
				// 添加
				case '+':
					numStack.push(b2 + a2);
					break;
				case '-':
					numStack.push(b2 - a2);
					break;
				case '(':
				case ')':
					// 将取出来数据重新入栈
					numStack.push(b2);
					numStack.push(a2);
					operStack.push(tmp2);
					break;
				}
				// 新符号入栈
				operStack.push(value);
				break;
			}
		}
		// 数据 符号进栈结束

		// 开始结果计算
		answer(numStack, operStack);
		int answer = numStack.pop();
		System.out.println(answer);

	}

	public static void answer(ArrayStack<Integer> numStack, ArrayStack<Character> operStack) {
		char symbol1 = operStack.pop();
		if (symbol1 == '(') {
			// 递归结束
			return;
		}
		if (symbol1 == ')') {
			// 倒数第一个符号是） ，则把倒数第二个符号入栈
			answer(numStack, operStack);// 递归，将（）内的结果算完后放到数据栈中
			// 重新拿出symbol
			symbol1 = operStack.pop();
		}
		int first = numStack.pop();

		// 如果倒数第二个是‘）’，那么第一个符号和数不参与递归
		if (operStack.size() >= 1) {// 前面取出一个后还有一个
			char symbol2 = operStack.pop();
			if (symbol2 == ')') {
				answer(numStack, operStack);
			} else {
				operStack.push(symbol2);
			}
		}

		int second = numStack.pop();
		// 计算按symbol1
		switch (symbol1) {
		case '*':
			numStack.push(second * first);
			break;
		case '/':
			numStack.push(second / first);
			break;
		case '%':
			numStack.push(second % first);
			break;
		// 添加
		case '+':
			numStack.push(second + first);
			break;
		case '-':
			numStack.push(second - first);
			break;
		}
		//
		if (operStack.isEmpty()) {
			return;
		}
		answer(numStack, operStack);// 递归
	}
}
