package chessengine.player.ai;

import chessengine.board.Board;
import chessengine.board.Move;

public interface MoveStrategy {

    Move execute(Board board);



}
