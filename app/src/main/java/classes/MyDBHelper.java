package classes;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MyDBHelper {

    public JSONArray get10DerniersDepenses() {
        makeTaskAsynchrone();

        String phpURL = "http://berghuis-peter.net/FinanceNous/get10DerniersDepenses.php";
        JSONArray jsonArray = null;

        jsonArray = getDataInJson(phpURL);

        return jsonArray;
    }

    public void insertDepense(Depense depense) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            sendData("http://berghuis-peter.net/FinanceNous/insertDepense.php?date=" + dateFormat.format(depense.getDateDepense()) + "&montant=" + depense.getMontant() + "&pieceJoint=" + depense.getPieceJoint() + "&refMagasin=" + depense.getMagasin().getNom_managasin() + "&refDomaine=" + depense.getDomaine() + "&idUtilisateur=" + depense.getUtilisatuer().getId_utilisateur());
        }
        catch (Exception e)
        {
            Log.e("json", e.toString());
        }
    }

    public ArrayList<String> getAllDomaines()
    {
        ArrayList<String> lesDomaines = null;

        makeTaskAsynchrone();

        String phpURL = "http://berghuis-peter.net/FinanceNous/getAllDomaines.php";
        JSONArray jsonArray = null;

        jsonArray = getDataInJson(phpURL);

        JsonConverter jsonConverter = new JsonConverter();

        lesDomaines = jsonConverter.ConvertDomaineToStringArrayList(jsonArray);

        return lesDomaines;
    }

    public ArrayList<String> getAllMagasins()
    {
        ArrayList<String> lesMagasins = null;

        makeTaskAsynchrone();

        String phpURL = "http://berghuis-peter.net/FinanceNous/getAllMagasins.php";
        JSONArray jsonArray = null;

        jsonArray = getDataInJson(phpURL);

        JsonConverter jsonConverter = new JsonConverter();

        lesMagasins = jsonConverter.ConvertMagasinToStringArrayList(jsonArray);

        return lesMagasins;
    }


    public Magasin getMagasinWithId(int idMagasin)
    {
        Magasin magasin = null;
        makeTaskAsynchrone();

        String phpURL = "http://berghuis-peter.net/FinanceNous/getMagasin.php?idMagasin="+idMagasin;

        JSONArray jsonArray = getDataInJson(phpURL);

        try {
            JSONObject json = jsonArray.getJSONObject(0);
            magasin = new Magasin(json.getInt("id_magasin"), json.getString("ref_magasin"), json.getString("adresse_magasin1"), json.getString("adresse_magasin2"), json.getString("code_postal_magasin"), json.getString("site_web_magasin"), json.getString("telephone_magasin"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return magasin;
    }

    public String getDomaineWithId(int idDomaine)
    {
        String domaine = null;
        makeTaskAsynchrone();

        String phpURL = "http://berghuis-peter.net/FinanceNous/getDomaine.php?idDomaine="+idDomaine;

        JSONArray jsonArray = getDataInJson(phpURL);

        try {
            JSONObject json = jsonArray.getJSONObject(0);
            domaine = json.getString("ref_domaine");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return domaine;
    }

    //---------------------------------------------------------

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

    private void sendData(String phpURL) {
        try {
            HttpURLConnection urlConnection = getHttpURLConnection(phpURL);
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            Writer w = new OutputStreamWriter(out, "UTF-8");
            w.write("Hello, World!");
            w.close(); //close will auto-flush

            


        } catch (IOException e) {
            e.printStackTrace();
        }
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
