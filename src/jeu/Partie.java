package jeu;

import plateau.Case;
import plateau.Echiquier;

public class Partie {

    private Echiquier plateau;

    private Joueur joueur_blanc;

    private Joueur joueur_noir;

    private Joueur joueurCourant = this.joueur_blanc;

    private boolean estFini;

    public Partie(Echiquier plateau, Joueur joueur_blanc, Joueur joueur_noir) {
        this.plateau = plateau;
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

    public void start() {
        while(!estFini) {
            tourDeJeu();
        }
    }

    public void tourDeJeu()  {
        Echiquier sauvegardeTempEchiquier = new Echiquier(this.plateau);
        this.afficherPlateau();
        if(!this.Abandonner() && !this.estMatPat()) {
            System.out.println("C'est au tour de " + this.joueurCourant);
            this.effectueeCoup();
            while(this.estEnEchec()) {
                this.plateau = new Echiquier(sauvegardeTempEchiquier);
                this.effectueeCoup();
            }
            this.changerTrait();
        } else {
            System.out.println("Abandon de "+this.joueurCourant);
            System.out.println(this.getJoueurOpposee() + " a gagné !");
            this.finirPartie();
        }

    }

    public Case choisirCaseValide() {
        String coord;
        boolean mauvaisFormat;
        do {
            coord = Joueur.choisirCase();
            mauvaisFormat = !Echiquier.estFormatCoord(coord);
            if(mauvaisFormat) {
                System.out.println("Format non valide. Réessayer.");
            }
        } while (mauvaisFormat);
        return plateau.getCasePlateau(coord);
    }

    public Case choisirPiece() {
        Case caseDepart;
        do {
            System.out.print("Choisir une pièce : ");
           caseDepart = this.choisirCaseValide();
        } while (caseDepart.estVide() || caseDepart.getPiece().couleurOpposee(this.traitAuBlanc()));
        return caseDepart;
    }

    public Case choisirDeplacement() {
        return this.choisirCaseValide();
    }

    public void effectueeCoup() {
        boolean coupFait = false;
        Case caseDepart, caseArrivee;
        while(!coupFait) {
            caseDepart = this.choisirPiece();
            System.out.print("Où déplacer la pièce ? ");
            caseArrivee = this.choisirDeplacement();
            if (plateau.estValide(caseDepart, caseArrivee)) {
                this.deplacerPiece(caseDepart, caseArrivee);
                coupFait = true;
            } else {
                System.out.println("Déplacement non valide. Veuillez resaisir le coup");
            }
        }
    }

    public void finirPartie() {
        this.estFini = true;
    }

    public void deplacerPiece(Case caseDepart, Case caseArrivee) {
        this.plateau.deplacerPiece(caseDepart, caseArrivee);
    }

    public void gererPromotion() {

    }

    public boolean estEnEchec() {
        return false;
    }

    public boolean estMatPat() {
        return false;
    }

    public void verifierFinDePartie() {

    }



    public void retourArriere() {

    }

    /**
     * Permet de savoir si le joueur qui a le trait est blanc
     * @return true si le joueur courant est blanc
     */
    public boolean traitAuBlanc() {
        return this.joueurCourant == this.joueur_blanc;
    }

    public Joueur getJoueurOpposee() {
        if(this.joueurCourant == this.joueur_blanc) {
            return this.joueur_noir;
        } else {
            return this.joueur_blanc;
        }
    }

    public void changerTrait() {
        this.joueurCourant = this.getJoueurOpposee();
    }

    public Echiquier getPlateau() {
        return this.plateau;
    }

    public boolean Abandonner() {
        System.out.println("Saisir Oui pour abandonner sinon Non");
        if(Joueur.choisirCase().equalsIgnoreCase("oui")) {
            this.finirPartie();
            return true;
        }
        return false;
    }

    public void afficherPlateau() {
        System.out.println(getPlateau());
    }

}
