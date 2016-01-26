package classes;

import android.app.Application;


public class Global extends Application {

    // attributs privé
    private Utilisateur mainUtilisateur = new Utilisateur(1, "test@fn.fr", "test");

    // getter/setter
    public Utilisateur getMainUtilisateur() {
        return mainUtilisateur;
    }

    public void setMainUtilisateur(Utilisateur mainUtilisateur) {
        this.mainUtilisateur = mainUtilisateur;
    }
}
