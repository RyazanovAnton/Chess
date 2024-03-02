package chessengine.player;

import chessengine.board.Board;
import chessengine.board.Move;

import java.util.concurrent.Future;

public class MoveTransition {
    private final Board transmitBoard;
    private final Move move;
    private final MoveStatus moveStatus;


    public MoveTransition(final Board transmitBoard,
                          final Move move,
                          final MoveStatus moveStatus) {
        this.transmitBoard = transmitBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }
}
