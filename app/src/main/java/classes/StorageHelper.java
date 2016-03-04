package classes;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StorageHelper {

    String FILENAME = "UserData.data";
    Activity act;

    public StorageHelper(Activity activity) {
        this.act = activity;
    }

    public void storeObject(Object object) {
        try {
            act.getApplicationContext().deleteFile(FILENAME);

            FileOutputStream fos = act.getApplicationContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(object);
            os.close();
            fos.close();
        } catch (Exception e) {
            Log.e("userFile", "Error: Failed to save User into internal storage - \n" + e.toString());
        }
    }

    public Utilisateur getUtilisateur() {
        Utilisateur mainUtilisateur = null;

        try {
            FileInputStream fis = act.getApplicationContext().openFileInput(FILENAME);
            ObjectInputStream is = new ObjectInputStream(fis);

            mainUtilisateur = (Utilisateur) is.readObject();

            is.close();
            fis.close();

        } catch (Exception e) {
            Log.e("userFile", "Error: loading from the internal storage failed - \n" + e.toString());
        } finally {
            return mainUtilisateur;
        }

    }


}
