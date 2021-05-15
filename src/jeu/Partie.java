package jeu;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import plateau.Echiquier;

@objid ("d5ed1d07-cdbb-47e5-9a0c-7cecb731ffdc")
public class Partie {
    @objid ("0678e7c7-4546-428f-bd99-77f6e6f657ef")
    private Joueur joueur_blanc;

    @objid ("3a1131d1-cb49-47e7-88c0-6bb0a28db3d5")
    private Echiquier echiquierPartie;

    @objid ("02be086e-a860-4ce5-9c6d-2c2aeefd6ada")
    private Joueur joueur_noir;

    @objid ("e0fd533c-8e56-4f46-aeb1-d396851b7791")
    private Joueur joueurCourant = this.joueur_blanc;
}
