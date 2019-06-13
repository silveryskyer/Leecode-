package leetcodetest;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import jdk.internal.org.jline.reader.Candidate;
import sun.tools.java.AmbiguousClass;

public class StrProcess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "12345";
		
		System.out.print(countAndSay(5));
	}
	
    public List<List<Integer>> subsets(int[] nums) {
    	List<List<Integer>> ans = new ArrayList<>();
    	ans.add(new ArrayList<>());
    	if(nums == null || nums.length==0) {
    		return ans;
    	}
    	for (int i = 0; i < nums.length; i++) {
			int size = ans.size();
			for (int j = 0; j < size; j++) {
				List<Integer> subset = new ArrayList<>(ans.get(j));
				subset.add(nums[i]);
				ans.add(subset);
			}
		}
    	return ans;
    }
	
    public String getPermutation(int n, int k) {
    	StringBuilder sb = new StringBuilder();
    	//候选数字
    	List<Integer> candidates = new ArrayList<>();
    	//分母的阶乘数
    	int[] factorials = new int[n+1];
    	factorials[0] = 1;
    	int fact = 1;
    	for (int i = 1; i <= n; ++i) {
			candidates.add(i);
			fact *= i;
			factorials[i] = fact;
		}
    	k -= 1;
    	for (int i = n-1; i >=0; --i) {
			//计算候选数字的index
    		int index = k/factorials[i];
    		sb.append(candidates.remove(index));
    		k -= index*factorials[i];
		}
        return sb.toString();
    }
	
	//动态规划
    public boolean isMatch(String s, String p) {
    	boolean[][] value = new boolean[p.length()+1][s.length()+1];
    	value[0][0] = true;
    	for (int i = 1; i < s.length(); i++) {
			value[0][i] = false;
		}
    	for (int i = 1; i < p.length(); i++) {
			if(p.charAt(i-1) == '*') {
				value[i][0] = value[i-1][0];
				for (int j = 1; j < s.length(); j++) {
					value[i][j] = (value[i][j-1]||value[i-1][j]);
				}
			}else if(p.charAt(i-1) == '?') {
				value[i][0] = false;
				for (int j = 1; j < s.length(); j++) {
					value[i][j] = value[i-1][j-1];
				}
			}else {
				value[i][0] = false;
				for (int j = 1; j < s.length(); j++) {
					value[i][j] = s.charAt(j-1) == p.charAt(i-1) && value[i-1][j-1];
				}
			}
		}
    	return value[p.length()][s.length()];
    }
	
    public static String countAndSay(int n) {
    	Map<String, String> map = new HashMap<String, String>();
    	String outStr = ""; 
        if(n==1) {
        	outStr = "1";
        	return outStr;
        }
        if(n==2){
            return "11";
        }
        String preStr = countAndSay(n-1);
        System.out.print("preStr:"+preStr+" ");
        int count = 1;
        for(int i=0;i<preStr.length();i++) {
        	if(i==preStr.length()-1) {
        		map.put(String.valueOf(preStr.charAt(i)), String.valueOf(count));
        	}else {
				if(String.valueOf(preStr.charAt(i)).equals(String.valueOf(preStr.charAt(i+1)))){
					count++;
				}
				else {
					System.out.print("i:"+i+" ");
					System.out.print("test:" + preStr.charAt(i) + " ");
					System.out.print("count:"+count+" ");
					map.put(String.valueOf(preStr.charAt(i)), String.valueOf(count));
					count = 1;
				}
			}
        }
        //System.out.print(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
        	outStr = entry.getValue() + entry.getKey() + outStr;
        }
        return outStr;
    }
	
    /*public List<Integer> findSubstring(String s, String[] words) {
    	if(s.length()==0||words.length==0) {
    		return null;
    	}
    	List<Integer> outList = new ArrayList<>();
    	List<String> wordList = Arrays.asList(words);
        int len = words[0].length()*words.length;
        int wlen = words[0].length();
        if(s.length()<len) {
        	return null;
        }
        for (int i = 0; i < s.length()-len+1; i++) {
        	int temp = 0;
			String subStr = s.substring(i, i+len);
			System.out.print(subStr+" ");
			for (int j = temp; j < words.length; j++) {
				for(int k = 0;k<words.length;k++) {
					if(subStr.substring(j*wlen,j*wlen+wlen).equals(wordList[k])) {
						temp ++;
						System.out.print(temp+" ");
						break;
					}
				}
				if(temp<j+1) {
					break;
				}
			}
			if(temp==words.length) {
				outList.add(i);
			}
		}
        return outList;
    }*/
    
    
	
    public int strStr(String haystack, String needle) {
    	if(needle.length()==0) {
    		return 0;
    	}
    	//调用内置函数法
    	/*if(haystack.length()<needle.length()) {
    		return-1;
    	}
        for(int i=0; i<(haystack.length()-needle.length()+1);i++) {
        	System.out.print(haystack.substring(i, i+needle.length())+" ");
        	if(haystack.substring(i, i+needle.length()).equals(needle)) {
        		return i;
        	}
        }
        return -1;*/
        //KMP法
        int[] next = getNext(needle.toCharArray());
        int i=0,j=0;
        while (i<haystack.length()&&j<needle.length()) {
			if(j==-1||haystack.charAt(i)==needle.charAt(j)) {
				i++;
				j++;
			}else {
				j=next[j];
			}
		}
        if(j==needle.length())
        	return i-j;
        else {
			return -1;
		}
    }
    
    public  int[] getNext(char[] pstr) {
		int[] next = new int[pstr.length+1];
		int i=0,j=-1;
		next[0] = -1;
		while (i<pstr.length) {
			if(j==-1||pstr[i]==pstr[j]) {
				i++;
				j++;
				next[i] = j;
			}else
				j=next[j];
		}
		return next;
	}

}
