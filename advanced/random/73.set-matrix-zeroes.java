class Solution {
	public void setZeroes(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;

		if(m * n == 0) {
       	//one of more of the dimensions are zeros. Return
			return;
		}
		//idea: two special characters to mark whether the first row or column should be zero.
		int firstRow = 1;
		int firstColumn = 1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(matrix[i][j] == 0){
       				//problem: if we set the whole row or column here, it will skew the results(more zeros than wanted).
       				//solution: it won't hurt if we set only one cell to zero and use it as a marker.
       				//          This special cell is?? the first element of the row or the column.
       				//          What to do about it??  Don't update it, use a special variable instead. 
					if (i > 0) {
						matrix[i][0] = 0;
					}else {
						firstRow = 0;
					}
					if (j > 0) {
						matrix[0][j] = 0;
					} else {
						firstColumn = 0;
					}
				}
			}
		}
		for(int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
       		//BUGWARNING: don't start with zero, start with 1+ and update 0 later.
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		//BUGWARNING: Confused between firstColumn and firstRow? 
		if(firstColumn == 0) {
			for (int i = 0; i < m; i++) {
				matrix[i][0] = 0;
			}
		}
		if (firstRow == 0) {
			for (int j = 0; j < n; j++) {
				matrix[0][j] = 0;
			}
		}

	}
}