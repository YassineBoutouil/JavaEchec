package plateau;

import pieces.Piece;

public class Case {

    private final int ligne; // y
    private final int colonne; // x

    private Piece piece = null; // Reference null == case vide

    /**
    * Contruis une case vide
    * @param x colonne
    * @param y ligne
    */
    public Case(int x, int y) {
        this.colonne = x;
        this.ligne = y;
    }

    /**
     * Construis une case avec une pièce
     * @param unePiece pièce contenu dans la case
     * @param x colonne
     * @param y ligne
     */
    public Case(Piece unePiece, int x, int y) {
        this(x ,y);
        this.piece = unePiece;
        this.piece.setCase(this);
    }

    /**
     *
     * @return La pièce contenu dans la case
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Modifie la pièce contenu dans la case de telle sorte
     * qu'elle référence l'instance courante de case.
     * @param p
     */
    public void setPiece(Piece p) {
        if (!(p.getCase() == null)) {
            p.getCase().viderCase();
        }
        p.setCase(this);
        this.piece = p;
    }

    /**
     * Retire la pièce contenu dans la case.
     */
    public void viderCase() {
        this.piece = null;
    }

    public int getLigne() {
        return this.ligne;
    }

    public int getColonne() {
        return this.colonne;
    }

    /**
     * Renvoie si la colonne de la case courante est égale à celle de la case
     * passée en paramètre
     * @param c Case testée
     * @return true si la case a la même colonne
     */
    public boolean memeColonne(Case c) {
        return this.colonne == c.getColonne();
    }

    /**
     * Renvoie si la ligne de la case courante est égale à celle de la case
     * passée en paramètre
     * @param c une case
     * @returnrue true si la case a la même ligne
     */
    public boolean memeLigne(Case c) {
        return this.ligne == c.getLigne();
    }

    /**
     * Renvoie si la case passée en paramètre est égale à celle courante
     * @param c une case
     * @return true si la case est identique
     */
    public boolean memeCase(Case c) {
        return this.memeColonne(c) && this.memeLigne(c);
    }

    /**
     * Renvoie si la case est vide. (case vide == null)
     * @return true si la case est vide
     */
    public boolean estVide() {
        return this.piece == null;
    }

    /**
     * Renvoie si la case courante est sur la même ligne ou même colonne que celle passée en paramètre
     * @param case_p une case
     * @return true si est sur la même ligne ou même colonne
     */
    public boolean estRectiligne(Case case_p) {
        return this.memeLigne(case_p) || this.memeColonne(case_p);
    }

    /**
     * Renvoie si la case courante est sur la même diagonale que celle passée en paramètre
     * @param case_p une case
     * @return true si est sur la même diagonale.
     */
    public boolean estDiagonal(Case case_p) {
        return Math.abs(this.getLigne() - case_p.getLigne()) ==
                Math.abs(this.getColonne() - case_p.getColonne());
    }

    public String toStringPiece(){
        if (this.piece == null) {
            return "-";
        }
        return this.piece.toStringAffiche();
    }

    @Override
    public String toString() {
        return "Case{" +
                "("+this.colonne+", "+this.ligne+")"+
                ", piece=" + this.piece +
                '}';
    }
}
