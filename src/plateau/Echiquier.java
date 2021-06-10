package plateau;

import pieces.NomPiece;
import pieces.Piece;
import pieces.Roi;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Echiquier implements Serializable {

    public final static int TAILLE_ECHIQUIER = 8;
    public final Case[][] plateau = new Case[TAILLE_ECHIQUIER][TAILLE_ECHIQUIER];

    /**
     * Construis un échiquier possiblement vide.
     * @param vide si l'échiquier sera vide.
     */
    public Echiquier(boolean vide) {
        for(int y = 0; y < (TAILLE_ECHIQUIER); y++) {
            for(int x = 0;  x < (TAILLE_ECHIQUIER); x++) {
                this.setCasePlateau(new Case(x, y));
            }
        }
        if(!vide)
            initialiserPiece();

    }

    /**
     * Constructeur par copie d'un echiquier.
     * @param plateau échiquier à copier.
     * @see Case#Case(Case)
     */
    public Echiquier(Echiquier plateau) {
        for(int ligne = 0; ligne < (TAILLE_ECHIQUIER); ligne++) {
            for(int colonne = 0;  colonne < (TAILLE_ECHIQUIER); colonne++) {
                this.setCasePlateau(new Case(plateau.getCasePlateau(colonne, ligne)));
            }
        }
    }

    /**
     * Initialise les pièces avec une configuration standard d'un échiquier.
     */
    public void initialiserPiece() {
        ajouterPiece(NomPiece.Tour, true, 0, 0); ajouterPiece(NomPiece.Cavalier, true, 1, 0);
        ajouterPiece(NomPiece.Fou, true, 2, 0); ajouterPiece(NomPiece.Dame, true, 3, 0);
        ajouterPiece(NomPiece.Roi, true, 4, 0); ajouterPiece(NomPiece.Fou, true, 5, 0);
        ajouterPiece(NomPiece.Cavalier, true, 6, 0); ajouterPiece(NomPiece.Tour, true, 7, 0);

        ajouterPiece(NomPiece.Pion, true, 0, 1); ajouterPiece(NomPiece.Pion, true, 1, 1);
        ajouterPiece(NomPiece.Pion, true, 2, 1); ajouterPiece(NomPiece.Pion, true, 3, 1);
        ajouterPiece(NomPiece.Pion, true, 4, 1); ajouterPiece(NomPiece.Pion, true, 5, 1);
        ajouterPiece(NomPiece.Pion, true, 6, 1); ajouterPiece(NomPiece.Pion, true, 7, 1);

        ajouterPiece(NomPiece.Tour, false, 0, 7); ajouterPiece(NomPiece.Cavalier, false, 1, 7);
        ajouterPiece(NomPiece.Fou, false, 2, 7); ajouterPiece(NomPiece.Dame, false, 3, 7);
        ajouterPiece(NomPiece.Roi, false, 4, 7); ajouterPiece(NomPiece.Fou, false, 5, 7);
        ajouterPiece(NomPiece.Cavalier, false, 6, 7); ajouterPiece(NomPiece.Tour, false, 7, 7);

        ajouterPiece(NomPiece.Pion, false, 0, 6); ajouterPiece(NomPiece.Pion, false, 1, 6);
        ajouterPiece(NomPiece.Pion, false, 2, 6); ajouterPiece(NomPiece.Pion, false, 3, 6);
        ajouterPiece(NomPiece.Pion, false, 4, 6); ajouterPiece(NomPiece.Pion, false, 5, 6);
        ajouterPiece(NomPiece.Pion, false, 6, 6); ajouterPiece(NomPiece.Pion, false, 7, 6);
    }

    /**
     * Renvoie les pièces de l'échiquier correspondantes à la couleur passée en paramètre.
     * @param couleur couleur des pièces voulues.
     * @return Un tableau de pièce contenant l'ensemble des pièces de l'échiquier correspondantes à la couleur choisie.
     */
    public ArrayList<Piece> getPiecesJoueur(boolean couleur) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for(Case[] ligne : this.plateau)  {
            for(Case uneCase: ligne) {
                if(!uneCase.estVide() && !(uneCase.getPiece().couleurOpposee(couleur))) {
                    pieces.add(uneCase.getPiece());
                }
            }
        }
        return pieces;
    }

    /**
     * Renvoie le roi du joueur correspondant.
     * @param couleur la couleur du joueur.
     * @return le roi du joueur.
     */
    public Piece getRoiJoueur(boolean couleur) {
        Piece piece = null;
        for(int ligne = 0; dansEchiquier(ligne); ligne++) {
            for(int colonne = 0; dansEchiquier(colonne); colonne++) {
                piece = this.getCasePlateau(colonne, ligne).getPiece();
                if(piece instanceof Roi && !piece.couleurOpposee(couleur)) {
                    return piece;
                }
            }
        }
        return piece;
    }

    /**
     * Construis une chaine qui représente l'échiquier.
     * @return Renvoie une représentation de l'échiquier.
     */
    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder("    ---");

        for(int i = 1; dansEchiquier(i); i++) chaine.append("---");
        chaine.append("\n     ");
        for(int i = 0; dansEchiquier(i); i++) chaine.append((char)(65 + i)).append("  ");
        chaine.insert(0, "\n");

        for(int ligne = 0; dansEchiquier(ligne); ligne++) {
            for(int colonne = TAILLE_ECHIQUIER-1; dansEchiquier(colonne); colonne--) {
                chaine.insert(0, "  " + this.getCasePlateau(colonne, ligne).toStringPiece());
            }
            chaine.insert(0, "\n" + (ligne+1) + " |");
        }
        return chaine.toString();

    }

    /**
     * Ajoute une pièce aux coordonéees indiqués.
     * @param piece une piece.
     * @param x la colonne.
     * @param y la ligne.
     */
    public void ajouterPiece(Piece piece, int x, int y) {
        this.getCasePlateau(x, y).setPiece(piece);
    }

    /**
     * Construit et ajoute une pièce aux coordonnées indiqués.
     * @param piece nom de la pièce.
     * @param couleur couleur de la pièce.
     * @param x la colonne.
     * @param y la ligne.
     * @see pieces.Piece#parse(NomPiece, boolean)
     */
    public void ajouterPiece(NomPiece piece, boolean couleur, int x, int y) {
        try {
            this.getCasePlateau(x, y).setPiece(Piece.parse(piece, couleur));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Place la case en adéquation avec ses coordonnées.
     * @param c une case
     */
    private void setCasePlateau(Case c) {
        this.plateau[c.getLigne()][c.getColonne()] = c;
    }

    /**
     * Renvoie la case aux coordonnées indiqués.
     * @param colonne la colonne
     * @param ligne la ligne
     * @return une case de l'échiquier.
     */
    public Case getCasePlateau(int colonne, int ligne) {
        return this.plateau[ligne][colonne];
    }

    /**
     * Renvoie la case au cordonnées XX indiqués.
     * @param coord un coordonnée de la forme de XX.
     * @return une case de l'échiquier.
     */
    public Case getCasePlateau(String coord) {
        int ligne, colonne;
        if(Character.isLowerCase(coord.charAt(0))) {
            colonne = coord.charAt(0) - 32 - 65;
        } else {
            colonne = coord.charAt(0) - 65;
        }
        ligne = coord.charAt(1) - 49;
        return this.getCasePlateau(colonne, ligne);
    }

    /**
     * Indique si un déplacement d'une case à une autre est valide en fonction du modèle de déplacement de la possible pièce sur la case de départ et de la configuration de l'échiquier.
     * @param caseDepart une case de départ.
     * @param caseArrivee une case d'arrivée.
     * @return True si le déplacement est possible.
     * @see pieces.Piece#verifDeplacement(Case)
     */
    public boolean estValide(Case caseDepart, Case caseArrivee) {
        int x_init, y_init;
        int x = 0, y = 0;
        if (!caseDepart.estVide() && !caseDepart.memeCase(caseArrivee) && caseDepart.getPiece().verifDeplacement(caseArrivee)) {
            Point p = caseDepart.getPiece().directionDeplacement(caseArrivee);
            x = caseDepart.getColonne() + ((int) p.getX());
            y = caseDepart.getLigne() + ((int) p.getY());
            x_init = (int) p.getX();
            y_init = (int) p.getY();
            while (dansEchiquier(x, y) && getCasePlateau(x, y).estVide() && !getCasePlateau(x, y).memeCase(caseArrivee)) {
                x += x_init;
                y += y_init;
            }
            return (dansEchiquier(x, y) && getCasePlateau(x, y).memeCase(caseArrivee) &&
                    (caseArrivee.estVide() || caseDepart.getPiece().couleurOpposee(caseArrivee.getPiece())));
        }
        return false;
    }

    /**
     *
     * @param pieceDepart une pièce de départ.
     * @param pieceArrivee une pièce attaquée.
     * @see #estValide(Case, Case)
     */
    public boolean estValide(Piece pieceDepart, Piece pieceArrivee) {
        return estValide(pieceDepart.getCase(), pieceArrivee.getCase());
    }

    /**
     * Déplace la pièce d'une case de départ à une case d'arrivée.
     * @param caseDepart une case de départ.
     * @param caseArrivee une case d'arrivée.
     * @return true le déplacement a pu se faire.
     */
    public boolean deplacerPiece(Case caseDepart, Case caseArrivee) {
        if(!caseDepart.estVide()) {
            caseDepart.getPiece().aBouge();
            caseArrivee.setPiece(caseDepart.getPiece());
            return true;
        }
        return false;
    }

    /**
     * Vérifie qu'un coordonnée de format XX est correcte.
     * @param coord un coordonnée.
     * @return true s'il est du bon format.
     */
    public static boolean estFormatCoord(String coord) {
        if (coord.length() >= 2) {
            char lettre = coord.charAt(0);
            if(Character.isLetter(lettre)) {
                if(Character.isLowerCase(lettre)) lettre = (char)(lettre-32);
                int nombre;
                try {
                    nombre = Integer.parseInt(coord.substring(1));
                } catch (NumberFormatException e) {
                    return false;
                }
                return lettre <= TAILLE_ECHIQUIER + 65 && nombre > 0 && nombre <= TAILLE_ECHIQUIER;
            }
        }
        return false;
    }

    /**
     * Indique si les coordonnées sont correctes par rapport à la taille de l'échiquier.
     * @param colonne une colonne.
     * @param ligne une ligne.
     * @return true si les coordonnées sont dans l'échiquier.
     */
    private static boolean dansEchiquier(int colonne, int ligne) {
        return dansEchiquier(colonne) && dansEchiquier(ligne);
    }

    /**
     * Indique si le coordonnée est correcte par rapport à la taille de l'échiquier.
     * @param x une ligne ou colonne.
     * @return true si le coordonnée est dans l'échiquier.
     */
    public static boolean dansEchiquier(int x) {
        return 0 <= x && x < TAILLE_ECHIQUIER;
    }
}

