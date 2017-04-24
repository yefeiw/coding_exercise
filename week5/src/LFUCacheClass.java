import java.util.*;
//utility class about a cache node.
class CacheNode {
    public int key;
    public int freq;
    public int value;
    public CacheNode() {
        key = 0;
        freq = 0;
        value = 0;
    }
    public CacheNode(int key, int freq, int value) {
        this.key = key;
        this.freq = freq;
        this.value = value;
    }
}
class LFUCache {
    //////////////Internal Data Structure/////////////
    HashMap <Integer, CacheNode> entries;// each cache entry and its corresponding node values.
    LinkedHashSet<CacheNode>[] frequencyList; // record of the frequency list of each node.
    int minFreq;//minimum frequency of all valid entries
    int maxFreq;//maximum frequency of all valid entries
    int capacity;//total capacity

    /////////////Public Methods ///////////////////

    public LFUCache(int capacity) {
        entries = new HashMap<Integer,CacheNode>(capacity);
        this.capacity = capacity;
        maxFreq = capacity * 3;
        minFreq = 1;
        frequencyList = new LinkedHashSet[maxFreq+1];
        //furnish linkedHashSet;
        furnishFrequencyList();
        
    }
    
    public int get(int key) {
        //firstly, check if key in map.
        if (!entries.containsKey(key) || capacity == 0) {
            return -1;
        }
        //Now key is in the map, return the value and update frequency.
        CacheNode node = entries.get(key);
        increaseRank(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        //sanity check: should we every try
        if(capacity == 0) {
            return;
        }
        //firstly, in key is already in the map.
        if (entries.containsKey(key)) {
            CacheNode node =  entries. get(key);
            node.value = value;
            increaseRank(node);
        } else {
            //anyways we need to insert a new node.
            //so first check if we need to remove node.
            if (entries.size() == capacity) {
                removeLast();
            }
            CacheNode newNode = new CacheNode(key,1,value);
            insertNode(newNode);
        }
        
    }
    //////////////////// Private Methods ///////////////////
    void increaseRank(CacheNode node) {
        int curFreq = node.freq;
        if (curFreq == maxFreq) {
            //make sure that is the last element in the current list
            frequencyList[curFreq].remove(node);
            frequencyList[curFreq].add(node);
        } else {
            frequencyList[curFreq].remove(node);
            frequencyList[curFreq+1].add(node);
        }
        //here we possibly need to update the minFreq.
        if (curFreq == minFreq && frequencyList[curFreq].isEmpty()) {
            minFreq++;
        }
    }

    void removeLast() {
        //Here, we know that the minFreq is maintained so that frequencyList[minFreq] is always not null;
        Iterator<CacheNode> it = frequencyList[minFreq].iterator();
        CacheNode victim = it.next();
        frequencyList[minFreq].remove(victim);
        entries.remove(victim.key);
        //here we possibly need to update the minFreq.
        while (frequencyList[minFreq].isEmpty() && minFreq < maxFreq) {
                    System.out.printf("searching %d\n",minFreq);

            minFreq++;
        }
        if (minFreq == maxFreq) {
            minFreq = 1;
        }
    }
    void insertNode(CacheNode node) {
        frequencyList[1].add(node);
        entries.put(node.key,node);
        //here we possibly need to update the minFreq to 1
        minFreq = Math.min(minFreq,1);
    }
    void furnishFrequencyList() {
        for (int i = 0;i <= maxFreq; i++) {
            frequencyList[i] = new LinkedHashSet<CacheNode>();
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

public class LFUCacheClass{
    public static void main (String args[]) {
    // int testIteration = 10000;
        //small test set for manual verification
    int testIteration = 20;
    Input input = new Input();
    /// Known crashing tests
    System.out.println("test 1");
    LFUCache test1 = new LFUCache(0);
    test1.put(0,0);
    test1.get(0);
    //sanity tests
    System.out.println("test 2");
    LFUCache test2 = new LFUCache(1);
    test2.put(0,0);
    test2.put(1,1);
    test2.get(0);
    test2.get(1);

    int size  = Math.abs(input.generateRandomInt()) % 9;
    System.out.println("Created an LFU cache of size" + size);
    LFUCache cache = new LFUCache(size);
    int[] keys = {1,2,3,4,5,6,7,8,9};
    
    for (int i = 0; i < testIteration; i++) {
       int action = Math.abs(input.generateRandomInt() % 2);
       int key = Math.abs(input.generateRandomInt()) % 9;
       if (action == 0) {
        System.out.printf("put %d, %d into cache\n",key, 2*key);
        cache.put(key, 2*key);
       } else {
        System.out.printf("get %d value from cache\n",key);
        cache.get(key);
       }
    }
    System.out.println("Test executed without crashes, please manually verify input");
 }
}