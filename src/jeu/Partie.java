package jeu;

import pieces.Piece;
import plateau.Case;
import plateau.Echiquier;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Partie {

    private final String PATH = "./saves/";

    private Scanner scanner = new Scanner(System.in);

    private Echiquier plateau;

    private Joueur joueur_blanc;

    private Joueur joueur_noir;

    private Joueur joueurCourant = this.joueur_blanc;

    private boolean abandon;

    private boolean estFini;

    /**
     * Construis une partie à partir d'un échiquier et de 2 joueurs.
     * @param plateau un échiquier.
     * @param joueur_blanc un joueur blanc.
     * @param joueur_noir un joueur noir.
     */
    public Partie(Echiquier plateau, Joueur joueur_blanc, Joueur joueur_noir) {
        this.plateau = plateau;
        this.joueur_blanc = joueur_blanc;
        this.joueur_noir = joueur_noir;
        this.joueurCourant = this.joueur_blanc;
    }

    /**
     * Construis une partie à partir de 2 noms et construis un échiquier avec une configuration par défaut.
     * @param nom_blanc nom joueur blanc.
     * @param nom_noir nom joueur noir.
     */
    public Partie(String nom_blanc, String nom_noir) {
        this(new Echiquier(false), new Joueur(nom_blanc), new Joueur(nom_noir));
    }

    /**
     * Construis une partie par défaut.
     */
    public Partie() {
        this(new Echiquier(false), new Joueur("joueurBlanc"), new Joueur("JoueurNoir"));
    }

    /**
     * Construis une partie à partir de seulement d'un échiquier.
     * @param plateau un échiquier.
     */
    public Partie(Echiquier plateau) {
        this(plateau, new Joueur("joueurBlanc"), new Joueur("JoueurNoir"));
    }

    /**
     * Lance la partie d'échecs.
     */
    public void start() {
        while(!estFini) {
            tourDeJeu();
        }
    }

    /**
     * Gére un tour de jeu.
     * @see #estMatPat()
     * @see #action()
     * @see #estEnEchec()
     * @see #effectueeCoup()
     * @see #changerTrait()
     * @see #finirPartie()
     *
     */
    public void tourDeJeu()  {
        Echiquier sauvegardeTempEchiquier = new Echiquier(this.plateau);
        Partie.clearConsole();
        this.afficherPlateau();
        System.out.println("C'est au tour de " + this.joueurCourant.getNom());
        if(!this.estMatPat() && !this.action()) {
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

    /**
     * Demande un coordonnée tant qu'il n'est pas du bon format puis renvoie la case correspondant au coordonnée.
     * @return une case de l'échiquier.
     */
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

    /**
     * Demande au joueur qui a le trait de choisir une de ses pièces tant qu'il n'en a pas choisi une correcte.
     * @return une case contenant une pièce appartenant au joueur qui a le trait
     */
    public Case choisirPiece() {
        Case caseDepart;
        do {
            System.out.print("Choisir une pièce : ");
           caseDepart = this.choisirCaseValide();
        } while (caseDepart.estVide() || caseDepart.getPiece().couleurOpposee(this.traitAuBlanc()));
        return caseDepart;
    }

    /**
     * Demande de choisir un déplacement.
     * @return une case de l'échiquier.
     * @see #choisirCaseValide()
     */
    public Case choisirDeplacement() {
        return this.choisirCaseValide();
    }

    /**
     * Demande un coup tant qu'il n'est pas valide puis l'effectue.
     * @see #choisirPiece()
     * @see #choisirDeplacement()
     */
    public void effectueeCoup() {
        boolean coupFait = false;
        Case caseDepart, caseArrivee;
        while(!coupFait) {
            caseDepart = this.choisirPiece();
            System.out.print("Où déplacer la pièce ? ");
            caseArrivee = this.choisirDeplacement();
            if (plateau.estValide(caseDepart, caseArrivee)) {
                this.plateau.deplacerPiece(caseDepart, caseArrivee);
                coupFait = true;
            } else {
                System.out.println("Déplacement non valide. Veuillez resaisir le coup");
            }
        }
    }

    /**
     * Met fin à la partie
     */
    public void finirPartie() {
        this.estFini = true;
    }


    public void gererPromotion() {

    }

    /**
     * Indique si le joueur courant est en échec par rapport à la configuration du plateau.
     * @return true s'il est en échec.
     */
    public boolean estEnEchec() {
        Piece roi = this.plateau.getRoiJoueur(traitAuBlanc());
        ArrayList<Piece> pieces = this.plateau.getPiecesJoueur(!traitAuBlanc());
        for(Piece piece: pieces) {
            if(plateau.estValide(piece.getCase(), roi.getCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Vérifie que le joueur est en mat ou en pat. La distinction des 2 situations peut se faire après.
     * @return true le joueur est en mat ou en pat.
     */
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
        ArrayList<Piece> piecesJoueurCourant = this.getPlateau().getPiecesJoueur(this.traitAuBlanc());
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

    /**
     * Renvoie le joueur qui n'a pas le trait.
     * @return le joueur qui n'a pas le trait.
     */
    public Joueur getJoueurOpposee() {
        if(this.joueurCourant == this.joueur_blanc) {
            return this.joueur_noir;
        } else {
            return this.joueur_blanc;
        }
    }

    /**
     * Change le trait.
     */
    public void changerTrait() {
        this.joueurCourant = this.getJoueurOpposee();
    }

    /**
     * @return Renvoie le plateau.
     */
    public Echiquier getPlateau() {
        return this.plateau;
    }

    /**
     * Demande à l'utilisateur s'il veut abandonner, sauvegarder ou charge une partie.
     * @return true s'il a abandonné.
     */
    public boolean action() {
        System.out.println("Saisir : Oui pour abandonner | save pour charger une partie | load pour charger une partie | Non sinon");
        String choix;
        do {
            choix = scanner.nextLine();
        } while(choix.length() == 0);

        if(choix.equalsIgnoreCase("oui")) {
            this.finirPartie();
            this.abandon = true;
            return true;
        }
        if(choix.equalsIgnoreCase("save")) {
            while(!this.sauvegarder()){
                System.out.println("Fichier invalide.");
            }
        }
        if (choix.equalsIgnoreCase("load")) {
            while(!this.charger()){
                System.out.println("Fichier invalide.");
            }
        }
        return false;
    }

    /**
     * Affiche dans la console le plateau.
     */
    public void afficherPlateau() {
        System.out.println(getPlateau());
    }

    public Joueur getJoueurBlanc() {
        return this.joueur_blanc;
    }

    public Joueur getJoueurNoir() {
        return this.joueur_noir;
    }

    /**
     * Sauvegarde l'instance de plateau.
     * @return true si la sauvegarde s'est bien déroulée.
     */
    public boolean sauvegarder() {
        System.out.println("Entrez nom fichier : ");
        new File(PATH).mkdir();
        File file = new File(PATH + scanner.nextLine() + ".ser");
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(this.plateau);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Charger une instance de plateau à partir d'un fichier.
     * @return true si le chargement s'est bien déroulé.
     */
    public boolean charger() {
        System.out.println("Entrez nom fichier : ");
        File file = new File(PATH + scanner.nextLine() + ".ser");
        File dossier = new File(PATH);
        dossier.mkdir();
        if(!(dossier.listFiles().length == 0)) {
            try {
                file.createNewFile();
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                this.plateau = (Echiquier) in.readObject();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        this.afficherPlateau();
        return true;
    }

    /**
     * Nettoie l'affichage dans la console.
     */
    public static void clearConsole() {
        System.out.println("\r\n".repeat(100));
    }
}
