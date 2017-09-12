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
        if(points.length <= 0) return 0;
        if(points.length <= 2) return points.length;
        int result = 0;
        for(int i = 0; i < points.length; i++){
            HashMap<Double, Integer> hm = new HashMap<Double, Integer>();
            int samex = 1;
            int samep = 0;
            for(int j = 0; j < points.length; j++){
                if(j != i){
                    if((points[j].x == points[i].x) && (points[j].y == points[i].y)){
                        samep++;
                    }
                    if(points[j].x == points[i].x){
                        samex++;
                        continue;
                    }
                    double k = (double)(points[j].y - points[i].y) / (double)(points[j].x - points[i].x);
                    if(hm.containsKey(k)){
                        hm.put(k,hm.get(k) + 1);
                    }else{
                        hm.put(k, 2);
                    }
                    result = Math.max(result, hm.get(k) + samep);
                }
            }
            result = Math.max(result, samex);
        }
        return result;
    }
}
//     public int maxPoints(Point[] points) {
//         if (points == null || points.length == 0) {
//         	return 0;
//         }
//         int maxNumber = 0;
//         for (int i = 0; i < points.length; i++) {
//         	int sameNumber = 0;
//         	Map<Double, Integer> map = new HashMap<>();
//         	for (int j = i+1; j < points.length; j++) {
//         		if (points[j].x == points[i].x && points[j].y == points[i].y) {
//         			sameNumber++;
//         		} else {
//         			double angle = (points[i].x == points[j].x) ? 
//         			Double.MAX_VALUE : (points[i].y == points[j].y) ? 
//         			0.0 : (((double)points[j].y - points[i].y) / ((double)points[j].x - points[i].x));
//         			System.out.println("angle is "+angle);
//         			if (map.containsKey(angle)) {
//         				map.put(angle, map.get(angle)+1);
//         			} else {
//         				map.put(angle, 1);
//         			}
//         		}
//         	}
//         	int curMax = 0;
//         	for (int number: map.values()) {
//         		curMax = Math.max(curMax, number);
//         	}
//         	maxNumber= Math.max(maxNumber, curMax + sameNumber + 1);
//         }

//         return maxNumber;
//     }
// }
