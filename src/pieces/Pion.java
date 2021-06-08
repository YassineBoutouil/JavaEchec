package pieces;

import plateau.Case;

public class Pion extends Piece {
    public Pion(boolean couleur) {
        super(couleur);
    }

    @Override
    public boolean verifDeplacement(Case case_p) {
        return super.verifDeplacement(case_p) && this.calculNombreCaseDeplacement(case_p) < 3 && (this.estBlanc() ^ this.case_piece.getLigne() >= case_p.getLigne()) &&
               (case_p.estVide() || this.couleurOpposee(case_p.getPiece())) &&
               (
                   this.calculNombreCaseDeplacement(case_p) == 2 &&
                   (
                           ((Math.abs(this.case_piece.getColonne() - case_p.getColonne()) == 1) &&
                           (!case_p.estVide() && this.couleurOpposee(case_p.getPiece())) &&
                           !this.dejaJoue)
                       || ((Math.abs(this.case_piece.getColonne() - case_p.getColonne()) == 1) ^ this.calculNombreCaseDeplacement(case_p) == 2 && !this.dejaJoue)
                   ) ||
                   this.calculNombreCaseDeplacement(case_p) == 1
               );
        /*
        if(this.calculNombreCaseDeplacement(case_p) < 3) {
            if(!(this.case_piece == case_p)) {
                if(this.estBlanc() ^ (this.case_piece.getLigne() >= case_p.getLigne()) ) {
                    if(case_p.estVide() || this.couleurOpposee(case_p.getPiece())) {
                    }
                }
            }
        }
        return false;
        */
    }

    @Override
    public String toStringPiece() {
        if (this.estBlanc()) {
            return "p";
        }
        return "P";
    }

}
