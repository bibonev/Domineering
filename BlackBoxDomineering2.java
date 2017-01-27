import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class BlackBoxDomineering2 {
	private static class CLDomineering implements MoveChannel<DomineeringMove> {

		private String firstOrSecond = "";

		public CLDomineering(String firstOrSecond) {
			this.firstOrSecond = firstOrSecond;
		}

		@Override
		public DomineeringMove getMove() {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String move = "";
			try {
				move = sc.next();
			} catch (PatternSyntaxException e) {
				System.exit(1);
			}
			String[] moves = move.split(",");
			int x1 = Integer.parseInt(moves[0]);
			int y1 = Integer.parseInt(moves[1]);

			if (firstOrSecond.equals("first")) {
				return new DomineeringMove(x1, y1, x1, y1 + 1);
			} else {
				return new DomineeringMove(x1, y1, x1 + 1, y1);
			}
		}

		@Override
		public void giveMove(DomineeringMove move) {
			System.out.println(move);
			System.out.flush();
		}

		@Override
		public void end(int Value) {
			System.exit(0);
		}

		@Override
		public void comment(String msg) {
		}

	}

	public static void main(String[] args) {
		try {
			assert (args.length == 4);
		} catch (AssertionError e) {
			System.exit(1);
		}

		String firstOrSecond = args[0];
		int width = Integer.parseInt(args[2]);
		int height = Integer.parseInt(args[3]);
		
		DomineeringBoard2 board = new DomineeringBoard2(width, height);
		//Plays either optimal or heuristic depending on the size of the board
		/*if (width * height > 25 && width + height > 10) {
			if (firstOrSecond.equals("first")) {
				board.heuristicTree(2).firstPlayerHeuristic(new CLDomineering("first"));
			} else if (firstOrSecond.equals("second")) {
				board.heuristicTree(2).secondPlayerHeuristic(new CLDomineering("second"));
			}
		} else {
			if (firstOrSecond.equals("first")) {
				board.tree().firstPlayer(new CLDomineering("first"));
			} else if (firstOrSecond.equals("second")) {
				board.tree().secondPlayer(new CLDomineering("second"));
			}
		}*/
		
		//Play always heuristic as it beats in most of the games
		if (firstOrSecond.equals("first")) {
				board.heuristicTree(2).firstPlayerHeuristic(new CLDomineering("first"));
		} else if (firstOrSecond.equals("second")) {
			board.heuristicTree(2).secondPlayerHeuristic(new CLDomineering("second"));
		}
	}

}
