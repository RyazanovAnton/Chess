package chessengine.player.ai;

import chessengine.board.Board;

public interface BoardEvaluator {
    int evaluate(Board board, int depth);
}
