package classes;

import android.app.Application;
import android.content.Context;


public class Global extends Application {

    private Context myContext;

    // attributs privé
    private Utilisateur mainUtilisateur = new Utilisateur(1,"test@fn.fr", "test");

    // getter/setter
    public Utilisateur getMainUtilisateur() {
        return mainUtilisateur;
    }

    public void setMainUtilisateur(Utilisateur mainUtilisateur) {
        this.mainUtilisateur = mainUtilisateur;
    }

    public Context getMyContext() {
        return myContext;
    }

    public void setMyContext(Context myContext) {
        this.myContext = myContext;
    }
}
