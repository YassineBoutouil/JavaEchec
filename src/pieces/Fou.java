package pieces;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import plateau.Case;

@objid ("24aba8ce-93b2-4402-8170-2e126c249a89")
public class Fou extends Piece {
    @objid ("f1e9f6cd-c8a1-4b25-aeb6-b466663a335c")
    public Fou(boolean couleur) {
        super(couleur);
    }

    @Override
    public boolean verifDeplacement(Case case_p) {
        return this.estDiagonal(case_p);
    }

    @Override
    public String toStringAffiche() {
        if (this.estBlanc()) {
            return "f";
        }
        return "F";
    }
}
