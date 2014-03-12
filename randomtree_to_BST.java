package anytreetobstree;

import java.util.ArrayList;



public class anytreetobstree {

	
    // Method to reverse a Linkedlist
	public static ll makerevconn(ll head)
	{
		ll curr = head.next , prev = head;
		while(curr != null)
		{
			curr.prev = prev;
			prev = curr;
			curr = curr.next;
		}
		
		return head;
	}
	//Merge subroutine to merge two sorted lists
		public static ll merge(ll a, ll b) {
		    ll dummyHead, curr; dummyHead = new ll(0); curr = dummyHead;
		    while(a !=null && b!= null) {
		        if(a.val <= b.val) { curr.next = a; a = a.next; }
		        else { curr.next = b; b = b.next; }
		        curr = curr.next;
		    }
		    curr.next = (a == null) ? b : a;
		    return dummyHead.next;
		}

		
		//Finding the middle element of the list for splitting
			public static ll getMiddle(ll head) {
			    if(head == null) { return head; }
			    ll slow, fast; slow = fast = head;
			    while(fast.next != null && fast.next.next != null) {
			        slow = slow.next; fast = fast.next.next;
			    }
			    return slow;
			}
			
		
		//Merge sort two Linked Lists
		public static ll merge_sort(ll head) {
		    if(head == null || head.next == null) { return head; }
		    ll middle = getMiddle(head);      //get the middle of the list
		    ll sHalf = middle.next; middle.next = null;   //split the list into two halfs

		    return merge(merge_sort(head),merge_sort(sHalf));  //recurse on that
		}
		
		
        //append to the double linked list
		public static void append(ll node1, ll node2)
		{
			node1.next = node2;
			node2.prev = node1;
		}
		
		
		static class llwrapper
		{
			ll head;
			
		llwrapper(ll ref)
		{
			this.head = ref;
		}
		
		public ll gethead()
		{
			return head;
		}
		
		}
		
       //convert a double linkedlist to a tree
		public static treenode lltotree(ll head)
		{
		
			int n = countnodes(head);
			llwrapper param = new llwrapper(head);
			treenode root = ll_treenode(param , n);
			return root;
		}
		
    // count nodes in a linked list
		public static int countnodes(ll head)
		{
			int count = 0;
			ll temp = head;
			while(temp!= null)
			{
				count++;
				temp = temp.next;
			}
			return count;
		}
		
     // convert a tree to double linked list
		public static treenode ll_treenode(llwrapper wrap , int n)

    
    {
        //alternative implementation
        
/*
 struct node *ConvertTreefromDll(struct node *head)
{
	If (head==NULL || head->next == NULL) return head;
	struct node *p,*q, *temp;
	
	temp=FindMiddle(head);
	
	p=head;
	while(p->next!=temp) p=p->next;
	p->next=NULL;
	
	q=temp->next;
	temp->next=NULL;
	temp->prev=ConvertTreefromDll(head);
	temp->next=ConvertTreefromDll(q);
	
	return temp;
}
 
	
struct node *ConvertTreefromDll(struct node *head) 
{     
If (head==NULL || head->next == NULL) 
    return head;     
struct node *q, *temp;       
temp=FindMiddle(head);   
    
temp->prev->next=NULL;


q=temp->next;     
temp->next=NULL;     
temp->prev=ConvertTreefromDll(head);     
temp->next=ConvertTreefromDll(q);       
return temp; 
} 
 */
 
			
			
			if(n <=0)
				return null;
			
			treenode left = ll_treenode(wrap , n/2);
			treenode curr = new treenode(wrap.gethead().val);
			curr.l = left;
			wrap.head = wrap.head.next;
			treenode right = ll_treenode(wrap , n - n/2 - 1);
			curr.r = right;
			return curr;
			
			
			
			
		}
    
    //convert tree to double linked list
		public static ll tree_ll(treenode root)
		{
			
			ll head = treetoll(root);
			head.prev.next = null;
			head.prev = null;
			return head;
			
		}
		
    //convert tree to double linked list
		public static ll treetoll(treenode root)
		{
			
			if(root == null)
				return null;
			
			ll prev = treetoll(root.l);
			ll next = treetoll(root.r);
			if(prev == null && next == null)
			{
				ll head = new ll(root.data);
				head.prev = head;
				head.next = head;
				return head;
			}
			
			ll curr = new ll(root.data);
			
			ll tailend = next == null? null: next.prev;
			
			if(prev == null)
			{
				
				append(next.prev,curr);
			}
			else
			{
				append(prev.prev , curr);
			}
			
			if(next == null)
			{
				append(curr, prev);
			}
			else
			{
				append(curr,next);
			}
			
			if(prev!= null && next!=null)
			{
				append(tailend,prev);
			}
			
			return prev==null?curr:prev;
		}
    
    // level order traversal of a tree
		
		public static void getlevels(ArrayList<ArrayList<treenode>> lists , int index,treenode root)
		{
			if(root == null)
				return;
			
			ArrayList<treenode> list;
			if(lists.size() == index)
			{
				list = new ArrayList<treenode>();
				lists.add(index,list);
			}
			else
			{
				list = lists.get(index);
			}
		
			list.add(root);
			getlevels(lists,index+1,root.l);
			getlevels(lists,index+1,root.r);
		}
		
		public static ArrayList<ArrayList<treenode>> levelorder(treenode root)
		{
			ArrayList<ArrayList<treenode>> lists = new ArrayList<ArrayList<treenode>>();
			getlevels(lists,0,root);
			return lists;
		}
		public static void inorderprint(treenode root)
		{
			if(root == null)
				return ;
			
			inorderprint(root.l);
			if(root.data == 99)
				System.out.println("Iam 99 and my l child is" + root.l.data);
			System.out.println(root.data);
	
			
			inorderprint(root.r);
		}
		public static void main(String args[])
		{
            //commented test cases
		/*
	    ll temp = new ll(55) , head = temp;
		for(int i = 12 ; i >=0 ; i--)
		{
		 temp.next = new ll(i);
		 temp = temp.next;
		}
	     
	     
	     head = merge_sort(head);
	     head = makerevconn(head);
	     while(head.next!= null)
			{
				
				head = head.next;
			}
			
	     while(head!= null)
			{
				System.out.println(head.val);
				head = head.prev;
			}
		}
		*/
			
			treenode root = new treenode(5);
			root.l = new treenode(1);
			root.r = new treenode(-4);
			root.l.l = new treenode(99);
			root.l.r = new treenode(4);
			
			ll head = tree_ll(root);
			head = merge_sort(head);
			root = lltotree(head);
			
			inorderprint(root);
			 ArrayList<ArrayList<treenode>> inorder = levelorder(root);
			 
			 for(ArrayList<treenode> node : inorder)
			 {
				 for(treenode r : node)
					 System.out.print(r.data + " ");
				 
				 System.out.println();
				 
				 
			 }
			 
}
}
