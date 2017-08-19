class Solution {
	class Position {
		public int x;
		public int y;
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
    public void wallsAndGates(int[][] rooms) {
       if (rooms.length == 0  || rooms[0].length == 0) {
       	return;
       } 
       Deque<Position> queue = new ArrayDeque<>();
       int[] dx = {0,1,0,-1};
       int[] dy = {1,0,-1,0};
       for(int i = 0; i < rooms.length;i++) {
       	for (int j = 0; j < rooms[0].length	; j++) {
       		if (rooms[i][j] == 0) {
       			queue.add(new Position(i,j));
       		}
       	}
       }
       int count = 0;
       while(!queue.isEmpty()) {
       		count++;
       		int size = queue.size();
       		for (int t = 0; t < size; t++) {
	       		Position front =  queue.remove();
	       		for (int i = 0; i < 4; i++) {
	       			int newx = front.x + dx[i];
	       			int newy = front.y + dy[i];
	       			if (newx < 0 || newx >= rooms.length) continue;
	       			if (newy < 0 || newy >= rooms[0].length) continue;
	       			if (rooms[newx][newy] == Integer.MAX_VALUE) {
	       				rooms[newx][newy] = count;
	       				queue.add(new Position(newx,newy));
	       			}
	       		}
       		}
       }

       return;

    }
}