package pieces;

import plateau.Case;

import java.awt.*;

public class Cavalier extends Piece {
    public Cavalier(boolean couleur) {
        super(couleur);
    }

    @Override
    public boolean verifDeplacement(Case case_p) {
        return this.calculNombreCaseDeplacement(case_p) == 3 && !this.case_piece.estRectiligne(case_p);
    }

    @Override
    public Point directionDeplacement(Case case_p) {
        return new Point(case_p.getColonne() - this.case_piece.getColonne(), case_p.getLigne() - this.case_piece.getLigne());
    }

    @Override
    public String toStringPiece() {
        if (this.estBlanc()) {
            return "c";
        }
        return "C";
    }
}
