package chessengine.player.ai;

import chessengine.board.Board;
import chessengine.board.Move;

public class MinMax implements MoveStrategy {
    private final BoardEvaluator boardEvaluator;
    public MinMax(){
        this.boardEvaluator = null;
    }

    @Override
    public String toString() {
        return "MinMax";
    }

    @Override
    public Move execute(Board board, int depth) {
        return null;
    }
}
