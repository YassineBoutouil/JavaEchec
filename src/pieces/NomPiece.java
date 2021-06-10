package pieces;

public enum NomPiece {
    Cavalier,
    Dame,
    Fou,
    Pion,
    Roi,
    Tour;

    public static NomPiece getPiece(String nomPiece) {
        return NomPiece.valueOf(nomPiece.substring(0, 1).toUpperCase()+nomPiece.substring(1).toLowerCase());
    }
}
