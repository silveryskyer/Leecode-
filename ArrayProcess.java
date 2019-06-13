package leetcodetest;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;


public class ArrayProcess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums = {1,1,2};
		System.out.print(removeDuplicates(nums));
	}
	
    public int singleNumber(int[] nums) {
    	if(nums.length==1){
    		return nums[0];
    	}
    	Arrays.sort(nums);
    	for (int i = 0; i < nums.length-1; i=i+2) {
			if(nums[i]!=nums[i+1]) {
				return nums[i];
			}
		}
    	return nums[nums.length-1];
    }
	
    public int maxProfit(int[] prices) {
    	if(prices.length <= 1)
            return 0;
    	int pro = 0;
    	for (int i = 1; i < prices.length; i++) {
			if(prices[i]-prices[i-1]>0) {
				pro = pro + prices[i]-prices[i-1];
			}
		}
    	return pro;
        /*int buy = 0, sale = 1, pro = 0, ans = 0;
        while (buy<prices.length-1) {
        	if(sale==prices.length) {
        		buy++;
        		if(buy==prices.length-1) {
        			return pro;
        		}
        		sale = buy+1;
        	}
			ans = prices[sale] - prices[buy];
			if(ans>pro) {
				pro = ans;
			}
			sale++;
		}
        return pro;*/
    	//动态规划
    	/*if(prices.length <= 1)
            return 0;
        int min = prices[0], max = 0;
        for (int i = 1; i < prices.length; i++) {
			max = Math.max(max, prices[i]-min);
			min = Math.min(min, prices[i]);
		}
        return max;*/
    }
	
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        /*for (int i = 0; i < n; i++) {
			nums1[m+i] = nums2[i];
		}
        Arrays.sort(nums1);*/
    	if(n<1) {
    		return;
    	}
    	for (int i = m + n - 1; (m > 0 && n > 0); i--) {
			if(nums1[m-1] > nums2[n-1]) {
				nums1[i] = nums1[m-1];
				m--;
			} else {
				nums1[i] = nums2[n-1];
				n--;
			}
		}
    	if(m == 0) {
    		for (int i = 0; i < n; i++) {
				nums1[i] = nums2[i];
			}
    	}
    }
	
	//以第n个数为结束点的子数列的最大和，存在一个递推关系f(n) = max(f(n-1) + A[n], A[n]);
    public int maxSubArray(int[] nums) {
    	if(nums.length==0) {
    		return 0;
    	}
    	int res = Integer.MIN_VALUE;
    	int f_n = -1;
    	for (int i = 0; i < nums.length; i++) {
			f_n = Math.max(nums[i], f_n + nums[i]);
			res = Math.max(f_n, res);		}
    	return res;
    }
    
    private int sum(int[] subnum) {
    	int sumNum = 0;
    	System.out.print("[");
		for (int i = 0; i < subnum.length; i++) {
			System.out.print(subnum[i]);
			sumNum += subnum[i];
		}
		System.out.print("]");
		return sumNum;
	}
	
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len/2; i++) {
			int start = i;
			int end = len - i - 1;
			for (int j = 0; j < end-start; j++) {
				int temp = matrix[start][start+j];
				matrix[start][start+j] = matrix[end-j][start];
				matrix[end-j][start] = matrix[end][end-j];
				matrix[end][end-j] = matrix[start][end];
				matrix[start+j][end] = temp;
			}
		}
    }
	
    public int jump(int[] nums) {
    	int count = 0;
    	if(nums.length<=1) {
    		return 0;
    	}
        for (int i = 0; i < nums.length; ) {
        	if(nums[i]>=nums.length-i) {
        		return count+1;
        	}
        	int next = 0;
			for (int j = i+1; j <= i+nums[i]; j++) {
				int jumpto = 0;
				if(jumpto<=j+nums[j]) {
					jumpto = j+nums[j];
					next = j;
				}
			}
			System.out.print(next);
			i = next;
			count ++;
		}
        return count;
    }
	
    public int trap(int[] height) {
    	Stack<Integer> stack = new Stack<>();
    	int area = 0;
    	for (int i = 0; i < height.length; i++) {
			if(stack.isEmpty()||height[i]<=height[stack.peek()]) {
				stack.push(i);
			}else {
				int count = stack.pop();
				if(!stack.empty()){
					int h = Math.min(height[stack.peek()], height[i])-height[count];
					h = h>0 ? h :0;
					area += h* (i-stack.peek()-1);
				}
				i--;
			}
		}
    	return area;
    }
	
    public int firstMissingPositive(int[] nums) {
    	if(nums.length==0) {
    		return 1;
    	}
        Arrays.sort(nums);
        if(nums[0]>1|nums[nums.length-1]<=0) {
        	return 1;
        }
        for (int i = 0; i < nums.length-1; i++) {
        	if(nums[i]<=0&&nums[i+1]>1) {
        		return 1;
        	}
			if(nums[i]>=0&&nums[i+1]-nums[i]>1) {
				return nums[i]+1;
			}
		}
        return nums[nums.length-1]+1;
    }
	
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    	Arrays.sort(candidates);
    	if(candidates==null||candidates.length==0) {
    		return null;
    	}
    	List<List<Integer>> record = new ArrayList<>();
    	List<Integer> cur = new ArrayList<>();
    	int[] used = new int[candidates.length];
    	find2(used,candidates, target, 0, cur, record);
    	return record;
    }
    
    private void find2(int[] used,int[] candidates,int target,int depth,List<Integer> cur, List<List<Integer>> record) {
    	if(target==0) {
			List<Integer> temp =  new ArrayList<>();
			for (Integer i:cur) {
				temp.add(i);
			}
			if(!record.contains(temp)) {
				record.add(temp);
			}
		}
		for (int i = depth; i < candidates.length; i++) {
			if(target>=candidates[i]&&used[i]!=1) {
				cur.add(candidates[i]);
				used[i]=1;
				find2(used,candidates, target-candidates[i], i, cur, record);
				used[i]=0;
				cur.remove(cur.size()-1);
			}
		}
    }
	
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	if(candidates==null||candidates.length==0) {
    		return null;
    	}
    	List<List<Integer>> record = new ArrayList<>();
    	List<Integer> cur = new ArrayList<>();
    	find(candidates, target, 0, cur, record);
    	return record;
    }
    
    private void find(int[] candidates,int target,int depth,List<Integer> cur, List<List<Integer>> record) {
		if(target==0) {
			List<Integer> temp =  new ArrayList<>();
			for (Integer i:cur) {
				temp.add(i);
			}
			record.add(temp);
		}
		for (int i = depth; i < candidates.length; i++) {
			if(target>=candidates[i]) {
				cur.add(candidates[i]);
				find(candidates, target-candidates[i], i, cur, record);
				cur.remove(cur.size()-1);
			}
		}
	}
	
    public int searchInsert(int[] nums, int target) {
    	int i = 0, j = nums.length - 1;
        if(target<nums[i]||nums.length==0) {
        	return 0;
        }
        if(target>nums[j]) {
        	return nums.length;
        }
        
        while (i<j) {
			int mid = i + (j-i)/2;
			if(nums[mid]<target) {
				i = mid + 1;
			}else {
				j = mid;
			}
		}
        return i;
    }
	
    public int[] searchRange(int[] nums, int target) {
    	int[] res = {-1, -1};
        if(Arrays.binarySearch(nums, target)<0) {
        	return res;
        }else {
			res[0] = binarySearchL(nums, target);
			res[1] = binarySearchR(nums, target);
		}
        return res;
    }
    
    public int binarySearchL(int[] nums, int target) {
		int i = 0, j = nums.length - 1;
		while (i<j) {
			int mid = i + (j-i)/2;
			if(nums[mid]<target) {
				i = mid+1;
			}else {
				j = mid;
			}
		}
		return i;
	}
    
    public int binarySearchR(int[] nums, int target) {
    	int i = 0, j = nums.length - 1;
    	while (i<j) {
			int mid = i + (int)Math.ceil((j-i)/2.0);
			if(target<nums[mid]) {
				j = mid - 1;
			}else {
				i = mid;
			}
		}
    	return i;
	}
	
    public int search(int[] nums, int target) {
        if(nums.length==0||(nums[0]<nums[nums.length-1]&&target<nums[0])||(nums[0]<nums[nums.length-1]&&target>nums[nums.length-1])) {
        	return -1;
        }
        if(nums[0]>nums[nums.length-1]&&target<nums[nums.length-1]) {
        	for (int i = nums.length-1; i >=0; i--) {
				if(nums[i]==target) {
					return i;
				}else if (i!=0&&nums[i]<nums[i-1]) {
					return -1;
				}
			}
        }
        else {
			for (int i = 0; i < nums.length; i++) {
				if(nums[i]==target) {
					return i;
				}else if(i!=nums.length-1&&nums[i]>nums[i+1]) {
					return -1;
				}
			}
		}
        return -1;
    }
	
    public void nextPermutation(int[] nums) {
    	if(nums.length == 0) {
    		return;
    	}
    	for (int i = nums.length-1; i >0 ; i--) {
			if(nums[i]>nums[i-1]) {
				for (int j = nums.length-1; j >= i; j--) {
					if(nums[j]>nums[i-1]) {
						int temp = nums[j];
						nums[j] = nums[i-1];
						nums[i-1] = temp;
						break;
					}
				}
				if(i<nums.length-1) {
					for (int k = i; k < (nums.length-i)/2+1; k++) {
						int temp = nums[k];
						nums[k] = nums[nums.length-k%i-1];
						nums[nums.length-k%i-1] = temp;
					}
				}
				break;
			}
			else if(i==1) {
				for (int k = i; k < (nums.length-i)/2+1; k++) {
					int temp = nums[k];
					nums[k] = nums[nums.length-k-1];
					nums[nums.length-k-1] = temp;
				}
			}
		}
    }
	
    public static int removeDuplicates(int[] nums) {
    	if(nums.length==0) {
    		return 0;
    	}
        /*int out = 1;
        int[] newNum = new int[nums.length]; 
        newNum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
			if(nums[i]!=nums[i-1]) {
				newNum[out] = nums[i]; 
				System.out.print(newNum[out]);
				out++;
			}
		}
        for (int i = 0; i < newNum.length; i++) {
			nums[i] = newNum[i];
		}
    
        return out;*/
    	int a = Integer.MIN_VALUE;
    	int b = 0;
    	for (int i = 0; i < nums.length; i++) {
			if(a!=nums[i]) {
				a = nums[i];
				nums[b] = nums[i];
				b++;
			}
		}
    	return b;
    }
    
    public int removeElement(int[] nums, int val) {
        int out = 0;
        for (int i = 0; i < nums.length; i++) {
			if(val!=nums[i]) {
				nums[out] = nums[i];
				out++;
			}
		}
        return out;
    }
    

}
