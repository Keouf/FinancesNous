package classes;


import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JsonConverter {

    MyDBHelper myDBHelper = new MyDBHelper();

    public void convertToDepense(JSONArray jsonArray, Activity act)
    {
        Global g = (Global)act.getApplication();
        JSONObject json;
        Depense depense = null;
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                json = jsonArray.getJSONObject(i);
                g.getMainUtilisateur().addDepense(new Depense(json.getInt("id_depense"), makeDate(json.getString("date_depense")), json.getDouble("montant_depense"), g.getMainUtilisateur(), myDBHelper.getDomaineWithId(json.getInt("domaine")), myDBHelper.getMagasinWithId(json.getInt("magasin")), json.getString("piece_jointe")));
            } catch (JSONException e) {
                Log.e("json", "jsonExeption, vérifie le nomage des champs");
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

    public ArrayList<Depense> ConvertDepensesToArrayList(Activity act)
    {
        Global g = (Global)act.getApplication();
        ArrayList<Depense> Depenses = new ArrayList<Depense>();
        for (int i=1; i <= g.getMainUtilisateur().getMesDepenses().size(); i++)
                Depenses.add(g.getMainUtilisateur().getMesDepenses().get(i));
        return Depenses;
    }

}
