public class DomineeringMove {
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public DomineeringMove (int x1, int y1, int x2, int y2) {
		this.setX1(x1);
		this.setY1(y1);
		this.setX2(x2);
		this.setY2(y2);
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public static boolean winsH(boolean[][] moves) {
		for(int i = 0; i < moves.length; i++){
			for(int j = 0; j < moves[0].length - 1; j++){
					if(!moves[i][j] && !moves[i][j+1]){
						 return false;
				}
			}
		}
		return true;
		
		
	}

	public static boolean winsV(boolean[][] moves) {
		for(int i =0; i < moves.length - 1; i++){
			for(int j = 0; j < moves[0].length; j++){
				if(!moves[i][j] && !moves[i+1][j]){
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	public String toString() {
		return this.getX1() + "," + this.getY1();
	}
	
	public int hashCode() {
		return (this.getX1() * 9999 + this.getY1() * 999 + this.getX2() * 99 + this.getY2()); 
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof DomineeringMove)) {
			return false;
		} else {
			DomineeringMove m = (DomineeringMove) o;
			if (this.getX1() == m.getX1() && this.getX2() == m.getX2() && this.getY1() == m.getY1() && this.getY2() == m.getY2()) {
				return true;
			} else {
				return false;
			}
		}
	}
}
