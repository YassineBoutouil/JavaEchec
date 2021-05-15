package pieces;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import plateau.Case;

@objid ("35ca6454-a2a3-4733-a3cb-dd2b33e22417")
public class Pion extends Piece {
    @objid ("9628139f-eff1-4a91-ac04-2f64c79b3e2d")
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
    public String toStringAffiche() {
        if (this.estBlanc()) {
            return "p";
        }
        return "P";
    }

}
