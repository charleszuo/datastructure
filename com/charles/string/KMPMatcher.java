package com.charles.string;

/** 
 * @author charleszuo@126.com
 * next函数计算的是字串每个字符的比较位置
 * 第一个字符的next值设为-1，表示不在当前字符位置中
 * 如果前缀等于后缀，那么后缀的下一个位置 = 前缀的下一个位置。所以通过比较当前i（后缀）字符和
 * J（前缀）字符，可以得出i下一个位置的next值
 * 这个程序最关键的是理解：比较当前字符是为来计算下一个字符的next值
 */
public class KMPMatcher {
	
	public static int[] next(String target){
		char[] t = target.toCharArray();
		int[] next = new int[target.length()];
		int i = 0, j = -1;
		next[0] = -1;
		while(i < target.length() - 1){
			if(j == -1 || t[i] == t[j]){
				i++;
				j++;
				next[i] = j;
			}else{
				j = next[j];
			}
		}
		return next;
	}
	
	/* nextPro函数是改进的next函数，如果i和j后移之后t[i] = t[j]，表示该位置的下一个位置的元素和该位置相同，所以还要回溯到下一个next[j]
	 * 这样避免了当前字符和next字符相同时的多余比较，直接回退到next字符的再next位置
	 */
	public static int[] nextPro(String target){
		char[] t = target.toCharArray();
		int[] next = new int[target.length()];
		int i = 0, j = -1;
		next[0] = -1;
		while(i < target.length() - 1){
			if(j == -1 || t[i] == t[j]){
				i++;
				j++;
				// 改进的地方在此，本来是直接next[i] = j。这里多了一步t[i]和t[j]的比较，如果相同，继续回溯
				if(t[i] == t[j]){
					next[i] = next[j];
				}else{
					next[i] = j;
				}
			}else{
				j = next[j];
			}
		}
		return next;
	}
	
	/* KMP匹配，i指针指向父串，不回溯，j指针指向字串，通过next函数确定j指针的回溯位置
	 * 
	 */
	public static int KMPIndex(String s, String t){
		if(s.length() < t.length()){
			return -1;
		}
		int i = 0, j = 0;
		char[] sChars = s.toCharArray();
		char[] tChars = t.toCharArray();
		int[] next = next(t);
		while(i < sChars.length && j < tChars.length){
			if(j == -1 || sChars[i] == tChars[j]){
				i++;
				j++;
			}else{
				j = next[j];
			}
		}
		if(j == tChars.length){
			return i - tChars.length;
		}
		
		return -1;
	}
	
	/* KMP改进后的匹配，i指针指向父串，不回溯，j指针指向字串，通过next函数确定j指针的回溯位置
	 * 
	 */
	public static int KMPProIndex(String s, String t){
		if(s.length() < t.length()){
			return -1;
		}
		int i = 0, j = 0;
		char[] sChars = s.toCharArray();
		char[] tChars = t.toCharArray();
		//使用改进后的nextPro函数
		int[] next = nextPro(t);
		while(i < sChars.length && j < tChars.length){
			if(j == -1 || sChars[i] == tChars[j]){
				i++;
				j++;
			}else{
				j = next[j];
			}
		}
		if(j == tChars.length){
			return i - tChars.length;
		}
		
		return -1;
	}
	
	public static void main(String[] args){
		int[] nexts = next("aaab");
		for(int i : nexts){
			System.out.println(i);
		}
		System.out.println("---------------------");
		System.out.println(KMPIndex("abcabdabcd", "abcabx"));
		System.out.println(KMPIndex("abcabdabcd", "abdabc"));
		System.out.println(KMPIndex("abcabdabcd", "bc"));
		System.out.println("---------------------");
		System.out.println(KMPProIndex("abcabdabcd", "abcabx"));
		System.out.println(KMPProIndex("abcabdabcd", "abdabc"));
		System.out.println(KMPProIndex("abcabdabcd", "bc"));
		System.out.println(KMPProIndex("abcaaaaaabd", "aaaaab"));
	}
}
