public class CommandLineDomineering2 {
	private static class CLDomineering implements MoveChannel<DomineeringMove> {

		@Override
		public DomineeringMove getMove() {
			System.out.println("Enter move: ");
			
			int x1 = Integer.parseInt(System.console().readLine());
			int y1 = Integer.parseInt(System.console().readLine());
			int x2 = Integer.parseInt(System.console().readLine());
			int y2 = Integer.parseInt(System.console().readLine());
			
			System.out.println("Ok");
			return new DomineeringMove(x1, y1, x2, y2);
		}

		@Override
		public void giveMove(DomineeringMove move) {
			System.out.println("I play " + move);			
		}

		@Override
		public void end(int Value) {
			System.out.println("Game over. The result is " + Value);			
		}

		@Override
		public void comment(String msg) {
			System.out.println(msg);
		}
	    
	  }

	  public static void main(String [] args) {
	    DomineeringBoard2 board = new DomineeringBoard2(8,8);
	    board.heuristicTree(3).firstPlayerHeuristic(new CLDomineering());
	    //board.tree().secondPlayer(new CLDomineering());
	  }
}
