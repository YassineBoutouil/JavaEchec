package pieces;

import plateau.Case;

public class Pion extends Piece {
    /**
     * Construis un pion avec la couleur passé en paramètre
     * @param couleur true pour blanc
     */
    public Pion(boolean couleur) {
        super(couleur);
    }

    public boolean verifDeplacement(Case case_p) {
        if(!this.getCase().memeCase(case_p) && this.calculNombreCaseDeplacement(case_p) < 3) {
            if((this.estBlanc() ^ this.case_piece.getLigne() >= case_p.getLigne())) {
                if((case_p.estVide() || this.couleurOpposee(case_p.getPiece()))) {
                    if(this.calculNombreCaseDeplacement(case_p) == 2) {
                        if(Math.abs(this.case_piece.getColonne() - case_p.getColonne()) == 1) {
                            return !case_p.estVide() && this.couleurOpposee(case_p.getPiece());
                        }
                        return !this.dejaJoue && case_p.estVide();
                    }
                    return this.calculNombreCaseDeplacement(case_p) == 1 && case_p.estVide();
                }
            }
        }
        return false;
    }

    @Override
    public String toStringPiece() {
        if (this.estBlanc()) {
            return "p";
        }
        return "P";
    }

}
