/*
 * [149] Max Points on a Line
 *
 * https://leetcode.com/problems/max-points-on-a-line
 *
 * algorithms
 * Hard (15.29%)
 * Total Accepted:    82K
 * Total Submissions: 536.5K
 * Testcase Example:  '[]'
 *
 * Given n points on a 2D plane, find the maximum number of points that lie on
 * the same straight line.
 */
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {
    public int maxPoints(Point[] points) {
            if (points.length == 0) {
                return 0;
            }
            int cnt = 1;
            for (int i = 0; i < points.length; i++) {
                Point base = points[i];
                int sameX = 0;
                int sameY = 0;
                int samePoint = 0;
                Map<Long, Integer> map = new HashMap();
                int localCnt = 1;
                for (int j = 0; j < points.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    Point cand = points[j];
                    if (cand.x == base.x && cand.y == base.y) {
                        samePoint++;
                        localCnt = Math.max(localCnt,samePoint+1);
                    } else if (cand.x == base.x) {
                        sameX++;
                        localCnt = Math.max(localCnt,samePoint + sameX+1);
                    } else if (cand.y == base.y) {
                        sameY++;
                        localCnt = Math.max(localCnt,samePoint + sameY+1);
                    } else {
                        long key = getKey(cand,base);
                        map.put(key,map.getOrDefault(key,0)+1);
                        localCnt = Math.max(localCnt,samePoint + map.get(key)+1);
                    }
                }
                cnt = Math.max(cnt,localCnt);
            }

            return cnt;
    }

    private long getKey(Point cand, Point base) {
        int x = cand.x - base.x;
        int y = cand.y - base.y;
        int gcd = getGcd(Math.max(x,y), Math.min(x,y));
        x = x / gcd;
        y = y / gcd;
        long key = x;
        key = key << 32;
        key |= y;
        return key;
    }

    private int getGcd(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return getGcd(y,x%y);
        }
    }
}
