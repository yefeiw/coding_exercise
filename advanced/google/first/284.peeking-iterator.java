import java.util.Iterator;

/*
 * [284] Peeking Iterator
 *
 * https://leetcode.com/problems/peeking-iterator/description/
 *
 * algorithms
 * Medium (35.85%)
 * Total Accepted:    58K
 * Total Submissions: 161.6K
 * Testcase Example:  '["PeekingIterator","next","peek","next","next","hasNext"]\n[[[1,2,3]],[],[],[],[],[]]'
 *
 * Given an Iterator class interface with methods: next() and hasNext(), design
 * and implement a PeekingIterator that support the peek() operation -- it
 * essentially peek() at the element that will be returned by the next call to
 * next().
 * 
 * Example:
 * 
 * 
 * Assume that the iterator is initialized to the beginning of the list:
 * [1,2,3].
 * 
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after
 * that still return 2. 
 * You call next() the final time and it returns 3, the last element. 
 * Calling hasNext() after that should return false.
 * 
 * 
 * Follow up: How would you extend your design to be generic and work with all
 * types, not just integer?
 * 
 */
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
	private Iterator<Integer> iterator;
	private Integer buffer;

	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		this.iterator = iterator;
		buffer = (this.iterator.hasNext()) ? this.iterator.next() : null;
	    
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return buffer;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		Integer ret = buffer;
		buffer = iterator.hasNext() ? iterator.next() : null;
		return ret;
	}

	@Override
	public boolean hasNext() {
	    return (iterator.hasNext() || buffer != null);
	}
}
