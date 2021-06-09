package pieces;

import plateau.Case;

public class Tour extends Piece {
    public Tour(boolean couleur) {
        super(couleur);
    }

    @Override
    public boolean verifDeplacement(Case case_p) {
        return !this.getCase().memeCase(case_p) && this.case_piece.estRectiligne(case_p);
    }

    @Override
    public String toStringPiece() {
        if (this.estBlanc()) {
            return "t";
        }
        return "T";
    }

}
