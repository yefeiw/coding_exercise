public class Solution {
    public boolean isHappy(int n) {
        if (n <= 0) {
            //said it will be positive integer
            return false;
        }
        Set<Integer> visited = new HashSet<Integer>();
        while(n != 1) {
            n = calc(n);
            if (visited.contains(n)) {
                //deadloop
                return false;
            }
            visited.add(n);
        }
        return true;
    }
    int calc (int n) {
        int ret = 0;
        while(n > 0) {
            int digit = n % 10;
            ret += digit * digit;
            n /= 10;
        }
        return ret;
    }
}
