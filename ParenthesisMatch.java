package leetcodetest;

import java.awt.List;
import java.util.ArrayList;
import java.util.Stack;


public class ParenthesisMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public int longestValidParentheses(String s) {
    	Stack<String> parentStack = new Stack<>();
    	int subSize = 0;
    	int subsubsize = 0;
    	int outputsize = 0;
        for (int i = 0; i < s.length(); i++) {
        	String str = s.substring(i,i+1);
        	System.out.print(str);
			if(str.equals("(")) {
				parentStack.add(str);
			}else if(str.equals(")") && parentStack.size()!=0) {
				parentStack.pop();
                System.out.print("size:" + parentStack.size()+" ");
				if(parentStack.size()==0) {
					subSize = subSize + 2 +subsubsize;
					if(subSize>outputsize) {
						outputsize = subSize;
					}
					subsubsize = 0;
				}else {
					subsubsize = subsubsize +2;
					if(subsubsize>outputsize) {
						outputsize = subsubsize;
					}
				}
                System.out.print("subsize:" + subSize+" ");
			}else if(i!=s.length()-1) {
                subSize = 0;
				subsubsize = 0;
			}
		}
        return outputsize;
    }

   /* public List<String> generateParenthesis(int n) {
        ArrayList<String> results = new ArrayList<String>();
        int leftNum=n,rightNum=n;
        parentheses("", results, leftNum, rightNum);
        return results;
    }*/
    public static void parentheses(String sublist, ArrayList<String> results,int leftNum,int rightNum) {
		if(leftNum==0&&rightNum==0) {
			results.add(sublist);
		}
		if(rightNum>leftNum) {
			parentheses(sublist+")", results, leftNum, rightNum-1);
		}
		if(leftNum>0) {
			parentheses(sublist+"(", results, leftNum-1, rightNum);
		}
	}
}
