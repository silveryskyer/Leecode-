package leetcodetest;

import java.io.Console;

import java.util.Arrays;


public class BinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
    public TreeNode constructMaximumBinaryTree(int[] nums) {
    	if(nums.length==0) {
    		return null;
    	}
        TreeNode root = new TreeNode(0);
        int max = maxIndex(nums);
        root.val = nums[max];
        if(max == 0) {
        	root.right = constructMaximumBinaryTree(Arrays.copyOfRange(nums, max+1, nums.length));
        	return root;
        }
        if(max==nums.length-1) {
        	root.left = constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, max-1));
        	return root;
        }
        root.left = constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, max-1));
        root.right = constructMaximumBinaryTree(Arrays.copyOfRange(nums, max+1, nums.length-1));
        return root;
    }
    
    public int maxIndex(int[] nums) {
    	int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if(nums[i]>nums[max]) {
				max = i;
			}
		}
		return max;
	}
	
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null&&t2==null) {
        	return null;
        }
        TreeNode root = new TreeNode(0);
        if(t1==null) {
        	return t2;
        }else if(t2==null) {
			return t1;
		}else {
			root.val = t1.val + t2.val;
			root.left = mergeTrees(t1.left, t2.left);
	        root.right = mergeTrees(t1.right, t2.right);
		}
        return root;
    }
	
    public TreeNode invertTree(TreeNode root) {
        if(root==null) {
        	return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode righ = invertTree(root.right);
        root.right = left;
        root.left = righ;
        return root;
    }
    
    public int minDepth(TreeNode root) {
        if(root==null) {
        	return 0;
        }
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        if(leftDepth==0||rightDepth==0) {
        	return Math.max(leftDepth, rightDepth)+1;
        }
        return Math.min(leftDepth, rightDepth)+1;
    }
	
    public static boolean isSymmetric(TreeNode root) {
    	if(root==null) {
    		return true;
    	}
    	return isSame(root.left, root.right);
    }
    
    public static boolean isSame(TreeNode left, TreeNode right) {
    	if(left==null&&right==null) {
    		return true;
    	}
    	if((left==null&&right!=null)||(left!=null&&right==null)) {
    		return false;
    	}
    	if(left.val == right.val) {
    		return isSame(left.left, right.right)&&isSame(left.right, right.left);
    	}
		return false;
	}
	
    public int maxDepth(TreeNode root) {
    	//��ֹ����������Ϊ��ʱ�����ݹ飬�����ص�ǰ���0
    	if(root==null) {
    		return 0;
    	}
    	//root������������������
    	int leftDepth = maxDepth(root.left);
    	int rightDepth = maxDepth(root.right);
    	//���ص�������������������+1
    	return Math.max(leftDepth, rightDepth) + 1;
    }
    
    public boolean isBalanced(TreeNode root) {
        return isBST(root).isB;
    }
    
  //�ο��ݹ���·�ĵ���������������ִ�й�����ʲô����
    //����ĵ���ִ�й��̾������£�
    //�Ƿ���ֹ?->û��ֹ�Ļ����ж��Ƿ����㲻ƽ�����������->����ֵ
    public ReturnNode isBST(TreeNode root){
    	if(root == null){
            return new ReturnNode(0, true);
        }
    	//��ƽ��������3�֣�������ƽ�⡢������ƽ�⡢������������ľ���ֵ����1
    	ReturnNode left = isBST(root.left);
    	ReturnNode right = isBST(root.right);
    	if(left.isB==false || right.isB==false) {
    		return new ReturnNode(0, false);
    	}
    	if(Math.abs(left.depth-right.depth)>1) {
    		return new ReturnNode(0, false);
    	}
    	return new ReturnNode(Math.max(left.depth, right.depth)+1, true);
    }
    
  //���ReturnNode�ǲο��������ĵݹ���·�ĵڶ�����˼������ֵ��ʲô
    //һ������BST�ȼ���������������������BST���������߶Ȳ����1
    //�������Ϊ����ֵӦ�ð�����ǰ���Ƿ���BST�͵�ǰ���ĸ߶���������Ϣ
    private class ReturnNode{
        boolean isB;
        int depth;
        public ReturnNode(int depth, boolean isB){
            this.isB = isB;
            this.depth = depth;
        }
    }
    
    public class TreeNode {
	     int val;
    	 TreeNode left;
    	 TreeNode right;
    	 TreeNode(int x) { val = x; }
    }

}
