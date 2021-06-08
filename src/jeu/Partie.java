package jeu;

import plateau.Case;
import plateau.Echiquier;

public class Partie {

    private Joueur joueur_blanc;

    private Echiquier echiquierPartie;

    private Joueur joueur_noir;

    private Joueur joueurCourant = this.joueur_blanc;

    public Partie(Echiquier plateau, Joueur joueur_blanc, Joueur joueur_noir) {
        this.echiquierPartie = plateau;
        this.joueur_blanc = joueur_blanc;
        this.joueur_noir = joueur_noir;
        this.joueurCourant = this.joueur_blanc;
    }

    public Partie(String nom_blanc, String nom_noir) {
        this(new Echiquier(), new Joueur(nom_blanc), new Joueur(nom_noir));
    }

    public Partie() {
        this(new Echiquier(), new Joueur("joueurBlanc"), new Joueur("JoueurNoir"));
    }

    public boolean tourDeJeu()  {
        return false;
    }

    public Case choisirPiece() {
        return null;
    }

    public Case saisirCoup() {
        return null;
    }

    public void deplacerPiece() {

    }

    public void prendrePiece() {

    }

    public void gererPromotion() {

    }

    public void verifierMatPat() {

    }

    public void verifierFinDePartie() {

    }

}
