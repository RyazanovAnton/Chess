package chessengine.pieces;

import chessengine.Alliance;
import chessengine.board.Board;
import chessengine.board.BoardUtils;
import chessengine.board.Move;
import chessengine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static chessengine.board.Move.*;

public class King extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(final Alliance pieceAlliance, final int piecePosition) {

        super(PieceType.KING, piecePosition, pieceAlliance);
    }
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                    isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)) {
                continue;
            }
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if (!candidateDestinationTile.isTileOccupied()) {         // если плитка не занята
                        legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAlliance) {            // если плитка занята фигурой противника
                            legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }
    @Override
    public King movePiece(Move move) {
        return new King(move.getMovedPiece().pieceAlliance, move.getDestinationCoordinate());
    }

    @Override
    public String toString(){
        return PieceType.KING.toString();
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == -1 ||
                candidateOffset == 7);
    }
    private static boolean isEighthColumnExclusion(final  int currentPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currentPosition]  && (candidateOffset == - 7|| candidateOffset == 1 ||
                candidateOffset == 9);
    }
}
