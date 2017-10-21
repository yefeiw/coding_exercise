/*
 * [146] LRU Cache
 *
 * https://leetcode.com/problems/lru-cache
 *
 * algorithms
 * Hard (17.64%)
 * Total Accepted:    131K
 * Total Submissions: 741.9K
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
 */
public class LRUCache {
	//data structures;
	Map<Integer,Integer> map; 
	int capacity;
    public LRUCache(int capacity) {
       	this.capacity = capacity;
       	this.map =  new LinkedHashMap<Integer, Integer>(); 
           
    }
    
    public int get(int key) {
       if (map.containsKey(key)) {
       	int value =  map.get(key);
       	map.remove(key);
       	map.put(key,value);
       	return map.get(key);
       } else {
       	return -1;
       } 
    }
    
    public void put(int key, int value) {
    	if(map.containsKey(key)) {
    		map.remove(key);
    	}
       map.put(key,value);
       if(map.size() > capacity) {
       	map.remove(map.entrySet().iterator().next().getKey());
       }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
