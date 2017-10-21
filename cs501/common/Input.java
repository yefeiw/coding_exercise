
import java.util.*;


/******* class definitions that are used in the exercies*******/
class ListNode {
	int val;
	ListNode next;
	ListNode(int val) {
		this.val = val;
	}

}
class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}
class TreeLinkNode {
      int val;
      TreeLinkNode left, right, next;
      TreeLinkNode(int x) { val = x; }
 }
class TreeNode {
      int val;
      TreeNode left, right;
      TreeNode(int x) { val = x; }
 }
 class Pair {
 	public int x;
 	public int y;
 	public Pair(int x, int y) { 
 		this.x = x;
 		this.y = y;
 	}
 	public boolean equals(Pair p) {
 		return (this.x == p.x) && (this.y == p.y);
 	}

 }
/////////////////////////////Utility Functions/////////////////////////  

//package utils;
public class Input {
	//static variables;
	Random rand;
	public Input() {
		rand = new Random();
	}
	//member functions to generate input.
	public ArrayList<Integer> generateRandomArrayList() {
		int maxSize = 10000;
		int size = rand.nextInt(maxSize+1);
		ArrayList<Integer> ret = new ArrayList<Integer>();//returning arraylist of integer.
		for (int i = 0; i < size; i++) {
			ret.add(rand.nextInt());
		}
		return ret;
	}
	//member functions to generate input.
	public int[] generateRandomArray() {
		int maxSize = 100;
		int limit = 100;
		int size = rand.nextInt(maxSize+1);
		int[] ret = new int[size];//returning array of integer.
		for (int i = 0; i < size; i++) {
			ret[i] = -limit + 2* rand.nextInt(limit);
		}
		return ret;
	}
	//member functions to generate input.
	public int[] generateRandomArray(int limit) {
		int maxSize = limit;
		int size = rand.nextInt(maxSize+1);
		int[] ret = new int[size];//returning array of integer.
		for (int i = 0; i < size; i++) {
			ret[i] = -limit + 2* rand.nextInt(limit);
			ret[i] = Math.abs(ret[i]);
		}
		return ret;
	}
	//member functions to generate input.
	public int[] generateRandomArray(int min,int max) {
		int maxSize = max - min + 1;
		int size = rand.nextInt(maxSize+1);
		int[] ret = new int[size];//returning array of integer.
		for (int i = 0; i < size; i++) {
			ret[i] = min + rand.nextInt(max);
		}
		return ret;
	}
	public void printArray(int[] input) {
		for(int i = 0; i < input.length; i++) {
			if ( i > 0 ) System.out.print(",");
			System.out.print(input[i]);
		}
		System.out.println();
	}
	public void printList(List<Integer> input) {
		for(int i = 0; i < input.size(); i++) {
			if ( i > 0 ) System.out.print(",");
			System.out.print(input.get(i));
		}
		System.out.println();
	}
	public int generateRandomInt() {
		return rand.nextInt();
	}
	public int generateRandomInt(int limit) {
		return rand.nextInt(limit);
	}
	//member funcitons to generate input
	public ListNode generateRandomListNode() {
		//int maxSize = 10000;
		//small test set for manual verification
		int maxSize = 10;
		int size = rand.nextInt(maxSize + 1);
		ListNode head = new ListNode(rand.nextInt(maxSize+1));
		ListNode iter = head;
		for (int i = 0; i < size; i++) {
			iter.next = new ListNode (rand.nextInt(maxSize + 1));
			iter = iter.next;
		}
		return head;
	}
	//member funcitons to generate input
	public Interval[] generateRandomIntervals() {
		int limit = 10000;
		int size = rand.nextInt(limit)+1;
		Interval[] ret = new Interval[size];
		System.out.println("created interval of size" + size);
		for ( int i = 0; i < size; i++) {
			int start = rand.nextInt(limit+1);
			ret[i] = new Interval(start, start + rand.nextInt(limit+1));
		}
		return ret;

	}
	static public void printListNode(ListNode head) {
		ListNode iter = head;
		while (null != iter) {
			if(iter != head) System.out.print("->");
			System.out.print(iter.val);
			iter = iter.next;
		}
		System.out.println();

	}
	public String generateRandomString() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	public String generateRandomString(int limit) {
		StringBuilder sb = new StringBuilder();
		while (sb.length() < limit) {
			char c = (char)rand.nextInt(128);
			if (Character.isLetterOrDigit(c)) {
				sb.append(c);
			}  
		}
		return sb.toString();
	}
	public String generateRandomString(char min, char max) {
		StringBuilder sb = new StringBuilder();
		int limit = rand.nextInt(max - min + 1);
		while (sb.length() < limit) {
			char c = (char)(min + rand.nextInt(max-min+1));
			sb.append(c);
		}
		return sb.toString();
	}
	public String[] generateRandomStringArray() {
		int limit = 10000;
		String operators = "+-*/";
		String[] ret =  new String[limit];
		for (int i = 0; i < limit; i++) {
			int seed = -limit + 2*rand.nextInt(limit);
			if (seed >= 0 && seed < 4) {
				ret[i] = operators.substring(seed,seed);
			} else {
				ret[i] = Integer.toString(seed);
			}
		}
		return ret;
	}
	public char [][] generateBinaryMatrix(int limit) {
		char [][]ret = new char[rand.nextInt(limit)][rand.nextInt(limit)];
		for (int i = 0; i < ret.length; i++) {
			for (int j = 0; j < ret[0].length; j++) {
				ret[i][j] = (char)(rand.nextInt() % 2);
			}
		}
		return ret;
	}
	public char [][] generateCharMatrix(int limit) {
		char [][]ret = new char[rand.nextInt(limit)][rand.nextInt(limit)];
		for (int i = 0; i < ret.length; i++) {
			for (int j = 0; j < ret[0].length; j++) {
					int seed = (rand.nextInt() % 2);
				ret[i][j] = (seed == 0) ?'0':'1';
			}
		}
		return ret;
	}
	
	public int [][] generateRandomMatrix(int limit) {
		int [][]ret = new int[rand.nextInt(limit)+4][rand.nextInt(limit)+2];
		for (int i = 0; i < ret.length; i++) {
			for (int j = 0; j < ret[0].length; j++) {
				ret[i][j] = (rand.nextInt() % limit);
			}
		}
		return ret;
	}
	public int [][] generateRandomMatrix(int limit, int mod) {
		int [][]ret = new int[rand.nextInt(limit)][rand.nextInt(limit)];
		for (int i = 0; i < ret.length; i++) {
			for (int j = 0; j < ret[0].length; j++) {
				ret[i][j] = Math.abs(rand.nextInt() % mod);
			}
		}
		return ret;
	}
	public void printMatrix(char[][] input) {
		System.out.println("##########################");
		for (int i = 0;i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				System.out.printf("%d ",(int)input[i][j]);
			}
			System.out.println();
		}
		System.out.println("##########################");
	}
	public void printMatrix(int[][] input) {
		System.out.println("##########################");
		for (int i = 0;i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				System.out.printf("%d ",input[i][j]);
			}
			System.out.println();
		}
		System.out.println("##########################");
	}
	public List<List<Integer>> generateRandom2DList(int limit) {
		int size = rand.nextInt(limit);
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		for(int  i = 0; i < size; i++) {
			List<Integer> item  = new ArrayList<Integer>();
			int jsize = rand.nextInt(limit);
			for(int j = 0; j < jsize; j++) {
				item.add(rand.nextInt(limit));
			}
			ret.add(item);
		}
		return ret;
	}
	///////////////////  Tree-Related Code borrowed from HW
	public TreeNode str2tree(String s) {
        Stack<TreeNode> stack = new Stack<>();
        for(int i = 0, j = i; i < s.length(); i++, j = i){
            char c = s.charAt(i);
            if(c == ')')    stack.pop();
            else if(c >= '0' && c <= '9' || c == '-'){
                while(i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') i++;
                TreeNode currentNode = new TreeNode(Integer.valueOf(s.substring(j, i + 1)));
                if(!stack.isEmpty()){
                    TreeNode parent = stack.peek();
                    if(parent.left != null)    parent.right = currentNode;
                    else parent.left = currentNode;
                }
                stack.push(currentNode);
            }
        }
        return stack.isEmpty() ? null : stack.peek();
    }
}