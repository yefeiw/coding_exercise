public class Solution {
    class Stat {
        char c;
        int freq;
        public Stat(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
        public void increment() {
            this.freq++;
        }
    }
    public String frequencySort(String s) {
        if (s.length() < 2) {
		    return s;
        }
        Map<Character, Stat> dict = new HashMap<Character, Stat>();
        PriorityQueue<Stat> heap = new PriorityQueue<Stat>(10,(Stat a, Stat b) ->(b.freq - a.freq));
        for(int i = 0;i < s.length(); i++) {
            char c  = s.charAt(i);
            if(!dict.containsKey(c)) {
                dict.put(c,new Stat(c,0));
            }
            dict.get(c).increment();
        }
        for(char c : dict.keySet()) {
            Stat stat = dict.get(c);
            heap.add(stat);
        }
        //output answer
        StringBuffer sb = new StringBuffer();
        while(!heap.isEmpty()) {
            Stat front = heap.poll();
            for(int j = 0; j < front.freq; j++) {
                sb.append(front.c);
            }
        }
        return sb.toString();
    }
}
