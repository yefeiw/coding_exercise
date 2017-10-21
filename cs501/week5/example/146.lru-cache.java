/*
 * [146] LRU Cache
 *
 * https://leetcode.com/problems/lru-cache
 *
 * algorithms
 * Hard (18.20%)
 * Total Accepted:    139.9K
 * Total Submissions: 768.5K
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
 */

class LRUCache {

    Map<Integer,Integer> entries;
    int capacity;

    public LRUCache(int capacity) {
        this.entries = new LinkedHashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!entries.containsKey(key)) {
          return -1;
        }
        int value = entries.get(key);
        //blindly remove the value and add it to the back.
        entries.remove(key);
        entries.put(key,value);
        //return the value now
        return value;
    }

    public void put(int key, int value) {
        if (entries.containsKey(key)) {
          //if key is there, there will not be any pressure to increase size,
          entries.remove(key);
          entries.put(key,value);
        } else {
          if (entries.size() == capacity) {
            //remove the first entry
            Iterator iter = entries.keySet().iterator();
            Integer first = (Integer)iter.next();
            entries.remove(first);
          }
          entries.put(key,value);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
