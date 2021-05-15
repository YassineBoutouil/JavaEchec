package moteur.affichage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import jeu.Partie;

@objid ("dc27ea02-a9c2-41ab-b13c-7da5ae915c60")
public class Interface {
    @objid ("26aa9685-b084-4bca-816e-167da61ee906")
    private Partie partie;

    @objid ("f1d12f57-8203-4163-b44a-7a467dc3cdca")
    Partie getPartie() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.partie;
    }

    @objid ("0331f265-2d65-439c-a7b5-dfea38d68ab6")
    void setPartie(Partie value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.partie = value;
    }

}
