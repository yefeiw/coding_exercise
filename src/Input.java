
import java.util.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }

}

//package utils;
public class Input {
	//member functions to generate input.
	public ArrayList<Integer> generateRandomArrayList() {
		Random rand = new Random();
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
		Random rand = new Random();
		int maxSize = 100;
		int limit = 100;
		int size = rand.nextInt(maxSize+1);
		int[] ret = new int[size];//returning array of integer.
		for (int i = 0; i < size; i++) {
			ret[i] = rand.nextInt(limit);
		}
		return ret;
	}
	public int generateRandomInt() {
		int limit = 100;
		Random rand = new Random(100);
		return rand.nextInt();
	}
	//member funcitons to generate input
	public ListNode generateRandomListNode() {
		Random rand = new Random();
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
	public void printListNode(ListNode head) {
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
}