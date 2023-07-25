package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


//���ú�׺���ʽʵ�� ���ʽ��ֵ
public class PolishNotation {

	public static void main(String[] args) {

		String infixExpression = "1+((2+3)*4)-5";
		// ���ȣ���Ϊֱ�Ӷ�String���ͽ��б��������㣬����׺���ʽת��Ϊ����һ��˳���List �������
		List<String> infixList = toInfixList(infixExpression);
		System.out.println(infixList);

		// ������������ؼ�����ΰ���׺���ʽת��Ϊ��׺���ʽ
		List<String> postfixList = infixToPostfix(infixList);
		System.out.println(postfixList);

		// �����׺���ʽ
		int res = calculate(postfixList);
		System.out.println(res);

		
	}

	// дһ������ �����ض���ʽ���ַ���ת��Ϊlist
	public static List<String> toInfixList(String infixExpression) {
		List<String> list = new ArrayList<>();
		int length = infixExpression.length();
		char tmp = ' '; // ���ڴ��ÿ��ȡ������Ԫ��
		String nextNum = ""; // �����������֣������ֶ����ʱ��
		for (int i = 0; i < length;) {
			// ����������
			tmp = infixExpression.charAt(i);
			if (tmp < '0' || tmp > '9') {
				list.add("" + tmp);
				i++;
			} else {
				// ���֣�ѭ�����
				while (i < length
						&& ((tmp = infixExpression.charAt(i)) >= '0' && (tmp = infixExpression.charAt(i)) <= '9')) {
					nextNum += tmp;
					i++;
				} // ���ﹹ��������ṹֵ��ѧϰ����һЩϸ��
				/*
				 * ����Ұ� tmp=infixExpression.charAt(i) �ŵ�ѭ���� ������ʵ�����ѭ�������۷���ʲôλ�ö���������Ҫȥ ������� i
				 * ��ֵ�Ĵ�С��tmp=infixExpression.charAt(i)��Ӱ��
				 * ��������ʼ����ôÿ��i++֮�󲻻����̸���tmp������һ�֣��Ǿ͵��»��һ�����ź�һ��������һ�� ���������棬�Ǹ����У���i = length-1
				 * ʱ������ѭ�����ԣ��ǺϷ��ģ�����������һ�ε�nexNum+����֮��i++��i=length�ˣ��ǾͲ�����charAt(i)�ˣ���������쳣
				 * �������ֵ�ŵ��ж��ֻҪǰ��ķ��ϣ��ĸ�ֵһ���Ϸ�
				 */
				list.add(nextNum);// ������д��Listȥ
				// nextNum�ƿ�
				nextNum = "";
			}
		}
		return list;
	}

	// дһ����������׺ת��׺
	public static List<String> infixToPostfix(List<String> ls) {
		// ��һ����׺���ʽList

		// ����һ���м�ջS1 ���ڶ������ջ ʹ��List���滻��Ч������
		Stack<String> s1 = new Stack<>();
		List<String> list = new ArrayList<>();
		// ʹ����ǿfor������ls
		for (String item : ls) {
			// ��������֣�ʹ��������ʽ��ֱ�Ӽ���List
			if (item.matches("\\d+")) {
				list.add(item);
				// ����������ţ�ֱ��ѹ��s1
			} else if (item.equals("(")) {
				s1.push(item);
				// �����������,s1ѭ����ջ��ֱ�������ų���
			} else if (item.equals(")")) {
				while (!(s1.peek().equals("("))) {
					// ��s1Ϊ�գ�����ջ��Ԫ��Ϊ������ ʱ����
					list.add(s1.pop());
				}
				// ȡ��ջ����������
				s1.pop();

			} else {
				// �����ǵ�item������������
				// ���ջ��Ԫ�����ȼ����ڵ���item���Ǿͽ�ջ��Ԫ�س�ջ�������Ƚ�
				while (!(s1.isEmpty()) && !(Operation.getPriority(item) > Operation.getPriority(s1.peek()))) {
					// �����ģ�����s1ջ�϶�����Ϊ�գ�����������ȼ��Ͱ����ܶණ���ˣ����ջ���������ţ����ȼ���Ϊ0���Ǻ������ʽ�ӾͲ��������ѭ���жϣ���������Ƚϵ�
					// �����������ж���
					// �����һ����else���������������¾��ǣ�
					// ���ж�s1�Ƿ�Ϊ�� ��Ϊ�� ���Ǿͽ���ѭ����item����ѹջ��
					// ǰ�߲�Ϊ�� �����ж� item���ȼ��ǲ��Ǹ���ջ��Ԫ�����ȼ����ǵĻ�������ѭ����itemѹջ������Ҳ�������ڶ��ϵ����������
					// Ȼ������Ķ������㣬�� ջ��Ԫ�����ȼ�����item���Ǿͳ�ջ���ӣ�������һ��ѭ��
					list.add(s1.pop());
				}
				s1.push(item);// Ҫ��һ����������֪��ֻҪ��������϶�����Ҫѹ��s1ջ�ģ��ؼ���������Ѷ���s1�е�ԭ�з����굽list��
			}
		}
		// ��ɨ���б��ʽ֮�󣬰�s1�еı��ʽȫ��д��list��
		for (int i = 0; i < s1.size(); i++) {
			list.add(s1.pop());
		}
		// ��Ϊû����ջ���洢���������������ģ�����list���Ǻ������ʽ
		return list;
	}

	// �����׺���ʽ
	public static int calculate(List<String> posfixList) {
		Stack<Integer> stack = new Stack<>();
		for (String item : posfixList) {
			// ���֣���ջ
			if (item.matches("\\d+")) {
				stack.push(Integer.parseInt(item));
			} else {
				// �������������ջ����ջ����������
				Integer a =null;
				switch(item) {
				case "+":
					stack.push(stack.pop()+stack.pop());
					break;
				case "-":
					a =stack.pop();
					stack.push(stack.pop()-a);//������ļ��ȳ�����
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

//дһ��������������������ȼ�
class Operation {
	private static int BRA = 0;// ����
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;
	private static int REM = 2;// ȡ��

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
			System.out.println("�Ҳ����÷���");
		}
		return res;
	}
}