import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DomineeringBoard extends Board<DomineeringMove> {
	private static final Player H = Player.MAXIMIZER;
	private static final Player V = Player.MINIMIZER;

	private final HashSet<DomineeringMove> hMoves;
	private final HashSet<DomineeringMove> vMoves;

	private final boolean[][] playBoard;

	public DomineeringBoard() {
		
		playBoard = new boolean[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				playBoard[i][j] = false;
			}
		}

		hMoves = new HashSet<DomineeringMove>();
		vMoves = new HashSet<DomineeringMove>();
	}

	public DomineeringBoard(int m, int n) {
		playBoard = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				playBoard[i][j] = false;
			}
		}

		hMoves = new HashSet<DomineeringMove>();
		vMoves = new HashSet<DomineeringMove>();
	}

	private DomineeringBoard(HashSet<DomineeringMove> hMoves, HashSet<DomineeringMove> vMoves, boolean[][] array) {
		assert(disjoint(hMoves,vMoves));
		this.hMoves = hMoves;
		this.vMoves = vMoves;
		this.playBoard = array;
	}
	
	@Override
	Player nextPlayer() {
		return ((hMoves.size() + vMoves.size()) % 2 == 0 ? H : V);
	}

	@Override
	Set<DomineeringMove> availableMoves() {
		return nextPlayer() == H ? complementH() : complementV();
	}

	@Override
	int value() {
		boolean all = true;
		
		for(int i = 0 ;i< playBoard.length; i++){
			for(int j = 0; j < playBoard[0].length; j++){
				if(playBoard[i][j] == false){
					all = false;
				}
			}
		}
		
		if (all) {
			if (nextPlayer() == H) return -1; else return 1; 
		}
		return (DomineeringMove.winsH(playBoard) ? 1 : DomineeringMove.winsV(playBoard) ? -1 : 0);
	}

	@Override
	Board<DomineeringMove> play(DomineeringMove move) {
		assert (!hMoves.contains(move) && !vMoves.contains(move));

		if (nextPlayer() == H) {
			return new DomineeringBoard(add(hMoves, move), vMoves, playMove(playBoard, move));
		} else {
			return new DomineeringBoard(hMoves, add(vMoves, move), playMove(playBoard, move));
		}
	}
	
	private boolean[][] playMove(boolean[][] a, DomineeringMove move) {
		boolean[][] b = new boolean[a.length][a[0].length];
		
		for(int i = 0; i < playBoard.length; i++){
			for(int j = 0; j < playBoard[i].length; j++){
				b[i][j] = playBoard[i][j];
			}
		}
		
		b[move.getX1()][move.getY1()] = true;
		b[move.getX2()][move.getY2()] = true;
		
		return b;
	}

	public String toString() {
		String print = "";
		
		for (int i = 0; i < playBoard[0].length; i++) {
			for (int j = 0; j < playBoard.length; j++) {
					print += pm(j, i) + " | ";
			}
			print += "\n================================\n";
		}
		
		return print;
	}

	@SuppressWarnings("rawtypes")
	private String pm(int m1, int m2) {
		Iterator it = hMoves.iterator();
		while (it.hasNext()) {
			DomineeringMove move = (DomineeringMove) it.next();
			if ((m1 == move.getX1() && m2 == move.getY1()) || (m1 == move.getX2() && m2 == move.getY2())) {

				return "H";
			}
		}

		Iterator it2 = vMoves.iterator();
		while (it2.hasNext()) {
			DomineeringMove move = (DomineeringMove) it2.next();
			if ((m1 == move.getX1() && m2 == move.getY1()) || (m1 == move.getX2() && m2 == move.getY2())) {

				return "V";
			}
		}

		return "*";
	}
	
	static private HashSet<DomineeringMove> intersection(HashSet<DomineeringMove> a, HashSet<DomineeringMove> b) {
		@SuppressWarnings("unchecked")
		HashSet<DomineeringMove> c = (HashSet<DomineeringMove>) a.clone(); // a.clone();
		c.retainAll(b);
		return c;
	}

	static private boolean disjoint(HashSet<DomineeringMove> a, HashSet<DomineeringMove> b) {
		return (intersection(a, b).isEmpty());
	}
	
	static private HashSet<DomineeringMove> add(HashSet<DomineeringMove> a, DomineeringMove b) {
		HashSet<DomineeringMove> c = new HashSet<DomineeringMove>();
		c.addAll(a);
		c.add(b);
		return c;
	}

	private HashSet<DomineeringMove> complementH() {
		HashSet<DomineeringMove> all = new HashSet<DomineeringMove>();
		
		//H
		for(int i = 0; i < playBoard.length-1; i++){
			for(int j = 0; j < playBoard[0].length; j++){
				if(!playBoard[i][j] && !playBoard[i+1][j]){
					all.add(new DomineeringMove(i, j, i + 1, j));
				}
			}
		}
		
		return all;
	}

	private HashSet<DomineeringMove> complementV() {
		HashSet<DomineeringMove> all = new HashSet<DomineeringMove>();
		
		//V
		for(int i = 0; i < playBoard.length; i++){
			for(int j = 0; j < playBoard[0].length-1; j++){
				if(!playBoard[i][j] && !playBoard[i][j + 1]){
					all.add(new DomineeringMove(i, j, i, j+1));
				}
			}
		}
		
		return all;
	}
}
