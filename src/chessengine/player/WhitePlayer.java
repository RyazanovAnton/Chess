package chessengine.player;

import chessengine.Alliance;
import chessengine.board.Board;
import chessengine.board.Move;
import chessengine.board.Move.KingSideCastleMove;
import chessengine.board.Move.QueenSideCastleMove;
import chessengine.board.Tile;
import chessengine.pieces.Pawn;
import chessengine.pieces.Piece;
import chessengine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WhitePlayer extends Player {
    public WhitePlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves){
        super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentsLegals) {
        final List<Move> kingCastles = new ArrayList<>();
        if(this.playerKing.isFirstMove() && !this.isInCheck()){
            //This castlemoving for white Alliance
            if (!this.board.getTile(61).isTileOccupied() &&
                    !this.board.getTile(62).isTileOccupied()){
                final Tile rookTile = this.board.getTile(53);
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()){
                    if(Player.calculateAttacksOnTile(61, opponentsLegals).isEmpty() &&
                    Player.calculateAttacksOnTile(62, opponentsLegals).isEmpty() &&
                    rookTile.getPiece().getPieceType().isRook()){
                        kingCastles.add(new KingSideCastleMove(this.board, this.playerKing, 62,
                                (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 61));
                    }
                }
            }
            if (this.board.getTile(59).isTileOccupied() &&
                    !this.board.getTile(58).isTileOccupied() &&
                    !this.board.getTile(57).isTileOccupied()){
                final Tile rookTile = this.board.getTile(56);
                if(rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()){
                    kingCastles.add(new QueenSideCastleMove(this.board, this.playerKing, 58,
                            (Rook) rookTile.getPiece(), rookTile.getTileCoordinate(), 59));
                }
            }
        }
        return Collections.unmodifiableList(kingCastles);
    }
}
