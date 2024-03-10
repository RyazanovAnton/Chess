import chessengine.board.Board;
import chessgui.Table;

public class JChess {
    public static void main(String[] args) {
        Board board = Board.createStandardBoard();
        System.out.println(board);

//        Table table = new Table();
        Table.get().show();
        System.out.println("03/03/2024");
    }
}
