public class Solution {
    public String getHint(String secret, String guess) {
        char[] dict = secret.toCharArray();
        char[] input = guess.toCharArray();
        //counters
        int bulls = 0;
        int cows = 0;
        Map<Character, Integer> map = new HashMap<Character,Integer>();
        for (char c : dict) {
            map.put(c, map.getOrDefault(c,0) + 1);
        }
        for(int i = 0; i < input.length; i++) {
            if (!map.containsKey(input[i])) {
                //nothing is found, continue;
                continue;
            }
            int times = map.get(input[i]);
            if (input[i] == dict[i]) {
                bulls++;
                if (times == 0) {
                    cows --;
                } else  {
                    map.put(input[i],times-1);
                }
            } else if (times > 0 ) {
                cows++;
                map.put(input[i],times-1);
            }

        }
        //generate output
        StringBuilder sb =  new StringBuilder();
        sb.append(bulls);
        sb.append("A");
        sb.append(cows);
        sb.append("B");
         return sb.toString();
        
    }
}
