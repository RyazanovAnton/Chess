package chessengine;

import chessengine.board.Board;
import chessgui.Table;

public class JChess {
    public static void main(String[] args) {
        Board board = Board.createStandardBoard();
        System.out.println(board);
        Table.get().show();
    }
}
