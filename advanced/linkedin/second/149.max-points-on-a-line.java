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
        int ret = 0;
        if (points.length < 2) {
            return points.length;
        }

        for (int i = 0; i < points.length; i++) {
            Map<Double,Integer> map = new HashMap<>();
            int numSameX = 0;
            int numSameY = 0;
            int samePoint = 0;

            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;
                if (points[j].x == points[i].x && points[j].y == points[i].y) {
                    samePoint++;
                    ret = Math.max(ret,samePoint+1);
                }else if (points[j].x == points[i].x) {
                    numSameX++;
                    ret = Math.max(ret,numSameX+samePoint+1);
                }else if (points[j].y == points[i].y) {
                    numSameY++;
                    ret = Math.max(ret,numSameY+samePoint+1);
                } else {
                double k = (double)points[j].y - points[i].y;
                k = k / ((double)points[j].x - points[i].x);
                System.out.println("k is " + k);

                int cnt = map.getOrDefault(k,0) + 1;
                ret = Math.max(cnt+samePoint+1,ret);
                map.put(k,cnt);
                }
            }
        }

        return ret;
    }
}
