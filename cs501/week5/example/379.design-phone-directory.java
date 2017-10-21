/*
 * [379] Design Phone Directory
 *
 * https://leetcode.com/problems/design-phone-directory
 *
 * algorithms
 * Medium (33.41%)
 * Total Accepted:    11.5K
 * Total Submissions: 34.3K
 * Testcase Example:  '["PhoneDirectory","get","get","check","get","check","release","check"]\n[[3],[],[],[2],[],[2],[2],[2]]'
 *
 * Design a Phone Directory which supports the following operations:
 * 
 * 
 * 
 * get: Provide a number which is not assigned to anyone.
 * check: Check if a number is available or not.
 * release: Recycle or release a number.
 * 
 * 
 * 
 * Example:
 * 
 * // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 * PhoneDirectory directory = new PhoneDirectory(3);
 * 
 * // It can return any available phone number. Here we assume it returns 0.
 * directory.get();
 * 
 * // Assume it returns 1.
 * directory.get();
 * 
 * // The number 2 is available, so return true.
 * directory.check(2);
 * 
 * // It returns 2, the only number that is left.
 * directory.get();
 * 
 * // The number 2 is no longer available, so return false.
 * directory.check(2);
 * 
 * // Release number 2 back to the pool.
 * directory.release(2);
 * 
 * // Number 2 is available again, return true.
 * directory.check(2);
 * 
 * 
 */
class PhoneDirectory {

    //data structures
    private Set<Integer> set;
    int maxNumber;

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */

    public PhoneDirectory(int maxNumbers) {
        set = new LinkedHashSet<>();
        for(int i = 0; i < maxNumbers; i++) {
            set.add(i);
        }
        this.maxNumber= maxNumbers;
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (set.isEmpty()) {
            return -1;
        }
        Iterator itr = set.iterator();
        //idea: how to use the iterator is shown here.
        int val = (int) itr.next();
        set.remove(val);
        return val;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return set.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        set.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
