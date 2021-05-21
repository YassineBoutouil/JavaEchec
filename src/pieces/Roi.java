package pieces;

import plateau.Case;

public class Roi extends Piece {
    public Roi(boolean couleur) {
        super(couleur);
    }

    @Override
    public boolean verifDeplacement(Case case_p) {
        return this.calculNombreCaseDeplacement(case_p) == 1 && this.case_piece.estRectiligne(case_p)
                || this.case_piece.estDiagonal(case_p) && this.calculNombreCaseDeplacement(case_p) == 2;
    }

    @Override
    public String toStringAffiche() {
        if (this.estBlanc()) {
            return "r";
        }
        return "R";
    }

}
