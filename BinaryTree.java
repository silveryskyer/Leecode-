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
    	//终止条件：当树为空时结束递归，并返回当前深度0
    	if(root==null) {
    		return 0;
    	}
    	//root的左、右子树的最大深度
    	int leftDepth = maxDepth(root.left);
    	int rightDepth = maxDepth(root.right);
    	//返回的是左右子树的最大深度+1
    	return Math.max(leftDepth, rightDepth) + 1;
    }
    
    public boolean isBalanced(TreeNode root) {
        return isBST(root).isB;
    }
    
  //参考递归套路的第三部：描述单次执行过程是什么样的
    //这里的单次执行过程具体如下：
    //是否终止?->没终止的话，判断是否满足不平衡的三个条件->返回值
    public ReturnNode isBST(TreeNode root){
    	if(root == null){
            return new ReturnNode(0, true);
        }
    	//不平衡的情况有3种：左树不平衡、右树不平衡、左树和右树差的绝对值大于1
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
    
  //这个ReturnNode是参考我描述的递归套路的第二步：思考返回值是什么
    //一棵树是BST等价于它的左、右俩子树都是BST且俩子树高度差不超过1
    //因此我认为返回值应该包含当前树是否是BST和当前树的高度这两个信息
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
