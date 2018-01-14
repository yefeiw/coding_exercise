/*
 * [146] LRU Cache
 *
 * https://leetcode.com/problems/lru-cache/description/
 *
 * algorithms
 * Hard (19.08%)
 * Total Accepted:    156K
 * Total Submissions: 816.6K
 * Testcase Example:  '["LRUCache","put","put","get","put","get","put","get","get","get"]\n[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]'
 *
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 *
 *
 *
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently
 * used item before inserting a new item.
 *
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache( 2 /* capacity */ );
 *
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 *
 *
 */
class LRUCache {
    class Node {
    	int key;
    	int value;
    	Node pre;
    	Node post;
    }

    private void addNode(Node node){
    	node.pre = head;
    	node.post = head.post;

    	head.post.pre = node;
    	head.post = node;
    }

    private void removeNode(Node node){
    	Node pre = node.pre;
    	Node post = node.post;

    	pre.post = post;
    	post.pre = pre;
    }

    private void moveToHead(Node node){
    	this.removeNode(node);
    	this.addNode(node);
    }

    private Node popTail(){
    	Node res = tail.pre;
    	this.removeNode(res);
    	return res;
    }

    private Hashtable<Integer, Node>
    	cache = new Hashtable<Integer, Node>();
    private int count;
    private int capacity;
    //psuedo nodes
    private Node head, tail;

    public LRUCache(int capacity) {
    	this.count = 0;
    	this.capacity = capacity;

    	head = new Node();
    	head.pre = null;

    	tail = new Node();
    	tail.post = null;

    	head.post = tail;
    	tail.pre = head;
    }

    public int get(int key) {

    	Node node = cache.get(key);
    	if(node == null){
    		return -1; // should raise exception here.
    	}

    	// move the accessed node to the head;
    	this.moveToHead(node);

    	return node.value;
    }


    public void set(int key, int value) {
    	Node node = cache.get(key);

    	if(node == null){

    		Node newNode = new Node();
    		newNode.key = key;
    		newNode.value = value;

    		this.cache.put(key, newNode);
    		this.addNode(newNode);

    		++count;

    		if(count > capacity){
    			// pop the tail
    			Node tail = this.popTail();
    			this.cache.remove(tail.key);
    			--count;
    		}
    	}else{
    		// update the value.
    		node.value = value;
    		this.moveToHead(node);
    	}

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
