package classes;


import java.util.Map;

public class Utilisateur {

    private int id_utilisateur;
    private String mail;
    private String test;
    private Map<Integer, Depense> mesDepenses;


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
    public String getTest() {
        return test;
    }
    public void setTest(String test) {
        this.test = test;
    }
    public Map<Integer, Depense> getMesDepenses() {
        return mesDepenses;
    }
    public void setMesDepenses(Map<Integer, Depense> mesDepenses) {
        this.mesDepenses = mesDepenses;
    }

    public Utilisateur(int id_utilisateur, String mail, String test) {
        this.id_utilisateur = id_utilisateur;
        this.mail = mail;
        this.test = test;
    }
}
