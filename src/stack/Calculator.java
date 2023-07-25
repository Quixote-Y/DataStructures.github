package stack;
/**
 * ����׺���ʽʵ�ֱ��ʽ��ֵ�������˺ܶ�ʱ�䣬���ɴ��ںܶ����⣬�������޷�����(20+10)%2+(10+30)/3���������⣬��Ϊ��׺���ʽ�����ڼ������������ʱ����Ҫ���ǵĶ����е�̫����
 *   ����������ȼ� ���ŵķ�Χ ���ر������Ŷ����������Ĺ���Ӱ�죬���ɻ���û���� 
 *   
 */


import java.util.Scanner;

//����ջ  ����ջ ʵ�����ݱ��ʽ����
public class Calculator {

	public static void main(String[] args) {
		ArrayStack<Integer> numStack = new ArrayStack<>(10); // ����վ
		ArrayStack<Character> operStack = new ArrayStack<>(10); // ����ջ

		Scanner scanner = new Scanner(System.in);

		System.out.println("�������������ı��ʽ��");
		String expression = scanner.next();

		// String expression = "(2+2*9)*(2+3)";
		boolean lastKuohao = false; // �ж���һ�������Ƿ��ǡ��������ţ����Ϊtrue����һ������ֱ����ջ
		boolean secondeKuohao = false; // �ж���һ�������Ƿ��ǡ��������ţ����Ϊtrue ��Ͻ��������ֱ����ջ����Ϊ�����ź���ظ���һ�����ţ��ĺ���ĵڶ�������ҲӦ��ֱ�����
		int count = 0;// ���ڼ�¼�����ź�����ֵĵڼ�������

		String keepNum = ""; // �����ֶ�λ��ʱ����ƴ��

		for (int i = 0; i < expression.length(); i++) {
			char value = expression.charAt(i);
			switch (value) {
			// ����
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
				// ����ֱ����ջ��ֱ����ջ�Ļ����޷������λ�������
				keepNum += value; // �ַ�ƴ��
				// �ж���һ���ַ��ǲ�������
				if (i == expression.length() - 1) {
					numStack.push(Integer.parseInt(keepNum));
					break;
				}
				value = expression.charAt(i + 1);
				if (!(value - '0' <= 9 && value - '0' >= 0)) {
					// ��һ���������֣��Ǿ�ֱ��д��ջ
					numStack.push(Integer.parseInt(keepNum));
					keepNum = "";
				} // ����--����һ�������֣������־Ͳ�д������һ���ж�
				break;
			case '(':
				operStack.push('(');
				// ���Գ���һ��bug������һ������һ�������ǻ���ô�������Ǿͻ�һ��ʼ�ַ�վ����һ���ַ��������ַ�վ��Ϊnull
				// ��һ�����������ű�Ӧֱ����ջ��������ջ��Ϊnull�����µ�һ�������ûֱ�Ӽӽ�ȥ�����ﻹ��Ӧ�þͰ���������룬��һ������������һ�������ǣ���
				lastKuohao = true;
				break;
			case ')':
				operStack.push(')');
				secondeKuohao = true;
				break;
			case '*':
			case '/':
			case '%':
				// ����ջΪ��
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
				// ����ջ��Ϊnull
				// ȡ��ջ������
				char tmp = operStack.pop();
				// �ȽϷ������ȼ�
				// ���ȼ����ڻ���� ����ע�⣬���ڱ���ʹ���һ���жϣ������С��һ���жϣ��ǾͿ��ܻ����
				// �������ȼ�һ����*/��һ��֮������+��ֻȡ���˵�һ��*��/��Ȼ���+�����ˣ���������
				int a = numStack.pop();
				int b = numStack.pop();
				switch (tmp) {

				// ��ȡ������
				case '*':
					numStack.push(b * a);
					break;
				case '/':
					numStack.push(b / a);
					break;
				case '%':
					numStack.push(b % a);
					break;
				// ���

				case '(':
				case ')':
				case '+':
				case '-':
					// ��ȡ��������������ջ
					// ˳�����Ҫ
					numStack.push(b);
					numStack.push(a);
					operStack.push(tmp);
					break;
				}
				// �·�����ջ
				operStack.push(value);
				break;
			// �Ӽ������Ļ�����ջֻҪ�з��ţ��Ͱ�ջ������ȡ�������㣬�·�����ջ--��ջ��ֻ�������������*%/�е�һ�� ��+-
			case '+':
			case '-':
				// ����ջΪ��
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
				// ����ջ��Ϊnull
				// ȡ��ջ������
				char tmp2 = operStack.pop();
				// �ȽϷ������ȼ�
				int a2 = numStack.pop();
				int b2 = numStack.pop();
				switch (tmp2) {
				// ��ȡ������
				case '*':
					numStack.push(b2 * a2);
					break;
				case '/':
					numStack.push(b2 / a2);
					break;
				case '%':
					numStack.push(b2 % a2);
					break;
				// ���
				case '+':
					numStack.push(b2 + a2);
					break;
				case '-':
					numStack.push(b2 - a2);
					break;
				case '(':
				case ')':
					// ��ȡ��������������ջ
					numStack.push(b2);
					numStack.push(a2);
					operStack.push(tmp2);
					break;
				}
				// �·�����ջ
				operStack.push(value);
				break;
			}
		}
		// ���� ���Ž�ջ����

		// ��ʼ�������
		answer(numStack, operStack);
		int answer = numStack.pop();
		System.out.println(answer);

	}

	public static void answer(ArrayStack<Integer> numStack, ArrayStack<Character> operStack) {
		char symbol1 = operStack.pop();
		if (symbol1 == '(') {
			// �ݹ����
			return;
		}
		if (symbol1 == ')') {
			// ������һ�������ǣ� ����ѵ����ڶ���������ջ
			answer(numStack, operStack);// �ݹ飬�������ڵĽ�������ŵ�����ջ��
			// �����ó�symbol
			symbol1 = operStack.pop();
		}
		int first = numStack.pop();

		// ��������ڶ����ǡ���������ô��һ�����ź���������ݹ�
		if (operStack.size() >= 1) {// ǰ��ȡ��һ������һ��
			char symbol2 = operStack.pop();
			if (symbol2 == ')') {
				answer(numStack, operStack);
			} else {
				operStack.push(symbol2);
			}
		}

		int second = numStack.pop();
		// ���㰴symbol1
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
		// ���
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
		answer(numStack, operStack);// �ݹ�
	}
}
