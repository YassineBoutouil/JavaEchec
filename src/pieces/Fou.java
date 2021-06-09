package pieces;

import plateau.Case;

public class Fou extends Piece {
    public Fou(boolean couleur) {
        super(couleur);
    }

    @Override
    public boolean verifDeplacement(Case case_p) {
        return !this.getCase().memeCase(case_p) && this.case_piece.estDiagonal(case_p);
    }

    @Override
    public String toStringPiece() {
        if (this.estBlanc()) {
            return "f";
        }
        return "F";
    }
}
