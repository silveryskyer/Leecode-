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
