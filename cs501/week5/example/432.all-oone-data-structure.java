/*
* [432] All O`one Data Structure
*
* https://leetcode.com/problems/all-oone-data-structure
*
* algorithms
* Hard (28.16%)
* Total Accepted:    7.2K
* Total Submissions: 25.7K
* Testcase Example:  '["AllOne","getMaxKey","getMinKey"]\n[[],[],[]]'
*
* Implement a data structure supporting the following operations:
*
*
*
* Inc(Key) - Inserts a new key  with value 1. Or increments an existing key by
* 1. Key is guaranteed to be a non-empty string.
* Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise
* decrements an existing key by 1. If the key does not exist, this function
* does nothing. Key is guaranteed to be a non-empty string.
* GetMaxKey() - Returns one of the keys with maximal value. If no element
* exists, return an empty string "".
* GetMinKey() - Returns one of the keys with minimal value. If no element
* exists, return an empty string "".
*
*
*
*
* Challenge: Perform all these in O(1) time complexity.
*
*/
class AllOne {
  //data structure
  class Bucket {
    public int rank;
    public Set<String> values;
    Bucket pre;
    Bucket next;


    public Bucket(int rank) {
      this.rank = rank;
      this.values = new HashSet<>();
    }
  }

  //key: int key;  value: ranks
  Map<String, Integer> ranks;
  Map<Integer,Bucket> BucketMap;
  //adding two guard Buckets for simplicity
  private Bucket headGuard;
  private Bucket tailGuard;
  /** Initialize your data structure here. */
  public AllOne() {
    ranks = new HashMap<>();
    BucketMap = new HashMap<>();
    BucketMap.put(Integer.MIN_VALUE, new Bucket(Integer.MIN_VALUE));
    BucketMap.put(Integer.MAX_VALUE, new Bucket(Integer.MAX_VALUE));
    headGuard = BucketMap.get(Integer.MIN_VALUE);
    tailGuard = BucketMap.get(Integer.MAX_VALUE);
    headGuard.next = tailGuard;
    tailGuard.pre = headGuard;
  }

  /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
  public void inc(String key) {
    if (!ranks.containsKey(key)) {
      //insert into one.
      if (getMinBucket().rank != 1) {
        addBucketAfter(headGuard, new Bucket(1));
      }
      ranks.put(key,1);
      BucketMap.get(1).values.add(key);
    } else {
      int rank = ranks.get(key);
      int nextRank = rank + 1;
      if (!BucketMap.containsKey(nextRank)) {
        addBucketAfter(BucketMap.get(rank),new Bucket(nextRank));
      }
      //first, add to nextRank
      BucketMap.get(nextRank).values.add(key);
      //second update the map information
      ranks.put(key, nextRank);
      //third, remove from rank
      Bucket current = BucketMap.get(rank);
      current.values.remove(key);
      if (current.values.size() == 0) {
        removeBucket(current);
      }
    }
  }

  /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
  public void dec(String key) {
    if (!ranks.containsKey(key)) {
      return;
    }
    int rank = ranks.get(key);
    int nextRank = rank - 1;
    //first remove from current rank;
    Bucket current = BucketMap.get(rank);
    current.values.remove(key);

    ranks.remove(key);
    if (rank > 1) {
      ranks.put(key,nextRank);
      if (!BucketMap.containsKey(nextRank)) {
        addBucketBefore(BucketMap.get(rank),new Bucket(nextRank));
      }
      BucketMap.get(nextRank).values.add(key);
    }
    //BUG:make sure we add first then delete
    if (current.values.size() ==0) {
      removeBucket(current);
    }
    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
      Bucket maxBucket = tailGuard.pre;
      if (maxBucket == null|| maxBucket.values.size() == 0) {
        return "";
      }
      return (String)maxBucket.values.iterator().next();

    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
      Bucket minBucket = headGuard.next;
      if (minBucket == null || minBucket.values.size() == 0) {
        return "";
      }
      return (String)minBucket.values.iterator().next();
    }

    //util fucntions
    void addBucketBefore(Bucket ref, Bucket cand) {
      Bucket prev = ref.pre;
      prev.next = cand;
      cand.pre = prev;
      ref.pre = cand;
      cand.next = ref;
      int rank = cand.rank;
      BucketMap.put(rank,cand);
    }

    void addBucketAfter(Bucket ref, Bucket cand) {
      Bucket next = ref.next;
      next.pre = cand;
      cand.next = next;
      ref.next = cand;
      cand.pre = ref;
      int rank = cand.rank;
      BucketMap.put(rank,cand);
    }

    void removeBucket(Bucket cand) {
      Bucket pre = cand.pre;
      Bucket next = cand.next;
      pre.next = next;
      next.pre = pre;
      BucketMap.remove(cand.rank);
    }

    Bucket getMaxBucket() {
      return tailGuard.pre;
    }

    Bucket getMinBucket() {
      return headGuard.next;
    }




  }

  /**
  * Your AllOne object will be instantiated and called as such:
  * AllOne obj = new AllOne();
  * obj.inc(key);
  * obj.dec(key);
  * String param_3 = obj.getMaxKey();
  * String param_4 = obj.getMinKey();
  */
