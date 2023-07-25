package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


//采用后缀表达式实现 表达式求值
public class PolishNotation {

	public static void main(String[] args) {

		String infixExpression = "1+((2+3)*4)-5";
		// 首先，因为直接对String类型进行遍历不方便，将中缀表达式转换为和他一样顺序的List 方便操作
		List<String> infixList = toInfixList(infixExpression);
		System.out.println(infixList);

		// 接下来就是最关键的如何把中缀表达式转换为后缀表达式
		List<String> postfixList = infixToPostfix(infixList);
		System.out.println(postfixList);

		// 计算后缀表达式
		int res = calculate(postfixList);
		System.out.println(res);

		
	}

	// 写一个方法 ，把特定格式的字符串转换为list
	public static List<String> toInfixList(String infixExpression) {
		List<String> list = new ArrayList<>();
		int length = infixExpression.length();
		char tmp = ' '; // 用于存放每次取出来的元素
		String nextNum = ""; // 用于连接数字，当数字多个的时候
		for (int i = 0; i < length;) {
			// 如果是运算符
			tmp = infixExpression.charAt(i);
			if (tmp < '0' || tmp > '9') {
				list.add("" + tmp);
				i++;
			} else {
				// 数字，循环添加
				while (i < length
						&& ((tmp = infixExpression.charAt(i)) >= '0' && (tmp = infixExpression.charAt(i)) <= '9')) {
					nextNum += tmp;
					i++;
				} // 这里构建的这个结构值得学习，，一些细节
				/*
				 * 起初我把 tmp=infixExpression.charAt(i) 放到循环里 ，但其实在这个循环里无论放在什么位置都不合理，都要去 讨论这个 i
				 * 的值的大小对tmp=infixExpression.charAt(i)的影响
				 * 如果放在最开始，那么每次i++之后不会立刻更新tmp，得下一轮，那就导致会把一个符号和一个数字连一起 如果放最后面，那更不行，当i = length-1
				 * 时，对于循环而言，是合法的，他会进行最后一次的nexNum+，但之后i++，i=length了，那就不能在charAt(i)了，报错出现异常
				 * 把这个赋值放到判断里，只要前面的符合，哪赋值一定合法
				 */
				list.add(nextNum);// 把数字写进List去
				// nextNum制空
				nextNum = "";
			}
		}
		return list;
	}

	// 写一个方法，中缀转后缀
	public static List<String> infixToPostfix(List<String> ls) {
		// 传一个中缀表达式List

		// 创建一个中间栈S1 ，第二个结果栈 使用List来替换，效果更优
		Stack<String> s1 = new Stack<>();
		List<String> list = new ArrayList<>();
		// 使用增强for来遍历ls
		for (String item : ls) {
			// 如果是数字，使用正则表达式，直接加入List
			if (item.matches("\\d+")) {
				list.add(item);
				// 如果是左括号，直接压入s1
			} else if (item.equals("(")) {
				s1.push(item);
				// 如果是右括号,s1循环出栈，直到左括号出现
			} else if (item.equals(")")) {
				while (!(s1.peek().equals("("))) {
					// 当s1为空，或者栈顶元素为左括号 时结束
					list.add(s1.pop());
				}
				// 取出栈顶的左括号
				s1.pop();

			} else {
				// 以下是当item是运算符的情况
				// 如果栈顶元素优先级大于等于item，那就将栈中元素出栈，继续比较
				while (!(s1.isEmpty()) && !(Operation.getPriority(item) > Operation.getPriority(s1.peek()))) {
					// 这样的，首先s1栈肯定不能为空，后面这个优先级就包含很多东西了，如果栈顶是左括号，优先级被为0，那后面这个式子就不会成立，循环中断，这个继续比较的
					// 含义就在这个判断里
					// 结合这一整个else来看，这里做的事就是，
					// 先判断s1是否为空 ，为空 ，那就结束循环，item符号压栈，
					// 前者不为空 ，在判断 item优先级是不是高于栈顶元素优先级，是的话，结束循环，item压栈，这里也吧括号在顶上的情况包含了
					// 然后当上面的都不满足，即 栈顶元素优先级高于item，那就出栈进队，继续下一轮循环
					list.add(s1.pop());
				}
				s1.push(item);// 要有一个清晰的认知，只要是运算符肯定都是要压到s1栈的，关键在于他会把多少s1中的原有符号剃到list中
			}
		}
		// 当扫完中表达式之后，把s1中的表达式全部写入list中
		for (int i = 0; i < s1.size(); i++) {
			list.add(s1.pop());
		}
		// 因为没有用栈来存储结果，链表是有序的，所以list就是后后序表达式
		return list;
	}

	// 计算后缀表达式
	public static int calculate(List<String> posfixList) {
		Stack<Integer> stack = new Stack<>();
		for (String item : posfixList) {
			// 数字，入栈
			if (item.matches("\\d+")) {
				stack.push(Integer.parseInt(item));
			} else {
				// 遇到运算符，出栈两个栈顶数，运算
				Integer a =null;
				switch(item) {
				case "+":
					stack.push(stack.pop()+stack.pop());
					break;
				case "-":
					a =stack.pop();
					stack.push(stack.pop()-a);//后出来的减先出来的
					break;
				case "*":
					stack.push(stack.pop()*stack.pop());
					break;
				case "/":
					a = stack.pop();
					stack.push(stack.pop()/a);
					break;
				case "%":
					a= stack.pop();
					stack.push(stack.pop()%a);
					
				}
			}
		}
		return stack.pop();
	}
}

//写一个类来返回运算符的优先级
class Operation {
	private static int BRA = 0;// 括号
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;
	private static int REM = 2;// 取余

	public static int getPriority(String str) {
		int res = 0;
		switch (str) {
		case "+":
			res = ADD;
			break;
		case "-":
			res = SUB;
			break;
		case "*":
			res = MUL;
			break;
		case "/":
			res = DIV;
			break;
		case "%":
			res = REM;
			break;
		case "(":
			res = BRA;
			break;
		default:
			System.out.println("找不到该符号");
		}
		return res;
	}
}