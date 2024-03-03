package chessengine.player;

import chessengine.board.Board;
import chessengine.board.Move;

public class MoveTransition {
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;


    public MoveTransition(final Board transitiontBoard,
                          final Move move,
                          final MoveStatus moveStatus) {
        this.transitionBoard = transitiontBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }

    public Board getTransitionBoard() {
        return this.transitionBoard;
    }
}
