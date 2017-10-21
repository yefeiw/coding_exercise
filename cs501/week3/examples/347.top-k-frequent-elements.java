// Notes;
// 1. Need to think about bucket sort
// 2. Need to use KeySet and EntrySet fairly comfily
// 3. After getting hte freq an dfilling the buckets, need to get the right amount of answers
public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> ret = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) {
            return ret; 
        }
        //bucket used to bucket sort
        //IMPORTANT: generic list creation
        List<Integer>[] bucket = new List[nums.length+1];
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        //first iteration: update the freqMap with all frequency values
        for (int i = 0; i < nums.length; i++) {
           freqMap.put(nums[i],freqMap.getOrDefault(nums[i],0)+1);
        }
        //second iteration: update the bucket values
        for (int key : freqMap.keySet()) {
            int frequency = freqMap.get(key);
            if(bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            //important: add key one way or another
            bucket[frequency].add(key);
        }

        //third iteration: pick the right values from the bucket.
        for(int pos  = bucket.length-1; pos > 0; pos--) {
            if (bucket[pos] != null) {
                for(int i = 0; i < bucket[pos].size() && ret.size() < k; i++) {
                    ret.add(bucket[pos].get(i));
                }
            }
        }
        return ret;
    }
}
