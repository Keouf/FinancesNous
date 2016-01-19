package classes;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyDBHelper {

    public JSONArray get10DerniersDepenses() {
        makeTaskAsynchrone();

        String phpURL = "http://berghuis-peter.net/FinanceNous/get10DerniersDepenses.php";
        JSONArray jsonArray = null;

        jsonArray = getDataInJson(phpURL);

        return jsonArray;
    }

    public JSONArray getInfo() {
        makeTaskAsynchrone();

        String phpURL = "http://berghuis-peter.net/androidTest/JSONTest.php";
        JSONArray jsonArray = null;

        jsonArray = getDataInJson(phpURL);

        return jsonArray;
    }

    private JSONArray getDataInJson(String phpURL ) {
        JSONArray jsonArray = null;
        try {
            HttpURLConnection urlConnection = getHttpURLConnection(phpURL);
            StringBuilder sb = getData(urlConnection);
            jsonArray = new JSONArray(sb.toString());

        } catch (MalformedURLException e) {
            Log.e("json", e.toString());
        } catch (IOException e) {
            Log.e("json", e.toString());
        } catch (Exception e) {
            Log.e("json", e.toString());
        }
        return jsonArray;
    }

    private void makeTaskAsynchrone() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }


    private StringBuilder getData(HttpURLConnection urlConnection) throws IOException {
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        InputStreamReader isw = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isw);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();
        urlConnection.disconnect();
        return sb;
    }

    private HttpURLConnection getHttpURLConnection(String phpURL) throws IOException {
        URL url = new URL(phpURL);
        return (HttpURLConnection) url.openConnection();
    }


}
