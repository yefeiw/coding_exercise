public class Solution {
double myPow(double x, int n) { 
    if(n==0) return 1;
    if(n<0) {
        n = -n;
        x = 1/x;
    }
    double ans = 1;
    while(n>0){
        if(n%2 ==1) ans *= x;
        x *= x;
        n >>= 1;
    }
    return ans;
}
    public double myPowStack(double x, int n) {
        if (x == 0.0) {
            if (n <= 0) {
                //0 only has meaningful positive power
                return Integer.MIN_VALUE;
            } else {
                return 0;
            }
        }
        if(x == 1.0) {
            return 1.0;
        }
        if (n == 0) {
            //here n is 0 and x is not 0. 
            return 1;
        }
        //Here, we need to use helper because there are two cases:
        //case 1, n >0
        if (n > 0) {
            return helper(x, n);
        } else {
            return 1 /helper(x, -n);
        }
    }

    double helper(double x, int n) {
        if (n == 1) {
            return x;
        }
        double tmp  = helper(x, n/2);
        if (n% 2 != 0 ) {
            return tmp * tmp * x;
        } else {
            return tmp * tmp;
        }
    }
}
