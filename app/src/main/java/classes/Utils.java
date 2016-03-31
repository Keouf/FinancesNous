package classes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class Utils {

    public static boolean getConnectivityStatus(Context myContext) {
        ConnectivityManager cm = (ConnectivityManager) myContext.getSystemService(myContext.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();

        return nInfo != null && nInfo.isConnected();

    }

}
