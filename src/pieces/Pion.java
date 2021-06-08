package pieces;

import plateau.Case;

public class Pion extends Piece {
    public Pion(boolean couleur) {
        super(couleur);
    }

    @Override
    public boolean verifDeplacement(Case case_p) {
        return (this.calculNombreCaseDeplacement(case_p) < 3) && !(this.case_piece == case_p) && (this.estBlanc() ^ this.case_piece.getLigne() >= case_p.getLigne()) &&
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

    }

    @Override
    public String toStringPiece() {
        if (this.estBlanc()) {
            return "p";
        }
        return "P";
    }

}
