package jeu;

import pieces.Piece;
import plateau.Case;
import plateau.Echiquier;

import java.util.ArrayList;

public class Partie {

    private Echiquier plateau;

    private Joueur joueur_blanc;

    private Joueur joueur_noir;

    private Joueur joueurCourant = this.joueur_blanc;

    private boolean abandon;

    private boolean estFini;

    public Partie(Echiquier plateau, Joueur joueur_blanc, Joueur joueur_noir) {
        this.plateau = plateau;
        this.joueur_blanc = joueur_blanc;
        this.joueur_noir = joueur_noir;
        this.joueurCourant = this.joueur_blanc;
    }

    public Partie(String nom_blanc, String nom_noir) {
        this(new Echiquier(false), new Joueur(nom_blanc), new Joueur(nom_noir));
    }

    public Partie() {
        this(new Echiquier(false), new Joueur("joueurBlanc"), new Joueur("JoueurNoir"));
    }

    public Partie(Echiquier plateau) {
        this(plateau, new Joueur("joueurBlanc"), new Joueur("JoueurNoir"));
    }

    public void start() {
        while(!estFini) {
            tourDeJeu();
        }
    }

    public void tourDeJeu()  {
        Echiquier sauvegardeTempEchiquier = new Echiquier(this.plateau);
        Partie.clearConsole();
        this.afficherPlateau();
        System.out.println("C'est au tour de " + this.joueurCourant.getNom());
        if(!this.estMatPat() && !this.abandonner()) {
            if(!this.estEnEchec()) {
                this.effectueeCoup();
            }
            while(this.estEnEchec()) {
                System.out.println(this.joueurCourant.getNom() + " est en échec.");
                this.plateau = new Echiquier(sauvegardeTempEchiquier);
                this.effectueeCoup();
            }
            this.changerTrait();
        } else {
            this.finirPartie();
            if(!estEnEchec() && !this.abandon)
                System.out.println("EGALITE");
            else
                System.out.println(this.getJoueurOpposee() + " a gagné !");
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
        Piece roi = this.plateau.getRoiJoueur(traitAuBlanc());
        ArrayList<Piece> pieces = this.plateau.getPieceJoueur(!traitAuBlanc());
        for(Piece piece: pieces) {
            if(plateau.estValide(piece.getCase(), roi.getCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean estMatPat() {
        /*
           Pour toutes les pièces du joueur courant, on va regarder s'il existe une case sur laquelle une de ses pièces peut se déplacer
           et on "simule", dans ce cas là, un coup pour vérifier si ce déplacement ne mettrait pas en échec le joueur.
           Le joueur est pat s'il n'existe aucun coup possible et que le roi n'est pas attaqué
           Le joueur est echec et mat s'il n'existe aucun coup qui sauve son roi de l'échec
        */
        Piece pieceTemp;
        Case casePiece;
        boolean pat = true, echec = this.estEnEchec();
        /*
         On part du principe que le joueur est en mat/pat. On cherche un contre exemple.
         Un échec et mat équivaut à un pat où le roi est attaqué
         */
        ArrayList<Piece> piecesJoueurCourant = this.getPlateau().getPieceJoueur(this.traitAuBlanc());
        Echiquier sauvegardeTempEchiquier = new Echiquier(this.getPlateau());
        for(Piece piece : piecesJoueurCourant) {
            for(int ligne = 0; Echiquier.dansEchiquier(ligne) && pat ; ligne++) {
                for (int colonne = 0; Echiquier.dansEchiquier(colonne) && pat; colonne++) {
                    if(this.getPlateau().estValide(piece.getCase(), this.getPlateau().getCasePlateau(colonne, ligne))){
                        casePiece = piece.getCase();
                        this.plateau = new Echiquier(this.plateau);
                        pieceTemp = this.plateau.getCasePlateau(casePiece.getColonne(), casePiece.getLigne()).getPiece();
                        this.plateau.deplacerPiece(pieceTemp.getCase(), this.plateau.getCasePlateau(colonne, ligne));
                        if(!this.estEnEchec()) {
                            pat = false;
                        }
                        this.plateau = sauvegardeTempEchiquier;
                    }
                }
            }
        }
        return pat;
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

    public boolean abandonner() {
        System.out.println("Saisir oui pour abandonner sinon non");
        if(Joueur.choisirCase().equalsIgnoreCase("oui")) {
            this.finirPartie();
            this.abandon = true;
            return true;
        }
        return false;
    }

    public void afficherPlateau() {
        System.out.println(getPlateau());
    }

    public Joueur getJoueurBlanc() {
        return this.joueur_blanc;
    }

    public Joueur getJoueurNoir() {
        return this.joueur_noir;
    }

    public static void clearConsole() {
        System.out.println("\r\n".repeat(100));
    }
}
