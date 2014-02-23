package com.charles.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author charleszuo@126.com
 * 中缀表达式转逆波兰表达式(后缀表达式)
 * 示例: 1 + (9 + 30 + 1) * (1 + 100 / 2) + 4  转换成 1 9 30 + 1 + 1 100 2 / + * + 4 + 
 * 算法思路: 1）数字直接打印
 * 		   2）遇到运算符，先跟栈顶运算符比较优先级，当栈顶运算符优先级大于等于该运算符时，栈内元素依次出栈，直到栈顶元素优先级小于该运算符。然后该运算符进栈
 * 		   3）'(' 直接进栈，且优先级最低
 *         4) 遇到')' ，栈内元素依次出栈，直到遇到'('。'('再出栈
 *         5）遍历完成后，栈内元素全部出栈 
 */
public class RPNExpression {
	
	public static String middleToRPN(String middleExpression){
		String[] expression = expressionToArray(middleExpression);
		StringBuilder rpnExpression = new StringBuilder();
		java.util.Stack<Character> operatorStack = new java.util.Stack<Character>();
		for(String item : expression){
			if("+".equals(item) || "-".equals(item) || "*".equals(item) || "/".equals(item)){
				if(operatorStack.size() == 0){
					operatorStack.push(item.charAt(0));
				}else if(compareOperator(item.charAt(0), operatorStack.peek()) == 1){
					// 待比较元素大于栈定元素才进栈，其他情况都出栈
					operatorStack.push(item.charAt(0));
				}else{
					// 出栈
					rpnExpression.append(operatorStack.pop()).append(" ");
					while(operatorStack.size() > 0){
						if(compareOperator(item.charAt(0), operatorStack.peek()) == 1){
							operatorStack.push(item.charAt(0));
							break;
						}else{
							rpnExpression.append(operatorStack.pop()).append(" ");
						}
					}
					if(operatorStack.size() == 0){
						operatorStack.push(item.charAt(0));
					}
				}
			}else if("(".equals(item)){
				operatorStack.push('(');
			}else if(")".equals(item)){
				while('(' != operatorStack.peek()){
					rpnExpression.append(operatorStack.pop()).append(" ");
				}
				operatorStack.pop();
			}else{
				rpnExpression.append(item).append(" ");
			}
		}
		// 最后全部出栈
		while(operatorStack.size() > 0){
			rpnExpression.append(operatorStack.pop()).append(" ");
		}
		return rpnExpression.toString();
	}
	
	public static float expressionCalculate(String middleExpression){
		String rpnExpression = middleToRPN(middleExpression);
		return rpnExpressionCalculate(rpnExpression);
	}
	
	/* 逆波兰表达式(后缀表达式) 数学运算
	 * 示例: 1 9 30 + 1 + 1 100 2 / + * + 4 + 计算
	 * 算法思路: 1）数字直接进栈
	 * 		   2）遇到运算符，连续出栈两次，然后根据运算符计算，将结果进栈
	 * 		   3）最后将结果出栈
	 */
	private static float rpnExpressionCalculate(String rpnExpressionStr){
		String[] rpnExpression = expressionToArray(rpnExpressionStr);
		java.util.Stack<Float> stack = new java.util.Stack<Float>();
		for(String item : rpnExpression){
			if("+".equals(item) || "-".equals(item) || "*".equals(item) || "/".equals(item)){
				float f1 = stack.pop();
				float f2 = stack.pop();
				switch(item.charAt(0)){
				case '+':
					stack.push(f2 + f1);
					break;
				case '-':
					stack.push(f2 - f1);
					break;
				case '*':
					stack.push(f2 * f1);
					break;
				case '/':
					stack.push(f2 / f1);
					break;
				}
			}else {
				stack.push(Float.valueOf(item));
			}
			
		}
		return stack.pop();
	}
	
	private static String[] expressionToArray(String expressionStr){
		char[] chars = expressionStr.trim().toCharArray();
		
		List<String> expressionArray = new ArrayList<String>();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < chars.length; i++){
			char c = chars[i];
			if(Character.isDigit(c)){
				sb.append(c);
			}else if(!Character.isSpaceChar(c)){
				if(sb.length() > 0){
					expressionArray.add(sb.toString());
					sb.delete(0, sb.length());
				}
				expressionArray.add(String.valueOf(c));
			}else{
				if(sb.length() > 0){
					expressionArray.add(sb.toString());
					sb.delete(0, sb.length());
				}
			}
			
			if(i == chars.length - 1){
				if(sb.length() > 0){
					expressionArray.add(sb.toString());
					sb.delete(0, sb.length());
				}
			}
		}
		String[] result = new String[expressionArray.size()];
		return expressionArray.toArray(result);
	}
	
	private static int compareOperator(char o1, char o2){
		if(o2 == '('){
			return 1;
		}
		
		if(o1 == o2){
			return 0;
		}else if((o1 == '*' && o2 == '/') || (o1 == '/' && o2 == '*') || (o1 == '+' && o2 == '-') || (o1 == '-' && o2 == '+')){
			return 0;
		}else if((o1 == '*' && o2 == '+') || (o1 == '*' && o2 == '-') || (o1 == '/' && o2 == '+') || (o1 == '/' && o2 == '-')){
			return 1;
		}else if((o1 == '+' && o2 == '*') || (o1 == '-' && o2 == '*') || (o1 == '+' && o2 == '/') || (o1 == '-' && o2 == '/')){
			return -1;
		}
		
		return 0;
	}
	
	public static void main(String[] args){
		// String expressionStr = "9 + (30 + 1) * 3 + 10 / 2";
		String expressionStr = "1 + ((9 + 30 + 1) * (1 + 100 / 2) + 1)+ 4 / 2";
		System.out.println(middleToRPN(expressionStr));
		System.out.println(expressionCalculate(expressionStr));
	
	}
}
