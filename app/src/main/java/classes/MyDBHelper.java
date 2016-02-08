package classes;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MyDBHelper {

    JsonConverter jsonConverter = new JsonConverter();

    public Utilisateur getUtilisateur(String userMail) {
        makeTaskAsynchrone();

        String phpURL = "http://berghuis-peter.net/FinanceNous/userLogin.php?idUtilisateur=" + userMail;

        JSONArray jsonArray = getDataInJson(phpURL);

        return jsonConverter.ConvertJsonArrayToUtilisateur(jsonArray);
    }

    public ArrayList<Depense> getMesDepenses(Global global) {
        makeTaskAsynchrone();

        String phpURL = "http://berghuis-peter.net/FinanceNous/getMesDepenses.php?idUtilisateur="+ global.getMainUtilisateur().getId_utilisateur();

        JSONArray jsonArray = getDataInJson(phpURL);

        return jsonConverter.convertJsonArrayToDepenseArray(jsonArray, global);
    }

    public void insertDepense(Depense depense) {
        makeTaskAsynchrone();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        sendData("http://berghuis-peter.net/FinanceNous/insertDepense.php?date=", dateFormat.format(depense.getDateDepense()) + "&montant=" + depense.getMontant() + "&pieceJoint=" + depense.getPieceJoint() + "&refMagasin=" + depense.getMagasin().getNom_managasin() + "&refDomaine=" + depense.getDomaine() + "&idUtilisateur=" + depense.getUtilisatuer().getId_utilisateur());
    }

    public void ajoutDomaine(Domaine domaine) {
        makeTaskAsynchrone();
        sendData("http://berghuis-peter.net/FinanceNous/ajoutDomaine.php?nom=", domaine.getNomDomaine());
    }

    public void ajoutMagasin(Magasin magasin) {
        makeTaskAsynchrone();
        sendData("http://berghuis-peter.net/FinanceNous/ajoutMagasin.php?id=", magasin.getId() + "&nom=" + magasin.getNom_managasin() + "&adresse=" + magasin.getAdresse1() + "&ville=" + magasin.getAdresse2() + "&codePostal=" + magasin.getCodePostal() + "&site=" + magasin.getSiteWeb() + "&tel=" + magasin.getTelephone());
    }

    public int getLastDepenseID()
    {
        int id = 0;
        JSONObject json = null;
        String phpURL = "http://berghuis-peter.net/FinanceNous/getLastDepenseID.php";

        makeTaskAsynchrone();

        JSONArray jsonArray = getDataInJson(phpURL);

        try {
            json = jsonArray.getJSONObject(0);
            id = json.getInt("id_depense");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return id++;
    }

    public ArrayList<String> getAllDomaines()
    {
        JSONArray jsonArray = null;
        JsonConverter jsonConverter = new JsonConverter();
        String phpURL = "http://berghuis-peter.net/FinanceNous/getAllDomaines.php";

        makeTaskAsynchrone();

        jsonArray = getDataInJson(phpURL);

        return jsonConverter.ConvertDomaineToStringArrayList(jsonArray);
    }

    public ArrayList<String> getAllMagasins()
    {
        JsonConverter jsonConverter = new JsonConverter();
        String phpURL = "http://berghuis-peter.net/FinanceNous/getAllMagasins.php";

        makeTaskAsynchrone();

        JSONArray jsonArray = getDataInJson(phpURL);

        return jsonConverter.ConvertMagasinToStringArrayList(jsonArray);
    }


    public Magasin getMagasinWithId(int idMagasin)
    {
        JSONObject json = null;
        JsonConverter jsonConverter = new JsonConverter();
        String phpURL = "http://berghuis-peter.net/FinanceNous/getMagasin.php?idMagasin="+idMagasin;

        makeTaskAsynchrone();

        JSONArray jsonArray = getDataInJson(phpURL);

        try {
            json = jsonArray.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonConverter.jsonToMagasin(json);
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

    private void sendData(String phpURL, String urlParameters) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(phpURL + urlParameters);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.getInputStream();
        } catch (Exception e) {
            Log.e("json", "could not insert");
        } finally {
            urlConnection.disconnect();
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