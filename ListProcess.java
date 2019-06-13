package leetcodetest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListProcess {

	public static void main(String[] args) {
		String aString = "aqwer";
		char[] c = aString.toCharArray();
		Arrays.sort(c);
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i]);
		}
	}
	
    public List<Integer> grayCode(int n) {
    	/*int left = 0, right = 0;
    	List<Integer> outList = new ArrayList<>();
    	outList.add(0);
    	int ans = 0;
        while (right<n) {
        	if(left==n) {
        		right++;
        		left = right;
        		ans = (int) Math.pow(2, left);
        		outList.add(ans);
        		left++;
        	}
			ans = (int) (ans + Math.pow(2, left));
			outList.add(ans);
			left ++;
		}
        return outList;*/
    	/**
        关键是搞清楚格雷编码的生成过程, G(i) = i ^ (i/2);
        如 n = 3: 
        G(0) = 000, 
        G(1) = 1 ^ 0 = 001 ^ 000 = 001
        G(2) = 2 ^ 1 = 010 ^ 001 = 011 
        G(3) = 3 ^ 1 = 011 ^ 001 = 010
        G(4) = 4 ^ 2 = 100 ^ 010 = 110
        G(5) = 5 ^ 2 = 101 ^ 010 = 111
        G(6) = 6 ^ 3 = 110 ^ 011 = 101
        G(7) = 7 ^ 3 = 111 ^ 011 = 100
        **/
    	List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < 1<<n; ++i)
            ret.add(i ^ i>>1);
        return ret;
    }
	
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int y1=0 , y2 = n-1;
        int x1=0 , x2 = n-1;
        int num = 1;
        while (y1<=y2 && x1<=x2) {
        	//行循环
			for (int i = x1; i <= x2; i++) {
				matrix[y1][i]=num;
				num++;
			}
			//扣边界
			if (y1==y2) break;
			y1++;
			//列循环
			for (int i = y1; i <= y2; i++) {
				matrix[i][x2]=num;
				num++;
			}
			if (x1==x2) break;
			x2--;
			//行循环
			for (int i = x2; i >= x1; i--) {
				matrix[y2][i]=num;
				num++;
			}
			if (y1==y2) break;
			y2--;
			for (int i = y2; i >= y1; i--) {
				matrix[i][x1]=num;
				num++;
			}
			if (x1==x2) break;
			x1++;
		}
        return matrix;
    }
	
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> output = new ArrayList<Integer>();
        int y1=0 , y2 = matrix.length - 1;
        int x1=0 , x2 = matrix[0].length - 1;
        while (y1<=y2 && x1<=x2) {
        	//行循环
			for (int i = x1; i <= x2; i++) {
				output.add(matrix[y1][i]);
			}
			//扣边界
			if (y1==y2) break;
			y1++;
			//列循环
			for (int i = y1; i <= y2; i++) {
				output.add(matrix[i][x2]);
			}
			if (x1==x2) break;
			x2--;
			//行循环
			for (int i = x2; i >= x1; i--) {
				output.add(matrix[y2][i]);
			}
			if (y1==y2) break;
			y2--;
			for (int i = y2; i >= y1; i--) {
				output.add(matrix[i][x1]);
			}
			if (x1==x2) break;
			x1++;
		}
        return output;
    }
	
    public List<List<String>> solveNQueens(int n) {
    	char[][] board = new char[n][n];
    	for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = '.';
			}
		}
    	List<List<String>> outlist = new ArrayList<List<String>>();
    }

    private char[][] queen(int n, char[][] board,int rol,int cow) {
		if(n==0) return board;
		for (int i = 0; i < n; i++) {
			if(board[rol][i]=='Q'||board[i][cow]=='Q'||
					(board[rol+i][cow+i]=='Q'&&(rol+i)<n&&(cow+i)<n)||
					(board[rol-i][cow+i]=='Q'&&(rol-i)>=0&&(cow+i)<n)||
					(board[rol-i][cow-i]=='Q'&&(rol-i)>=0&&(cow-i)>=0)||
					(board[rol+i][cow-i]=='Q'&&(rol+i)<n&&(cow-i)>=0)) {
				if(rol<n) {
					rol++;
				}else if(cow<n) {
					cow++;
				}
				i=0;
			}else {
				board[rol][cow]='Q';
				
			}
		}
	}
	
	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs == null || strs.length == 0){
            return null;
        }
        Map<String, List<String>> map = new HashMap<>();
        for(String str:strs){
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            map.computeIfAbsent(new String(arr), x -> new ArrayList<>()).add(str);
        }
        return new ArrayList<List<String>>(map.values());
		/*
    	List<List<String>> outList = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();
        list.add(strs[0]);
        outList.add(list);
        int flag = 0;
        for (int i = 1; i < strs.length; i++) {
			for (int j = 0; j < outList.size(); j++) {
				List<String> list2 = new ArrayList<String>();
				char[] char1 = strs[i].toCharArray();
				list2 = outList.get(j);
				char[] char2 = list2.get(0).toCharArray();
				Arrays.sort(char1);
				String aString = new String(char1);
				Arrays.sort(char2);
				String bString = new String(char2);
				System.out.println(aString+" ");
				System.out.println(bString+" ");
				if(aString.equals(bString)) {
					outList.get(j).add(strs[i]);
					flag = 0;
					break;
				}
				flag = 1;
			}
			if(flag==1) {
				List<String> list1 = new ArrayList<String>();
				list1.add(strs[i]);
				outList.add(list1);
			}
		}
        return outList;*/
    }
	
    public List<List<Integer>> permuteUnique(int[] nums) {
    	Arrays.sort(nums);
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	core2(nums, 0, res);
    	return res;
    }
    private static void core2(int[] nums, int index,List<List<Integer>> res) {
    	for (int i = index; i < nums.length; i++) {
			// 指针已经到倒数第二个数，只需一次交换，可以把存储结果
			if(index == nums.length - 1) {
				List<Integer> list = new ArrayList<Integer>();
				for (int j = 0; j < nums.length; j++) {
					list.add(nums[j]);
				}
				/*if(!res.contains(list)) {
					res.add(list);
				}*/
				res.add(list);
			}
			for (int j = index; j < i; j++) {
				if(nums[i]==nums[j]) continue;
			}
			swap(nums, i, index);
			core2(nums, index+1, res);
			swap(nums, i, index);
		}
    }
	//全排列
    public List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> res = new ArrayList<List<Integer>>();
    	core(nums, 0, res);
    	return res;
    }
    
	private static void core(int[] nums, int index,List<List<Integer>> res) {
		for (int i = index; i < nums.length; i++) {
			// 指针已经到倒数第二个数，只需一次交换，可以把存储结果
			if(index == nums.length - 1) {
				List<Integer> list = new ArrayList<Integer>();
				for (int j = 0; j < nums.length; j++) {
					list.add(nums[j]);
				}
				res.add(list);
			}
			swap(nums, i, index);
			core(nums, index+1, res);
			swap(nums, i, index);
		}
	}
	
	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
