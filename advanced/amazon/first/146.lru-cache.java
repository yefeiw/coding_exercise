
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

    private Map<Integer, Node> cache = new HashMap();
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
