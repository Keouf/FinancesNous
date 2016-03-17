package classes;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class StorageHelper {

    public StorageHelper() {
    }

    public static void storeObject(Context context, Object object) {

        try {
            context.deleteFile("UserData.data");

            FileOutputStream fos = context.openFileOutput("UserData.data", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(object);
            os.close();
            fos.close();
        } catch (Exception e) {
            Log.e("userFile", "Error: Failed to save User into internal storage - \n" + e.toString());
        }

    }

    public static Utilisateur getUtilisateur(Context context) {
        Utilisateur mainUtilisateur = null;

        try {
            FileInputStream fis = context.openFileInput("UserData.data");
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

    public static boolean fileExists(Context context) {
        File file = context.getFileStreamPath("UserData.data");
        return file.exists();
    }
}
