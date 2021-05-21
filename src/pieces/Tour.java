package pieces;

import plateau.Case;

public class Tour extends Piece {
    public Tour(boolean couleur) {
        super(couleur);
    }

    @Override
    public boolean verifDeplacement(Case case_p) {
        return this.case_piece.estRectiligne(case_p);
    }

    @Override
    public String toStringAffiche() {
        if (this.estBlanc()) {
            return "t";
        }
        return "T";
    }

}
