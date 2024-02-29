package chessengine.pieces;

import chessengine.Alliance;
import chessengine.board.Board;
import chessengine.board.Move;
import chessengine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17 };
    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }
    @Override
    public List<Move> calculateLegalMoves(Board board) {
        int candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidate : CANDIDATE_MOVE_COORDINATES){
            candidateDestinationCoordinate = this.piecePosition + currentCandidate;
            if(true /*isValidTileCoordinate*/){
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if(!candidateDestinationTile.isTileOccupied()){
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                    if(this.pieceAlliance != pieceAlliance){
                        legalMoves.add(new Move());
                    }
                }
            }
        }




        return Collections.unmodifiableList(legalMoves);
    }
}