package classes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Vidjay Seganti on 09/02/2016.
 */
public final class Utils {

    public static boolean getConnectivityStatus(Context myContext) {
        ConnectivityManager cm = (ConnectivityManager) myContext.getSystemService(myContext.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();

        if (nInfo != null && nInfo.isConnected())
            return true;

        return false;
    }

}
