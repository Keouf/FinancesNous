package classes;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Utilisateur implements Serializable {

    private int id_utilisateur;
    private String mail;
    private String motDePasse;
    private ArrayList<Depense> mesDepenses;

    public Utilisateur(int id_utilisateur, String mail, String motDePasse) {
        this.id_utilisateur = id_utilisateur;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.mesDepenses = new ArrayList<>();
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public ArrayList<Depense> getMesDepenses() {
        return mesDepenses;
    }

    public void setMesDepenses(ArrayList<Depense> mesDepenses) {
        this.mesDepenses = mesDepenses;
    }

    public void addDepense(Depense maDepense) {
        mesDepenses.add(maDepense);
    }

    public ArrayList<Depense> get10DernierDepenses() {
        ArrayList<Depense> mes10Depenses = new ArrayList<>();

        if (getMesDepenses().size() >= 10) {
            for (int i = getMesDepenses().size() - 1; i > (getMesDepenses().size() - 11); i--) {
                mes10Depenses.add(mesDepenses.get(i));
            }
        } else {
            mes10Depenses = getMesDepenses();
            Collections.reverse(mes10Depenses);
        }

        return mes10Depenses;
    }

    public void removeDepense(Depense depense) {
        for (int i = 0; i < mesDepenses.size(); i++) {
            if (mesDepenses.get(i).getIdDepense().equals(depense.getIdDepense())) {
                mesDepenses.remove(i);
            }
        }
    }

    public String getAllDepensesInString() {
        String depenses = "";

        for (Depense maDepense : mesDepenses) {
            depenses += (Integer.toString(maDepense.getIdDepense())) + " - ";
        }

        return depenses;
    }
}
