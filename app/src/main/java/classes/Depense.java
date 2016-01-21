package classes;


import java.util.Date;

public class Depense {

    private Integer idDepense;
    private Date dateDepense;
    private float montant;
    private String pieceJoint;
    private Magasin magasin;
    private Domaine domaine;
    private Utilisateur utilisatuer;

    public Integer getIdDepense() {
        return idDepense;
    }
    public void setIdDepense(Integer idDepense) {
        this.idDepense = idDepense;
    }
    public Date getDateDepense() {
        return dateDepense;
    }
    public void setDateDepense(Date dateDepense) {
        this.dateDepense = dateDepense;
    }
    public float getMontant() {
        return montant;
    }
    public void setMontant(float montant) {
        this.montant = montant;
    }
    public String getPieceJoint() {
        return pieceJoint;
    }
    public void setPieceJoint(String pieceJoint) {
        this.pieceJoint = pieceJoint;
    }
    public Magasin getMagasin() {
        return magasin;
    }
    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }
    public Domaine getDomaine() {
        return domaine;
    }
    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }
    public Utilisateur getUtilisatuer() {
        return utilisatuer;
    }
    public void setUtilisatuer(Utilisateur utilisatuer) {
        this.utilisatuer = utilisatuer;
    }

    public Depense(Integer idDepense, Date dateDepense, float montant, Utilisateur utilisatuer, Domaine domaine, Magasin magasin, String pieceJoint) {
        this.idDepense = idDepense;
        this.dateDepense = dateDepense;
        this.montant = montant;
        this.utilisatuer = utilisatuer;
        this.domaine = domaine;
        this.magasin = magasin;
        this.pieceJoint = pieceJoint;
    }
}
