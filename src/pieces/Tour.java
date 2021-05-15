package pieces;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import plateau.Case;

@objid ("72ba4cd0-8a18-4f59-8cf1-7c1f4c6698e9")
public class Tour extends Piece {
    @objid ("ec2111d8-771e-4cbf-876a-841def11929f")
    public Tour(boolean couleur) {
        super(couleur);
    }

    @Override
    public boolean verifDeplacement(Case case_p) {
        return this.estRectiligne(case_p);
    }

    @Override
    public String toStringAffiche() {
        if (this.estBlanc()) {
            return "t";
        }
        return "T";
    }

}
