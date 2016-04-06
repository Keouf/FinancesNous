package com.example.pcportablevidjay.financesnous;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

class RequestHandler {

    public String sendPostRequest(HashMap<String, String> postDataParams) {

        URL url;

        StringBuilder stringReponseTotal = new StringBuilder();
        try {
            url = new URL("http://berghuis-peter.net/FinanceNous/uploadImage.php");

            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
            connexion.setReadTimeout(15000);
            connexion.setConnectTimeout(15000);
            connexion.setRequestMethod("POST");
            connexion.setDoInput(true);
            connexion.setDoOutput(true);


            OutputStream os = connexion.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));


            writer.flush();
            writer.close();
            os.close();
            int responseCode = connexion.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader receptionReponse = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
                stringReponseTotal = new StringBuilder();
                String reponse;
                while ((reponse = receptionReponse.readLine()) != null) {
                    stringReponseTotal.append(reponse);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringReponseTotal.toString();
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean premierResultat = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (premierResultat)
                premierResultat = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}