package pieces;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import plateau.Case;

@objid ("d8689fa4-5448-4b85-8f7d-e38e4b536479")
public class Dame extends Piece {
    @objid ("061e833b-d9bb-4bb8-99ee-f40bee8eaf1e")
    public Dame(boolean couleur) {
        super(couleur);
    }

    @Override
    public boolean verifDeplacement(Case case_p) {
        return this.estRectiligne(case_p) || this.estDiagonal(case_p);
    }

    @Override
    public String toStringAffiche() {
        if (this.estBlanc()) {
            return "d";
        }
        return "D";
    }

}
