package classes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AndroidConnectivity {

    private Context myContext;

    // tester si une connection est détecté
    public boolean getConnectivityStatus() {
        ConnectivityManager cm = (ConnectivityManager) myContext.getSystemService(myContext.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();

        if (nInfo != null && nInfo.isConnected())
            return true;

        return false;
    }

    public AndroidConnectivity(Context myContext) {
        this.myContext = myContext;
    }
}
