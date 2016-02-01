package classes;


import java.util.ArrayList;

public class Utilisateur {

    private int id_utilisateur;
    private String mail;
    private String motDePasse;
    private ArrayList<Depense> mesDepenses;


    public int getId_utilisateur() {
        return id_utilisateur;
    }
    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    public ArrayList<Depense> getMesDepenses() {
        return mesDepenses;
    }
    public void setMesDepenses(ArrayList<Depense> mesDepenses) {
        this.mesDepenses = mesDepenses;
    }
    public void addDepense(Depense maDepense)
    {
        mesDepenses.add(maDepense.getIdDepense(), maDepense);
    }

    public Utilisateur(int id_utilisateur, String mail, String motDePasse) {
        this.id_utilisateur = id_utilisateur;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.mesDepenses = new ArrayList<>();
    }

    public ArrayList<Depense> get10DernierDepenses()
    {
        ArrayList<Depense> mes10Depenses = new ArrayList<>();

        for (int i = getMesDepenses().size(); i >= )

        return mes10Depenses;
    }
}
