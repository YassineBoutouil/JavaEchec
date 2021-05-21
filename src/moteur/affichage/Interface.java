package moteur.affichage;

import jeu.Partie;

public class Interface {
    private Partie partie;

    Partie getPartie() {
        return this.partie;
    }

    void setPartie(Partie value) {
        this.partie = value;
    }

}
