package pieces;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import plateau.Case;

@objid ("aec0c2b6-c1b6-4291-8c7a-f4243d43787c")
public class Cavalier extends Piece {
    @objid ("8b41bb88-e6ac-456b-8dfb-4d6f80925bdf")
    public Cavalier(boolean couleur) {
        super(couleur);
    }

    @Override
    public boolean verifDeplacement(Case case_p) {
        return this.calculNombreCaseDeplacement(case_p) == 3 && !this.estRectiligne(case_p);
    }

    @Override
    public String toStringAffiche() {
        if (this.estBlanc()) {
            return "c";
        }
        return "C";
    }
}
