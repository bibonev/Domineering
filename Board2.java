import java.util.LinkedHashMap;
import java.util.Set;

public abstract class Board2<Move> {
	abstract Player nextPlayer();

	abstract Set<Move> availableMoves();
	abstract Set<Move> availableMovesH();
	abstract Set<Move> availableMovesV();
	abstract int maxAvailableMoves();

	abstract int value();

	abstract Board2<Move> play(Move move);

	private int alpha = 1;
	private int beta = -1;
	
	private int level;

	// Constructs the game tree of the board using the minimax algorithm
	// (without alpha-beta pruning):
	public GameTree2<Move> tree() {
		if (availableMoves().isEmpty())
			return new GameTree2<Move>(this,
					new LinkedHashMap<Move, GameTree2<Move>>(), value());
		else
			return (nextPlayer() == Player.MAXIMIZER ? maxTree() : minTree());
	}

	// Two helper methods for that, which call the above method tree:
	public GameTree2<Move> maxTree() {
		assert (!availableMoves().isEmpty());

		int optimalOutcome = Integer.MIN_VALUE;
		LinkedHashMap<Move, GameTree2<Move>> children = new LinkedHashMap<Move, GameTree2<Move>>();

		for (Move m : availableMoves()) {
			GameTree2<Move> subtree = play(m).tree();
			children.put(m, subtree);
			optimalOutcome = Math.max(optimalOutcome, subtree.optimalOutcome());
			
			 if (optimalOutcome == alpha) {
		    	  break;
		      }
		}

		return new GameTree2<Move>(this, children, optimalOutcome);
	}

	public GameTree2<Move> minTree() {
		assert (!availableMoves().isEmpty());

		int optimalOutcome = Integer.MAX_VALUE;
		LinkedHashMap<Move, GameTree2<Move>> children = new LinkedHashMap<Move, GameTree2<Move>>();

		for (Move m : availableMoves()) {
			GameTree2<Move> subtree = play(m).tree();
			children.put(m, subtree);
			optimalOutcome = Math.min(optimalOutcome, subtree.optimalOutcome());
			
			 if (optimalOutcome == beta) {
		    	  break;
		      }
			
		}

		return new GameTree2<Move>(this, children, optimalOutcome);
	}
	
	public GameTree2<Move> heuristicTree(int level){
		  this.level = level;
		  if(availableMoves().isEmpty())
			  return new GameTree2<Move>
	      					(this, 
	      					new LinkedHashMap<Move,GameTree2<Move>>(), 
	      					value());
		  else if(level == 0){
			  return new GameTree2<Move>
				(this, 
				new LinkedHashMap<Move,GameTree2<Move>>(), 
				availableMovesH().size() - availableMovesV().size());
		  }else{
			  return (nextPlayer() == Player.MAXIMIZER ? maxTreeHeuristic() : minTreeHeuristic()); 
		  }
	  }
	
	public GameTree2<Move> maxTreeHeuristic() {
		assert (!availableMoves().isEmpty());

		int optimalOutcome = Integer.MIN_VALUE;
		LinkedHashMap<Move, GameTree2<Move>> children = new LinkedHashMap<Move, GameTree2<Move>>();

		for (Move m : availableMoves()) {
			GameTree2<Move> subtree = play(m).heuristicTree(level - 1);
			children.put(m, subtree);
			optimalOutcome = Math.max(optimalOutcome, subtree.optimalOutcome());
			
			 if (optimalOutcome == alpha) {
		    	  break;
		      }
		}

		return new GameTree2<Move>(this, children, optimalOutcome);
	}

	public GameTree2<Move> minTreeHeuristic() {
		assert (!availableMoves().isEmpty());

		int optimalOutcome = Integer.MAX_VALUE;
		LinkedHashMap<Move, GameTree2<Move>> children = new LinkedHashMap<Move, GameTree2<Move>>();

		for (Move m : availableMoves()) {
			GameTree2<Move> subtree = play(m).heuristicTree(level - 1);
			children.put(m, subtree);
			optimalOutcome = Math.min(optimalOutcome, subtree.optimalOutcome());
			
			 if (optimalOutcome == beta) {
		    	  break;
		      }
			
		}
		return new GameTree2<Move>(this, children, optimalOutcome);
	}


}
