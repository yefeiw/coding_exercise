/*
 * [187] Repeated DNA Sequences
 *
 * https://leetcode.com/problems/repeated-dna-sequences
 *
 * algorithms
 * Medium (32.07%)
 * Total Accepted:    83.5K
 * Total Submissions: 260.5K
 * Testcase Example:  '"AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"'
 *
 *
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and
 * T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to
 * identify repeated sequences within the DNA.
 *
 * Write a function to find all the 10-letter-long sequences (substrings) that
 * occur more than once in a DNA molecule.
 *
 *
 * For example,
 *
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 *
 * Return:
 * ["AAAAACCCCC", "CCCCCAAAAA"].
 *
 */
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> sequences = new HashMap();
        List<String> result = new ArrayList();
        int m = s.length();
        for (int i = 0; i < m-9; i++) {
            String key = s.substring(i,i+10);
            sequences.put(key,sequences.getOrDefault(key,0)+1);
            if (sequences.get(key) == 2) {
                result.add(key);
            }
        }

        return result;

    }
}
