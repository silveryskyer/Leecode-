package leetcodetest;

import java.util.HashSet;

import com.sun.tools.classfile.Annotation.element_value;
import com.sun.tools.classfile.Opcode.Set;

public class ListNodeTest {
	/*
	 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
	 * 示例：
	 * 输入：1->2->4, 1->3->4
	 * 输出：1->1->2->3->4->4
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * 链表排序
	 * 对一个链表进行排序，且时间复杂度要求为 O(n log n) ，空间复杂度为常量。一看到 O(n log n) 的排序，首先应该想到归并排序
	 * 和快速排序，但是通常我们使用这两种排序方法时都是针对数组的，现在是链表了。
	 * 归并排序法：在动手之前一直觉得空间复杂度为常量不太可能，因为原来使用归并时，都是 O(N)的，需要复制出相等的空间来进行赋值归并。
	 * 对于链表，实际上是可以实现常数空间占用的（链表的归并排序不需要额外的空间）。利用归并的思想，递归地将当前链表分为两段，然后
	 * merge，分两段的方法是使用 fast-slow 法，用两个指针，一个每次走两步，一个走一步，知道快的走到了末尾，然后慢的所在位置
	 * 就是中间位置，这样就分成了两段。merge时，把两段头部节点值比较，用一个 p 指向较小的，且记录第一个节点，然后 两段的头一步一步
	 * 向后走，p也一直向后走，总是指向较小节点，直至其中一个头为NULL，处理剩下的元素。最后返回记录的头即可。
	 * 主要考察3个知识点，
	 * 知识点1：归并排序的整体思想
	 * 知识点2：找到一个链表的中间节点的方法
	 * 知识点3：合并两个已排好序的链表为一个新的有序链表
	 * 归并排序的基本思想是：找到链表的middle节点，然后递归对前半部分和后半部分分别进行归并排序，最后对两个以排好序的链表进行Merge。
	 */
    public ListNode sortList(ListNode head) {
    	return head == null ? null : mergeSort(head);
    }
    
    private ListNode mergeSort(ListNode head) {
		if(head.next == null) {
			return head;
		}
		ListNode p = head, q = head, pre = null;
		while (q!=null && q.next!=null) {
			pre = p;
			p = p.next;
			q = q.next.next;
		}
		pre.next = null;
		ListNode l = mergeSort(head);
		ListNode r = mergeSort(p);
		return merge(l, r);
	}
    
    ListNode merge(ListNode l,ListNode r) {
    	ListNode dummyHead = new ListNode(0);
    	ListNode cur = dummyHead;
    	while (l != null && r != null) {
			if(l.val <= r.val) {
				cur.next = l;
				cur = cur.next;
				l = l.next;
			}else {
				cur.next = r;
				cur = cur.next;
				r = r.next;
			}
		}
    	if(l != null) {
    		cur.next = l;
    	}
    	if(r != null) {
    		cur.next = r;
    	}
    	return dummyHead.next;
    }
	
    public ListNode detectCycle(ListNode head) {
    	java.util.Set<ListNode> node = new HashSet<>();
        while (head!=null) {
			if(node.contains(head))
				return head;
			else {
				node.add(head);
			}
			head = head.next;
		}
        return null;
    }
	
    public boolean hasCycle(ListNode head) {
        /*java.util.Set<ListNode> node = new HashSet<>();
        while (head!=null) {
			if(node.contains(head))
				return true;
			else {
				node.add(head);
			}
			head = head.next;
		}
        return false;*/
    	//快慢指针
    	ListNode slow = head, fast = head;
    	while (fast!=null && fast.next!=null) {
			slow = slow.next;
			fast = fast.next.next;
			if(fast==slow) return true;
		}
    	return false;
    }
	
    public ListNode rotateRight(ListNode head, int k) {
    	if(head==null||k==0) {
    		return head;
    	}
    	ListNode node =head;
    	int len = 0;
    	while (node.next!=null) {
			len++;
			node = node.next;
		}
    	len++;
    	node.next = head;
    	k = len - k % len;
    	for (int i = 0; i < k-1; i++) {
			head = head.next;
		}
    	node = head.next;
    	head.next = null;
    	return node;
    }
	
    public ListNode reverseKGroup(ListNode head, int k) {
    	ListNode prev = null, cur = head, next = null, check= head;
    	int canProceed = 0;
    	int count = 0;
    	//检查链表长度是否满足翻转
    	while (canProceed < k && check != null) {
			check = check.next;
			canProceed++;
		}
    	//满足条件，进行翻转
    	if(canProceed==k) {
    		while (count < k && cur!=null) {
				next = cur.next;
				cur.next = prev;
				prev = cur;
				cur = next;
				count++;
			}
    		if(next != null) {
    			head.next = reverseKGroup(next, k);
    		}
    		return prev;
    	}else {
    		return head;
		} 
    }
    
    public int getLength(ListNode head){        
    	int length = 0;        
    	while(head!=null){            
    		length++;            
    		head= head.next;        
    		}        
    	return length;            
    }
    
	
    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null) {
        	return head;
        }
        /*ListNode pListNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return pListNode;*/
        ListNode pre = null;
        while (head!=null) {
        	ListNode new_head = new ListNode(head.val); 
			new_head.next = pre;
			pre = new_head;
			head = head.next;
		}
        return pre;
    }
    
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null) {
        	return head;
        }
        head.next = deleteDuplicates(head.next);
        if(head.val == head.next.val) {
        	head = head.next;
        }
        return head;
    }
	
    public ListNode swapPairs(ListNode head) {
    	if(head==null||head.next==null) {
    		return head;
    	}
    	ListNode next = head.next;
    	head.next = swapPairs(next.next);
    	next.next = head;
        return next;
    }
	
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode p = new ListNode(0);
    	ListNode cur = p; 
    	while (l1!=null&&l2!=null) {
			if(l1.val>l2.val) {
				cur.next = l2;
				cur = cur.next;
				l2 = l2.next;
			}else {
				cur.next = l1;
				cur = cur.next;
				l1 = l1.next;
			}
    	}
    	if(l1==null) {
    		cur.next = l2;
    	}else {
			cur.next = l1;
		}
    	return p.next;
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
    	if(lists.length==0) {
    		return null;
    	}
    	else {
    		ListNode listLeft = lists[0];
            for (int i = 1; i < lists.length; i++) {
    			listLeft = mergeTwoLists(listLeft, lists[i]);
    		}
            return listLeft;
		}
    }
    
    public class ListNode {
        int val;
         ListNode next;
         ListNode(int x) { val = x; }
    }
}
