package pieces;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import plateau.Case;

@objid ("e17aa7af-2e3b-4a6c-9c4f-e71e8199ed77")
public class Roi extends Piece {
    @objid ("135d4c2e-0034-4e6f-98ad-0d952f1346c1")
    public Roi(boolean couleur) {
        super(couleur);
    }

    @Override
    public boolean verifDeplacement(Case case_p) {
        return this.calculNombreCaseDeplacement(case_p) == 1 && this.estRectiligne(case_p)
                || this.estDiagonal(case_p) && this.calculNombreCaseDeplacement(case_p) == 2;
    }

    @Override
    public String toStringAffiche() {
        if (this.estBlanc()) {
            return "r";
        }
        return "R";
    }

}
