package chessengine.player.ai;

import chessengine.board.Board;
import chessengine.board.Move;
import chessengine.player.MoveTransition;

import java.sql.SQLOutput;

public class MinMax implements MoveStrategy {
    private final BoardEvaluator boardEvaluator;
    private final int searchDepth;
    public MinMax(final int searchDepth){

        this.boardEvaluator = new StandardBoardEvaluator();
        this.searchDepth = searchDepth;
    }

    @Override
    public String toString() {

        return "MinMax";
    }

    @Override
    public Move execute(Board board) {

        final long startTime = System.currentTimeMillis();
        Move bestMove = null;
        int highestSeenValue = Integer.MIN_VALUE;
        int lowlestSeenValue = Integer.MAX_VALUE;
        int currentValue;
        System.out.println(board.currentPlayer() + "thinking with depth = " + this.searchDepth);
        int numMoves = board.currentPlayer().getLegalMoves().size();
        for (final Move move : board.currentPlayer().getLegalMoves()){
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if(moveTransition.getMoveStatus().isDone()){
                currentValue = board.currentPlayer().getAlliance().isWhite() ?
                        min(moveTransition.getTransitionBoard(), this.searchDepth-1) :
                        max(moveTransition.getTransitionBoard(), this.searchDepth-1);
                if(board.currentPlayer().getAlliance().isWhite() && currentValue >= highestSeenValue){
                    highestSeenValue = currentValue;
                    bestMove = move;
                }else  if (board.currentPlayer().getAlliance().isBlack() && currentValue <= lowlestSeenValue){
                    lowlestSeenValue = currentValue;
                    bestMove = move;
                }
            }
        }
        final long executionTime = System.currentTimeMillis() - startTime;
        return bestMove;
    }

    public int min(final Board board, final int depth){
        // depth == 0 -> gameOver
        if(depth == 0 || isEndGameScenario(board)){
            return this.boardEvaluator.evaluate(board, depth);
        }
        int lowestSeenValue = Integer.MAX_VALUE;
        for(final Move move : board.currentPlayer().getLegalMoves()){
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if(moveTransition.getMoveStatus().isDone()){
                final int currentValue = max(moveTransition.getTransitionBoard(), depth -1 );
                if(currentValue <= lowestSeenValue){
                    lowestSeenValue = currentValue;
                }
            }
        }
        return lowestSeenValue;
    }
    public int max(final Board board, final int depth){
        // depth == 0 -> gameOver
        if(depth == 0 || isEndGameScenario(board)){
            return this.boardEvaluator.evaluate(board, depth);
        }
        int highestSeenValue = Integer.MIN_VALUE;
        for(final Move move : board.currentPlayer().getLegalMoves()){
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if(moveTransition.getMoveStatus().isDone()){
                final int currentValue = min(moveTransition.getTransitionBoard(), depth -1 );
                if(currentValue >= highestSeenValue){
                    highestSeenValue = currentValue;
                }
            }
        }
        return highestSeenValue;
    }

    private static boolean isEndGameScenario(final Board board) {
        return board.currentPlayer().isInCheckMate() ||
                board.currentPlayer().isInStaleMate();
    }
}
