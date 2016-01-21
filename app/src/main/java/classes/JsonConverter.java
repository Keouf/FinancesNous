package classes;


import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonConverter {

    MyDBHelper myDBHelper = new MyDBHelper();

    public void convertToDepense(JSONArray myJsonArray, Activity act)
    {
        Global g = (Global)act.getApplication();
        JSONObject myJsonObject;
        for (int i=0; i < myJsonArray.length(); i++) {
            try {
                myJsonObject = myJsonArray.getJSONObject(i);
                g.getMainUtilisateur().addDepense(new Depense(myJsonObject.getInt("id_depense"), makeDate(myJsonObject.getString("date_depense")), myJsonObject.getDouble("montant_depense"), g.getMainUtilisateur() ,  new Domaine(0, "test"), myDBHelper.getMagasinWithId(myJsonObject.getInt("MAGASIN_id_magasin")) , myJsonObject.getString("piece_jointe")));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public Date makeDate(String date)
    {
        Date myDate = null;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-mm");

        try {
            myDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  myDate;
    }

}
