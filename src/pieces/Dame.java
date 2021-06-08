package pieces;

import plateau.Case;

public class Dame extends Piece {
    public Dame(boolean couleur) {
        super(couleur);
    }

    @Override
    public boolean verifDeplacement(Case case_p) {
        return this.case_piece.estRectiligne(case_p) || this.case_piece.estDiagonal(case_p);
    }

    @Override
    public String toStringPiece() {
        if (this.estBlanc()) {
            return "d";
        }
        return "D";
    }

}
